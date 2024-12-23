package MobileWare;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class BrowserStackFileDelete {

    public static void main(String[] args) {
        String apiEndpoint = "https://api-cloud.browserstack.com/automate/custom_media/delete/media_id";
        String username = "mobilewaretechno_mbNSnA"; // Replace with your username
        String accessKey = "reMqKvBztxhtP8so6CNG"; // Replace with your access key
        String mediaId = "10ce726cf3eb726e69d3553f030f6f193b0d0462"; // Replace with the actual media ID
        //Local Variable : mediaUrl : {"media_url":"media://ed2e3b349e50bb6d681ecb31cb16608a063859c2"}

        try {
            deleteFile(apiEndpoint, mediaId, username, accessKey);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteFile(String apiEndpoint, String mediaId, String username, String accessKey) throws IOException {
        URL url = new URL(apiEndpoint.replace("media_id", mediaId));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");
        String auth = username + ":" + accessKey;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
        connection.setRequestProperty("Authorization", "Basic " + encodedAuth);

        int responseCode = connection.getResponseCode();
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        System.out.println("Response Body : " + response.toString());

        if (responseCode == HttpURLConnection.HTTP_NO_CONTENT || responseCode == HttpURLConnection.HTTP_OK) {
            System.out.println("File deleted successfully");
        } else {
            System.out.println("Failed to delete file. Response Code: " + responseCode);
        }

        connection.disconnect();
    }
}