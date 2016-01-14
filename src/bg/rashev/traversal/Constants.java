package bg.rashev.traversal;

import java.util.concurrent.atomic.AtomicInteger;


class Constants {
    public static final int STORE_CAPACITY = 10000;

    public static final int CONSUMER_THREAD_COUNT = 500;
    public static final int PRODUCER_THREAD_COUNT = 1000;

    //Testing constants

    public static final AtomicInteger numberOfOccurrences = new AtomicInteger(0);
    public static final int ACTUAL_NUMBER_OF_OCCURRENCES = 1364;

    public static final int TEST_LOWER_BOUND_PRODUCER = 1;
    public static final int TEST_UPPER_BOUND_PRODUCER = 10;

    public static final int TEST_LOWER_BOUND_CONSUMER = 1;
    public static final int TEST_UPPER_BOUND_CONSUMER = 10;
}
