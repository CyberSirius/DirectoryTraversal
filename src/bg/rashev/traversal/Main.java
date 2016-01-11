package bg.rashev.traversal;

import java.io.File;
import java.util.concurrent.*;

public class Main {
    private static ExecutorService producerPool;
    private static Store store;

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        File file = new File("D:\\TestDirectoryTree");
        producerPool = Executors.newFixedThreadPool(Constants.PRODUCER_THREAD_COUNT);
        // ExecutorService consumerPool = Executors.newFixedThreadPool(Constants.CONSUMER_THREAD_COUNT);
        store = new Store();
        String input = "sonder";
        Thread consumerThread;
        for (int i = 0; i < Constants.CONSUMER_THREAD_COUNT; i++) {
            consumerThread = new Thread(new Consumer(store, input));
            consumerThread.start();
        }
        long mills = System.currentTimeMillis();
        directoryTraverse(file);
        producerPool.shutdown();
        producerPool.awaitTermination(1, TimeUnit.DAYS);
        store.setProducingToFalse();
        //consumerPool.shutdown();

        System.out.println(System.currentTimeMillis() - mills);
        System.out.println("Number of occurrences: " + Constants.numberOfOccurrences.get());
    }

    public static void directoryTraverse(File file) {
        // TODO: 09-Jan-16 may try threads
        // TODO: 10-Jan-16 try/catch if file is directory
        for (File tempFile : file.listFiles()) {
            if (tempFile.isFile()) {
                producerPool.execute(new Producer(tempFile, store));

            } else directoryTraverse(tempFile);
        }
    }
}
