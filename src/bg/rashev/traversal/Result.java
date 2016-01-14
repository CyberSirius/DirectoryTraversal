package bg.rashev.traversal;

import java.util.Collection;

public class Result {

    private final long time;
    private final int numberOfProducerThreads;
    private final int numberOfConsumerThreads;
    private final boolean isCorrect;
    private final int numberOfOccurrences;

    public Result(long time, int producerThreads, int consumerThreads, boolean isCorrect, int numberOfOccurrences) {
        this.time = time;
        this.numberOfProducerThreads = producerThreads;
        this.numberOfConsumerThreads = consumerThreads;
        this.isCorrect = isCorrect;
        this.numberOfOccurrences = numberOfOccurrences;
    }

    public static Result getMeanResult(Collection<Result> results) {
        long newMean = 0;
        int newNumberOfOccurrences = 0;
        boolean newIsCorrect = true;
        int newProdThreads = 0;
        int newConsThreads = 0;
        for (Result result : results) {
            newMean += result.getTime();
            newNumberOfOccurrences += result.getNumberOfOccurrences();
            newConsThreads = result.numberOfConsumerThreads;
            newProdThreads = result.numberOfProducerThreads;
            if (!result.isCorrect())
                newIsCorrect = false;
        }
        return new Result(newMean / 3, newProdThreads, newConsThreads, newIsCorrect, newNumberOfOccurrences / 3);
    }

    @Override
    public String toString() {
        if (isCorrect()) {
            return "Result is correct for " + numberOfProducerThreads + " producer threads and " + numberOfConsumerThreads + " consumer threads. In " + getTime() + " milliseconds, the crawler managed to find " + numberOfOccurrences + " occurrences of the word.";
        } else {
            return "Result is incorrect: " +
                    ", producerThreads= " + numberOfProducerThreads +
                    ", consumerThreads= " + numberOfConsumerThreads +
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
