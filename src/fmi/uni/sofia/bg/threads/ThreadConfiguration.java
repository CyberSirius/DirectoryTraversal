package fmi.uni.sofia.bg.threads;

/**
 * Created by CyberSirius on 28-Jan-16.
 */
public class ThreadConfiguration {
    private int producerThreadCount;
    private int consumerThreadCount;

    public ThreadConfiguration(int producerThreadCount, int consumerThreadCount) {
        this.producerThreadCount = producerThreadCount;
        this.consumerThreadCount = consumerThreadCount;
    }

    public int getProducerThreadCount() {
        return producerThreadCount;
    }

    public void setProducerThreadCount(int producerThreadCount) {
        if (producerThreadCount < 1)
            throw new IllegalArgumentException("Number of threads should be a positive number!");
        this.producerThreadCount = producerThreadCount;
    }

    public int getConsumerThreadCount() {
        return consumerThreadCount;
    }

    public void setConsumerThreadCount(int consumerThreadCount) {
        if (consumerThreadCount < 1)
            throw new IllegalArgumentException("Number of threads should be a positive number!");
        this.consumerThreadCount = consumerThreadCount;
    }
}
