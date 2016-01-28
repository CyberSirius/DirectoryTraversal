package fmi.uni.sofia.bg.store;

import fmi.uni.sofia.bg.constants.Constants;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;


public class Store {
    private final BlockingQueue<Product> store = new LinkedBlockingQueue<>(Constants.STORE_CAPACITY);// queue is fastest, tested with small files in approx. 1 gb tree

    private boolean isProducing = true;

    public void setProducingToFalse() {
        isProducing = false;
    }

    public boolean isProducing() {
        return isProducing;
    }

    public void add(Product product) throws InterruptedException {
        store.put(product);
    }

    public Product get() throws InterruptedException {
        return store.poll(1, TimeUnit.SECONDS);//returns null if 1 second elapses, before an element is available (should be enough for one to become)
    }

    public boolean isEmpty() {
        return this.store.isEmpty();
    }

    public int size() {
        return this.store.size();
    }
}

