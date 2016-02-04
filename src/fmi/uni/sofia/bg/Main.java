package fmi.uni.sofia.bg;

import fmi.uni.sofia.bg.constants.Constants;
import fmi.uni.sofia.bg.crawler.DirectoryCrawler;
import fmi.uni.sofia.bg.threads.ThreadConfiguration;

import java.io.File;

class Main {

    public static void main(String[] args) {
        File file = new File("D:\\TestDirectoryTreeSmall");
        String input = "Java technologies";
        DirectoryCrawler directoryCrawler = new DirectoryCrawler(file, input);
//        List<ThreadConfiguration> configurations = new ArrayList<>();
////        for (int i = 1; i < 8; i++) {
////            configurations.add(new ThreadConfiguration(i, 8 - i));
////        }
//        configurations.add(new ThreadConfiguration(2, 6));
//        configurations.add(new ThreadConfiguration(3, 5));
//        Test test = new Test(directoryCrawler);
        //test.test(configurations, 10);
        ThreadConfiguration threadConfiguration = new ThreadConfiguration(Constants.PRODUCER_THREAD_COUNT, Constants.CONSUMER_THREAD_COUNT);
        System.out.println(directoryCrawler.searchForWordInDirectoryTree(threadConfiguration));
    }
}