package bg.rashev.traversal;

import java.io.File;
import java.util.concurrent.*;

public class Main {
    private static ExecutorService producerPool;
    private static Store store;

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        File file = new File("D:\\Work");
        producerPool = Executors.newFixedThreadPool(Constants.PRODUCER_THREAD_COUNT);
        ExecutorService consumerPool = Executors.newFixedThreadPool(Constants.CONSUMER_THREAD_COUNT);
        store = new Store();
        String input = "java";
        for (int i = 0; i < Constants.CONSUMER_THREAD_COUNT; i++) {
            consumerPool.execute(new Consumer(store, input));
        }
        long mills = System.currentTimeMillis();
        directoryTraverse(file);
        producerPool.shutdown();
        producerPool.awaitTermination(1, TimeUnit.MINUTES);
        store.setProducingToFalse();
        while (true) {
            if (store.isEmpty()) {
                consumerPool.shutdown();
                break;
            }
        }

        System.out.println(System.currentTimeMillis() - mills);
    }

    public static void directoryTraverse(File file) {
        // TODO: 09-Jan-16 may try threads

        for (File tempFile : file.listFiles()) {
            if (tempFile.isFile()) {
                producerPool.execute(new Producer(tempFile, store));

            } else directoryTraverse(tempFile);
        }
    }
}
