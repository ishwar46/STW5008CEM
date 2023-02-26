package Seven;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

public class Question7B {
    private static final int NUM_THREADS = 10; // number of threads to use

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool(); // create a cached thread pool
        CompletionService<String> completionService = new ExecutorCompletionService<>(executorService); // create a completion service to retrieve completed tasks
        Set<String> results = new HashSet<>(); // use a set to store results from each thread

        // submit tasks to executor service
        for (int i = 0; i < 50; i++) {
            completionService.submit(new CrawlTask("http://ishwarchaudhary.com/" + i));
        }

        // retrieve completed tasks and store results in a set
        for (int i = 0; i < 50; i++) {
            try {
                results.add(completionService.take().get());
            } catch (InterruptedException | ExecutionException e) {
                System.out.println("Task failed: " + e.getMessage());
            }
        }

        // print results
        for (String result : results) {
            System.out.println(result);
        }

        executorService.shutdown(); // shut down executor service
    }

    // A private static class for the task to be executed by the thread pool
    private static class CrawlTask implements Callable<String> {
        private final String url;

        public CrawlTask(String url) {
            this.url = url;
        }

        // The call method contains the actual logic for web crawling
        @Override
        public String call() throws Exception {
            // implement web crawling logic here
            return "Crawled " + url;
        }
    }
}
