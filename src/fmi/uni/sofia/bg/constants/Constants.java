package fmi.uni.sofia.bg.constants;

import java.util.concurrent.atomic.AtomicInteger;


public class Constants {
    public static final int STORE_CAPACITY = 1000;
    public static final int PRODUCER_THREAD_COUNT = 3;
    public static final int CONSUMER_THREAD_COUNT = 5;

    public static final int ACTUAL_NUMBER_OF_OCCURRENCES = 6768;//small=6768, standart=13743,large=26913

    public static final AtomicInteger numberOfOccurrences = new AtomicInteger(0);
}
