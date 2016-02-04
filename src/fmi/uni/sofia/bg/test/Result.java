package fmi.uni.sofia.bg.test;

import fmi.uni.sofia.bg.threads.ThreadConfiguration;

import java.util.Collection;

public class Result {

    private final long time;
    private final boolean isCorrect;
    private final int numberOfOccurrences;
    ThreadConfiguration threadConfiguration;

    public Result(long time, ThreadConfiguration threadConfiguration, boolean isCorrect, int numberOfOccurrences) {
        this.time = time;
        this.threadConfiguration = threadConfiguration;
        this.isCorrect = isCorrect;
        this.numberOfOccurrences = numberOfOccurrences;
    }

    public static Result getMeanResult(Collection<Result> results, int numberOfRuns) {
        long newMean = 0;
        int newNumberOfOccurrences = 0;
        boolean newIsCorrect = true;
        ThreadConfiguration threadConfiguration = new ThreadConfiguration(0, 0);
        for (Result result : results) {
            newMean += result.getTime();
            newNumberOfOccurrences += result.getNumberOfOccurrences();
            threadConfiguration.setProducerThreadCount(result.threadConfiguration.getProducerThreadCount());
            threadConfiguration.setConsumerThreadCount(result.threadConfiguration.getConsumerThreadCount());
            if (!result.isCorrect())
                newIsCorrect = false;
        }
        return new Result(newMean / numberOfRuns, threadConfiguration, newIsCorrect, newNumberOfOccurrences / numberOfRuns);
    }

    @Override
    public String toString() {
        if (isCorrect()) {
            return "Result is correct for " + threadConfiguration.getProducerThreadCount() + " producer threads and " + threadConfiguration.getConsumerThreadCount() + " consumer threads. In " + getTime() + " milliseconds, the crawler managed to find " + numberOfOccurrences + " occurrences of the word.";
        } else {
            return "Result is incorrect: " +
                    ", producerThreads= " + threadConfiguration.getProducerThreadCount() +
                    ", consumerThreads= " + threadConfiguration.getConsumerThreadCount() +
                    ", time=" + time +
                    ", numberOfOccurrences=" + numberOfOccurrences;
        }
    }

    public long getTime() {
        return time;
    }

    private boolean isCorrect() {
        return isCorrect;
    }

    private int getNumberOfOccurrences() {
        return numberOfOccurrences;
    }
}
