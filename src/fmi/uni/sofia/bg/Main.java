package fmi.uni.sofia.bg;

import fmi.uni.sofia.bg.constants.Constants;
import fmi.uni.sofia.bg.crawler.DirectoryCrawler;
import fmi.uni.sofia.bg.test.Test;
import fmi.uni.sofia.bg.threads.ThreadConfiguration;

import java.io.File;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        File file = new File("D:\\TestDirectoryTree");
        String input = "Strashimir Rashev 61702";
        DirectoryCrawler directoryCrawler = new DirectoryCrawler(file, input);
        Test test = new Test(directoryCrawler);
        ThreadConfiguration threadConfiguration = new ThreadConfiguration(Constants.PRODUCER_THREAD_COUNT, Constants.CONSUMER_THREAD_COUNT);
        //test.test(Constants.TEST_LOWER_BOUND_PRODUCER, Constants.TEST_UPPER_BOUND_PRODUCER, Constants.TEST_LOWER_BOUND_CONSUMER, Constants.TEST_UPPER_BOUND_CONSUMER);
        System.out.println(directoryCrawler.searchForWordInDirectoryTree(threadConfiguration));
    }
}