package Practice;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class FetchAllMessageFromInbox {
    public static void main(String[] args) {
        System.out.println("Inbox Details:");
        Map<String, String> inboxDetails = new HashMap<>();
        try {
            Process process = Runtime.getRuntime().exec("adb shell content query --uri content://sms/inbox");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            String key = null;
            String value = null;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    if (key != null && value != null) {
                        inboxDetails.put(key, value);
                    }
                    key = null;
                    value = null;
                } else {
                    if (key == null) {
                        String[] parts = line.split("=");
                        if (parts.length == 2) {
                            key = parts[0].trim();
                            value = parts[1].trim();
                        }
                    } else {
                        value += "\n" + line.trim();
                    }
                }
            }
            if (key != null && value != null) {
                inboxDetails.put(key, value);
            }
            process.waitFor();
            reader.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        for (Map.Entry<String, String> entry : inboxDetails.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}