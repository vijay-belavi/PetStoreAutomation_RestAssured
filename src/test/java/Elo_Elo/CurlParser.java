package Elo_Elo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CurlParser {

    public static void main(String[] args) {
        String curlCommand = "curl --location 'https://vote.eloelo.in/v3/get/live?page=0&versioncode=20003356&network=SyncApi&campaign=SyncAPI_91&adgroup=91111&adid=6bb6a14ab9a1386dcd39cbf32cf34201&install_timestamp=0&source=HP' \\\r\n"
        		+ "--header 'androidVersion: 30' \\\r\n"
        		+ "--header 'app-language-id: 1' \\\r\n"
        		+ "--header 'Authorization: eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiTE9HR0VEVVNFUiIsImNkIjoxNzA2MTk4NjI3Mjk1LCJzdWIiOiI3MTU1ODgyMSIsImV4cCI6MTcyNTE4NTU3N30.ZRrrz44IABSUv4ZiqqLt16bwuP8wUFKZ5YSpUIDMJWc' \\\r\n"
        		+ "--header 'clientTimeStamp: 1724929749994' \\\r\n"
        		+ "--header 'deviceId: f9c7fb43c386e53e' \\\r\n"
        		+ "--header 'eloAnalyticSessionId: 71558821_1724928278314' \\\r\n"
        		+ "--header 'iscanaryx: false' \\\r\n"
        		+ "--header 'iscnry: 0' \\\r\n"
        		+ "--header 'roomV2: true' \\\r\n"
        		+ "--header 'src: follow_pn_incoming_user' \\\r\n"
        		+ "--header 'versioncode: 20003356' \\\r\n"
        		+ "--header 'view-lang-id: 2' \\\r\n"
        		+ "--header 'x-auth-token: 46e94dd8217968f88407b62542444bc3'";
        String url = extractUrl(curlCommand);
        System.out.println("URL: " + url);

        // Extract Headers
        Map<String, String> headers = extractHeaders(curlCommand);
        System.out.println("Headers: " + headers);

        // Extract Data
        String data = extractData(curlCommand);
        System.out.println("Data: " + data);
    }

    // Method to extract URL
    private static String extractUrl(String curlCommand) {
        String urlPattern = "--location '(.*?)'";
        Pattern pattern = Pattern.compile(urlPattern);
        Matcher matcher = pattern.matcher(curlCommand);

        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    // Method to extract headers
    private static Map<String, String> extractHeaders(String curlCommand) {
        Map<String, String> headers = new HashMap<>();
        String headerPattern = "--header '(.*?): (.*?)'";
        Pattern pattern = Pattern.compile(headerPattern);
        Matcher matcher = pattern.matcher(curlCommand);

        while (matcher.find()) {
            headers.put(matcher.group(1).trim(), matcher.group(2).trim());
        }
        return headers;
    }

    // Method to extract data
    private static String extractData(String curlCommand) {
        String dataPattern = "--data '(.*?)'";
        Pattern pattern = Pattern.compile(dataPattern);
        Matcher matcher = pattern.matcher(curlCommand);

        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}