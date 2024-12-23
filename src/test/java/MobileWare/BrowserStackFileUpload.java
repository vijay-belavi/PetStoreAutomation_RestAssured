package MobileWare;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BrowserStackFileUpload {

    public static void main(String[] args) {
        String apiEndpoint = "https://api-cloud.browserstack.com/automate/upload-media";
        String filePath = "C:\\Users\\User\\Downloads\\agentFile_PartnerError.csv"; // Replace with your file path
        String username = "newtest_kJ98gW"; // Replace with your username
        String accessKey = "vu5WrGaKu7QD2qDAo1dG"; // Replace with your access key

        try {
            uploadFile(apiEndpoint, filePath, username, accessKey);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void uploadFile(String apiEndpoint, String filePath, String username, String accessKey) throws IOException {
        File file = new File(filePath);
        String boundary = "Boundary-" + System.currentTimeMillis();
        URL url = new URL(apiEndpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        String auth = username + ":" + accessKey;
        String encodedAuth = java.util.Base64.getEncoder().encodeToString(auth.getBytes());
        connection.setRequestProperty("Authorization", "Basic " + encodedAuth);
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

        try (DataOutputStream out = new DataOutputStream(connection.getOutputStream())) {
            out.writeBytes("--" + boundary + "\r\n");
            out.writeBytes("Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getName() + "\"\r\n");
            out.writeBytes("Content-Type: " + Files.probeContentType(file.toPath()) + "\r\n\r\n");
            Files.copy(file.toPath(), out);
            out.writeBytes("\r\n--" + boundary + "--\r\n");
        }

        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                String jsonResponse = response.toString();
                String regex = "media_url\":\"media://([a-f0-9]{40,})";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(jsonResponse);

                // Find and return the media ID
                if (matcher.find()) {
                    String mediaUrl = matcher.group(1).toString();
                    System.out.println(mediaUrl);
            }
            }
        } else {
            System.out.println("Failed to upload file");
        }
        connection.disconnect();
    }
}
