package bg.rashev.traversal;

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
                if (product.containsString(input)) {
                    product.print();
                    Constants.numberOfOccurrences.incrementAndGet();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}