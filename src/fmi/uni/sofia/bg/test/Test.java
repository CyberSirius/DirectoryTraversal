package fmi.uni.sofia.bg.test;

import fmi.uni.sofia.bg.crawler.DirectoryCrawler;
import fmi.uni.sofia.bg.threads.ThreadConfiguration;

import java.util.ArrayList;
import java.util.Collection;

public class Test {

    private final DirectoryCrawler directoryCrawler;

    public Test(DirectoryCrawler directoryCrawler) {
        this.directoryCrawler = directoryCrawler;
    }

    public void test(Collection<ThreadConfiguration> threadConfigurations) throws InterruptedException {
        ArrayList<Result> results = new ArrayList<>();
        threadConfigurations.forEach(threadConfiguration -> {
            Result resultOfThreeIdenticalRuns = getResultOfThreeIdenticalRuns(threadConfiguration);
            results.add(resultOfThreeIdenticalRuns);
            System.out.println(resultOfThreeIdenticalRuns);
        });
        System.out.println("Results in order");
        results.stream().sorted((r1, r2) -> Long.compare(r1.getTime(), r2.getTime())).forEach(System.out::println);
    }

    private Result getResultOfThreeIdenticalRuns(ThreadConfiguration threadConfiguration) {
        ArrayList<Result> results = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            results.add(directoryCrawler.searchForWordInDirectoryTree(threadConfiguration));
        }
        return Result.getMeanResult(results);
    }
}