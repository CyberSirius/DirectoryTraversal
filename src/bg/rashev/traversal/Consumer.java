package bg.rashev.traversal;

/**
 * Created by CyberSirius on 09-Jan-16.
 */
public class Consumer implements Runnable {
    private Store store;
    private String input;

    public Consumer(Store store, String input) {
        this.store = store;
        this.input = input;
    }

    @Override
    public void run() {
        try {
            Product product = store.get();
            while (!store.isEmpty() || store.isProducing()) {
                if (product.getLine().contains(input)) {
                    System.out.println(product);
                }
                else {
                    store.remove(product);
                }
                product = store.get();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
