package fmi.uni.sofia.bg.constants;

import java.util.concurrent.atomic.AtomicInteger;


public class Constants {
    public static final int STORE_CAPACITY = 10000;

    public static final int CONSUMER_THREAD_COUNT = 6;
    public static final int PRODUCER_THREAD_COUNT = 2;
    public static final AtomicInteger numberOfOccurrences = new AtomicInteger(0);

    //Testing constants
    public static final int ACTUAL_NUMBER_OF_OCCURRENCES = 4099;
    public static final int TEST_LOWER_BOUND_PRODUCER = 1;
    public static final int TEST_UPPER_BOUND_PRODUCER = 10;
    public static final int TEST_LOWER_BOUND_CONSUMER = 1;
    public static final int TEST_UPPER_BOUND_CONSUMER = 10;
    public static StringBuilder stringBuilder = new StringBuilder();
}
