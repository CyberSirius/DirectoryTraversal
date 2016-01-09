package bg.rashev.traversal;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by CyberSirius on 09-Jan-16.
 */
public class Store {
    private ArrayBlockingQueue<Product> store;
    private boolean isProducing = true;

    public void setProducing(boolean producing) {
        isProducing = producing;
    }

    public boolean isProducing() {
        return isProducing;
    }

    public Store() {
        this.store = new ArrayBlockingQueue<>(Constants.STORE_CAPACITY);
    }

    public void add(Product product) throws InterruptedException {
        store.put(product);//// TODO: 09-Jan-16 don't know what im doing
    }

    public Product get() throws InterruptedException {
        return store.take();
    }

    public boolean isEmpty() {
        return store.isEmpty();
    }
    public void remove(Product product){
        store.remove(product);
    }
}
