package Torus_1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class DownloadImage {
    public static void main(String[] args) {
        try {
            // Step 1: The API URL to fetch the HTML response
            String apiUrl = "http://torusapis.cmots.com/AMCLogo/6051";

            // Fetch the HTML content from the API
            Document document = Jsoup.connect(apiUrl).get();

            // Step 2: Extract the image URL from the <img> tag's src attribute
            String imageRelativeUrl = document.select("img").attr("src");

            // Step 3: Build the full image URL (assuming the base URL is the same)
            String baseUrl = "http://torusapis.cmots.com";
            String imageUrl = baseUrl + imageRelativeUrl;

            // Step 4: Download the image with redirect handling
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Allow following redirects (default behavior, but setting explicitly)
            connection.setInstanceFollowRedirects(true);

            // Connect to the URL and check the response code
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_MOVED_PERM || responseCode == HttpURLConnection.HTTP_MOVED_TEMP) {
                // If redirected, follow the Location header
                if (responseCode == HttpURLConnection.HTTP_MOVED_PERM || responseCode == HttpURLConnection.HTTP_MOVED_TEMP) {
                    String redirectUrl = connection.getHeaderField("Location");
                    System.out.println("Redirected to: " + redirectUrl);

                    // Open the new connection to the redirect URL
                    connection = (HttpURLConnection) new URL(redirectUrl).openConnection();
                    connection.connect();
                }

                // Create input stream from the connection
                InputStream inputStream = connection.getInputStream();

                // Create a file to save the image
                FileOutputStream outputStream = new FileOutputStream("C:\\Users\\User\\OneDrive\\Desktop\\New folder\\Images\\SubImage_0.png");

                // Read the input stream and write to file
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                // Close the streams
                outputStream.close();
                inputStream.close();

                System.out.println("Image downloaded successfully.");
            } else {
                System.out.println("Failed to fetch image. HTTP response code: " + responseCode);
            }

            connection.disconnect();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
}
