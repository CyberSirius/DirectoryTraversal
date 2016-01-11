package bg.rashev.traversal;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * Created by CyberSirius on 09-Jan-16.
 */
class Store {
    private final LinkedBlockingDeque<Product> store;//todo test with linked deque
    private boolean isProducing = true;

    public Store() {
        this.store = new LinkedBlockingDeque<>(Constants.STORE_CAPACITY);
    }

    public void setProducingToFalse() {
        isProducing = false;
    }

    public boolean isProducing() {
        return isProducing;
    }

    public void add(Product product) throws InterruptedException {
        store.putLast(product);
    }
    public Product get() throws InterruptedException {
        return store.pollFirst(1, TimeUnit.SECONDS);//returns null if 1 second elapses, before an element is available (should be enough for one to become)
    }

}
