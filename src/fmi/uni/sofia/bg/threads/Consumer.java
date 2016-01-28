package fmi.uni.sofia.bg.threads;

import fmi.uni.sofia.bg.store.Product;
import fmi.uni.sofia.bg.store.Store;

public class Consumer implements Runnable {
    private final Store store;
    private final String input;

    public Consumer(Store store, String input) {
        this.store = store;
        this.input = input;
    }

    @Override
    public void run() {
        try {
            Product product;
            while (store.isProducing()) {
                product = store.get();
                if (product == null)
                    break;
                if (product.containsString(input)) {
                    product.print();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}