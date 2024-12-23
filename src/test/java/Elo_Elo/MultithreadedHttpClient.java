package Elo_Elo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultithreadedHttpClient {

    private static final int TIMEOUT = 5000; // 5 seconds timeout for connection and read

    // List to hold responses
    private static final List<String> responses = new ArrayList<>();

    public static void main(String[] args) {
        // Define the curl command strings
        List<String> curlCommands = List.of(
            "curl --location 'https://jsonplaceholder.typicode.com/posts/1' \\\r\n"
                    + "--header 'Accept: application/json' \\\r\n" + "--header 'User-Agent: MyApp/1.0'\r\n",
            "curl --location 'https://jsonplaceholder.typicode.com/posts/2' \\\r\n"
                    + "--header 'Accept: application/json' \\\r\n" + "--header 'User-Agent: MyApp/1.0'\r\n"
        );

        // Determine the thread pool size based on the number of curl commands
        int threadPoolSize = curlCommands.size();
        ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);

        for (String curlCommand : curlCommands) {
            executor.execute(() -> {
                try {
                    executeCurl(curlCommand);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        // Shutdown the executor service
        executor.shutdown();
        try {
            // Wait for all tasks to finish
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        // Print all responses collected
        System.out.println("All Responses:");
        for (String response : responses) {
            System.out.println(response);
        }
        System.out.println("Total Responses: " + responses.size());
    }

    // Execute curl command
    private static void executeCurl(String curlCommand) throws IOException {
        String url = extractUrl(curlCommand);
        Map<String, String> headers = extractHeaders(curlCommand);

        HttpURLConnection con = null;
        BufferedReader in = null;
        try {
            URL obj = new URL(url);
            con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(TIMEOUT);
            con.setReadTimeout(TIMEOUT);

            // Set headers
            for (Map.Entry<String, String> header : headers.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            // Read response
            int responseCode = con.getResponseCode();
            StringBuilder response = new StringBuilder();

            in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            // Add response to list
            synchronized (responses) {
                responses.add(response.toString());
            }
        } catch (IOException e) {
            synchronized (responses) {
                responses.add("Error reading response: " + e.getMessage());
            }
        } finally {
            // Ensure resources are closed
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ignored) {
                }
            }
            if (con != null) {
                con.disconnect();
            }
        }
    }

    // Extract URL from the curl command
    private static String extractUrl(String curlCommand) {
        String[] parts = curlCommand.split("'");
        return parts[1];
    }

    // Extract headers from the curl command
    private static Map<String, String> extractHeaders(String curlCommand) {
        Map<String, String> headers = new HashMap<>();
        String[] parts = curlCommand.split("--header '");
        for (int i = 1; i < parts.length; i++) {
            String header = parts[i].split("'")[0];
            String[] headerParts = header.split(": ");
            if (headerParts.length == 2) {
                headers.put(headerParts[0], headerParts[1]);
            }
        }
        return headers;
    }
}