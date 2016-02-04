package fmi.uni.sofia.bg.store;

import fmi.uni.sofia.bg.constants.Constants;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;


public class Store {
    private final BlockingQueue<Product> store = new LinkedBlockingQueue<>(Constants.STORE_CAPACITY);

    private boolean isProducing = true;

    public void setProducingToFalse() {
        isProducing = false;
    }

    public boolean isProducing() {
        return isProducing;
    }

    public void add(Product product) {
        try {
            store.put(product);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Product get() {
        try {
            return store.poll(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}

