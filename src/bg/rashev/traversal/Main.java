package bg.rashev.traversal;

import java.io.File;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        File file = new File("D:\\TestDirectoryTree");
        String input = "sonder";
        DirectoryCrawler directoryCrawler = new DirectoryCrawler(file, input);
        Test test = new Test(directoryCrawler);
        //test.test(Constants.TEST_LOWER_BOUND_PRODUCER, Constants.TEST_UPPER_BOUND_PRODUCER, Constants.TEST_LOWER_BOUND_CONSUMER, Constants.TEST_UPPER_BOUND_CONSUMER);
        System.out.println(directoryCrawler.searchForWordInDirectoryTree(Constants.PRODUCER_THREAD_COUNT, Constants.CONSUMER_THREAD_COUNT));
    }
}