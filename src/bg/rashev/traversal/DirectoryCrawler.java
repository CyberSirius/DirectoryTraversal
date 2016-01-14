package bg.rashev.traversal;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


class DirectoryCrawler {
    private final File file;
    private final String input;
    private ExecutorService producerPool;
    private Store store;

    public DirectoryCrawler(File file, String input) {
        this.file = file;
        this.input = input;
    }

    public Result searchForWordInDirectoryTree(int producerThreadCount, int consumerThreadCount) throws InterruptedException {
        producerPool = Executors.newFixedThreadPool(producerThreadCount);
        store = new Store();
        Thread consumerThread;
        Constants.numberOfOccurrences.set(0);
        for (int i = 0; i < consumerThreadCount; i++) {
            consumerThread = new Thread(new Consumer(store, input));
            consumerThread.start();
        }
        long t0 = System.currentTimeMillis();
        directoryTraverse(this.file);
        producerPool.shutdown();
        producerPool.awaitTermination(1, TimeUnit.DAYS);// TODO: 14-Jan-16 !isTerminated infinite loop looks to be slower, still not sold on awaitTermination
        store.setProducingToFalse();
        // FIXME: 14-Jan-16 consumers may not be finished before the return, try to think of a test case where this happens
        long t1 = System.currentTimeMillis();
        return new Result(t1 - t0, producerThreadCount, consumerThreadCount, Constants.numberOfOccurrences.get() == Constants.ACTUAL_NUMBER_OF_OCCURRENCES, Constants.numberOfOccurrences.get());
    }

    private void directoryTraverse(File file) {
        // TODO: 14-Jan-16 maybe try a fork-join pool
        if (file.isDirectory()) {
            for (File tempFile : file.listFiles()) {
                if (tempFile.isFile()) {
                    producerPool.execute(new Producer(tempFile, store));
                } else directoryTraverse(tempFile);
            }
        } else producerPool.execute(new Producer(file, store));
    }
}