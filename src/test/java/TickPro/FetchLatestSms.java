package TickPro;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class FetchLatestSms {

    public static void main(String[] args) {
        try {
            // Build the ADB command
            String command = "adb shell content query --uri content://sms/inbox --projection --sort DESC";

            // Execute the command
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor(5, TimeUnit.SECONDS);

            // Read the output
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            StringBuilder output = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            // Check if output is empty
            if (output.length() > 0) {
                System.out.println("Latest SMS Details:\n" + output.toString());
            } else {
                System.out.println("No SMS details found or ADB command did not execute successfully.");
            }

            // Close resources
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}