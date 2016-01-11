package bg.rashev.traversal;

/**
 * Created by CyberSirius on 09-Jan-16.
 */
class Consumer implements Runnable {
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
                if (product.getLine().contains(input)) {
                    System.out.println(product);
                    Constants.numberOfOccurrences.incrementAndGet();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}