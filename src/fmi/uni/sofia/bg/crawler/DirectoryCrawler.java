package fmi.uni.sofia.bg.crawler;

import fmi.uni.sofia.bg.constants.Constants;
import fmi.uni.sofia.bg.store.Store;
import fmi.uni.sofia.bg.test.Result;
import fmi.uni.sofia.bg.threads.Consumer;
import fmi.uni.sofia.bg.threads.Producer;
import fmi.uni.sofia.bg.threads.ThreadConfiguration;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class DirectoryCrawler {
    private final File file;
    private final String input;
    ExecutorService consumerPool;
    private ExecutorService producerPool;
    private Store store;

    public DirectoryCrawler(File file, String input) {
        this.file = file;
        this.input = input;
    }

    public Result searchForWordInDirectoryTree(ThreadConfiguration threadConfiguration) {
        producerPool = Executors.newFixedThreadPool(threadConfiguration.getProducerThreadCount());
        consumerPool = Executors.newFixedThreadPool(threadConfiguration.getConsumerThreadCount());
        store = new Store();
        Constants.numberOfOccurrences.set(0);
        long t0 = System.currentTimeMillis();
        startConsumerThreads(threadConfiguration, consumerPool);
        directoryTraverse(this.file);
        producerPool.shutdown();
        try {
            if (producerPool.awaitTermination(1, TimeUnit.DAYS))
                store.setProducingToFalse();
            consumerPool.shutdown();
            consumerPool.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long t1 = System.currentTimeMillis();
        return new Result(t1 - t0, threadConfiguration, Constants.numberOfOccurrences.get() == Constants.ACTUAL_NUMBER_OF_OCCURRENCES, Constants.ACTUAL_NUMBER_OF_OCCURRENCES);
    }

    private void startConsumerThreads(ThreadConfiguration threadConfiguration, ExecutorService consumerPool) {
        for (int i = 0; i < threadConfiguration.getConsumerThreadCount(); i++) {
            consumerPool.execute(new Consumer(store, input));
        }
    }

    private void directoryTraverse(File file) {
        if (file.isDirectory()) {
            for (File tempFile : file.listFiles()) {
                if (tempFile.isFile()) {
                    producerPool.execute(new Producer(tempFile, store));
                } else directoryTraverse(tempFile);
            }
        } else producerPool.execute(new Producer(file, store));
    }
}