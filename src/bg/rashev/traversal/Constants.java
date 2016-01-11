package bg.rashev.traversal;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by CyberSirius on 09-Jan-16.
 */
public class Constants {
    public static final int STORE_CAPACITY = 1000;
    public static final int CONSUMER_THREAD_COUNT = 5;
    public static final int PRODUCER_THREAD_COUNT = 5;
    public static AtomicInteger numberOfOccurrences = new AtomicInteger(0);
}
