package bg.rashev.traversal;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by CyberSirius on 09-Jan-16.
 */
public class Store {
    private ArrayBlockingQueue<Product> store;
    private boolean isProducing = true;

    public Store() {
        this.store = new ArrayBlockingQueue<>(Constants.STORE_CAPACITY);
    }

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
        return store.poll(1, TimeUnit.SECONDS);
    }

    public boolean isEmpty() {
        return store.isEmpty();
    }
}
