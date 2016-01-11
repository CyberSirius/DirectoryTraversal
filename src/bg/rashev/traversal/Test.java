package bg.rashev.traversal;

import java.util.ArrayList;

class Test {

    private final DirectoryCrawler directoryCrawler;

    public Test(DirectoryCrawler directoryCrawler) {
        this.directoryCrawler = directoryCrawler;
    }

    public void test(int lowerBoundProducerThreads, int upperBoundProducerThreads, int lowerBoundConsumerThreads, int upperBoundConsumerThreads) throws InterruptedException {
        ArrayList<Result> results = new ArrayList<>();
        if (lowerBoundConsumerThreads > upperBoundConsumerThreads || lowerBoundProducerThreads > upperBoundProducerThreads)
            throw new IllegalArgumentException("Lower bound should be smaller, than upper bound");
        for (int i = lowerBoundProducerThreads; i <= upperBoundProducerThreads; i++) {
            for (int j = lowerBoundConsumerThreads; j <= upperBoundConsumerThreads; j++) {
                Result resultOfThreeIdenticalRuns = getResultOfThreeIdenticalRuns(i, j);
                results.add(resultOfThreeIdenticalRuns);
                System.out.println(resultOfThreeIdenticalRuns);
            }
        }
        System.out.println("Results in order");
        results.stream().sorted((r1, r2) -> Long.compare(r1.getTime(), r2.getTime())).forEach(System.out::println);
    }

    private Result getResultOfThreeIdenticalRuns(int producerThreadsCount, int consumerThreadsCounts) throws InterruptedException {
        ArrayList<Result> results = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            results.add(directoryCrawler.searchForWordInDirectoryTree(producerThreadsCount, consumerThreadsCounts));
        }
        return Result.getMeanResult(results);
    }

}
