package bg.rashev.traversal;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by CyberSirius on 09-Jan-16.
 */
class Constants {
    public static final int STORE_CAPACITY = 1000;

    public static final int CONSUMER_THREAD_COUNT = 10;
    public static final int PRODUCER_THREAD_COUNT = 5;

    //Testing constants

    public static final AtomicInteger numberOfOccurrences = new AtomicInteger(0);
    public static final int ACTUAL_NUMBER_OF_OCCURRENCES = 179;

    public static final int TEST_LOWER_BOUND_PRODUCER = 1;
    public static final int TEST_UPPER_BOUND_PRODUCER = 10;

    public static final int TEST_LOWER_BOUND_CONSUMER = 1;
    public static final int TEST_UPPER_BOUND_CONSUMER = 10;
}
