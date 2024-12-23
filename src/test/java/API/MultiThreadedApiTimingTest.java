package API;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultiThreadedApiTimingTest {

    public static void main(String[] args) throws InterruptedException {
        // Number of concurrent API calls
        int numberOfThreads = 5;
        
        // Create a thread pool
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        
        // Create a CountDownLatch to synchronize the start of the threads
        CountDownLatch latch = new CountDownLatch(1);
        
        // List to store response times
        List<Long> responseTimes = new ArrayList<>();
        
        // URL for API requests
        String apiUrl = "https://app.fireflink.com/";
        
        // Submit tasks to executor service
        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(() -> {
                try {
                    // Wait for the latch to count down to 0 (start signal)
                    latch.await();
                    
                    // Record the start time
                    long startTime = System.nanoTime();
                    
                    // Make the API call
                    makeApiCall(apiUrl);
                    
                    // Record the end time
                    long endTime = System.nanoTime();
                    
                    // Calculate the response time
                    long duration = endTime - startTime;
                    
                    // Store the response time
                    synchronized (responseTimes) {
                        responseTimes.add(duration);
                    }
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        
        // Start all threads at the same time
        latch.countDown();
        
        // Shut down the executor service and wait for tasks to finish
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
        
        // Analyze the response times
        analyzeResponseTimes(responseTimes);
    }

    // Simulated method to make an API call
    public static void makeApiCall(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.getResponseCode();  // Send the request and get the response code
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to analyze the response times
    public static void analyzeResponseTimes(List<Long> responseTimes) {
        long maxTime = responseTimes.stream().mapToLong(Long::longValue).max().orElse(0);
        long minTime = responseTimes.stream().mapToLong(Long::longValue).min().orElse(0);
        long avgTime = (long) responseTimes.stream().mapToLong(Long::longValue).average().orElse(0);

        System.out.println("Max response time: " + maxTime / 1_000_000.0 + " ms");
        System.out.println("Min response time: " + minTime / 1_000_000.0 + " ms");
        System.out.println("Average response time: " + avgTime / 1_000_000.0 + " ms");

        // If the difference between min and max response times is significant, it indicates delays
        if (maxTime - minTime > 1_000_000_000) {  // e.g., >1 second
            System.out.println("Significant delay detected between API calls.");
        } else {
            System.out.println("No significant delay detected.");
        }
    }
}
