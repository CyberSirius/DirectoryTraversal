package bg.rashev.traversal;

import java.io.*;
import java.sql.Time;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    private static ExecutorService producerPool;
    private static ExecutorService consumerPool;
    private static Store store;

    public static void main(String[] args) throws InterruptedException {
        File file = new File("D:\\Work\\Mini-mathematica");
        producerPool = Executors.newFixedThreadPool(10);
        consumerPool = Executors.newFixedThreadPool(10);
        store = new Store();
        long mills = System.currentTimeMillis();
        directoryTraverse(file);
        producerPool.shutdown();
        while (true) {
            if (producerPool.isShutdown()) {
                store.setProducing(false);
                consumerPool.shutdown();
                break;
            }
        }
        System.out.println(System.currentTimeMillis() - mills);
    }

    public static void directoryTraverse(File file) {
        //if not directory exception
        // TODO: 09-Jan-16 may try threads

        for (File tempFile : file.listFiles()) {
            if (tempFile.isFile()) {
                consumerPool.execute(new Consumer(store, "java"));
                producerPool.execute(new Producer(tempFile, store));
            } else directoryTraverse(tempFile);
        }
    }
}
