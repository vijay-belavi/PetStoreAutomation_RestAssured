package TickPro;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class FetchLatestSmsWithDynamicWait {

    public String fetchLatestMessage(int waitTimeInSeconds, LocalDateTime executionStartTime) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String adbCommand = "adb shell content query --uri content://sms/inbox";
        String num = null;
        String messageBody = "";
        String secondDateAndTime = null;
        String formattedDateTime = "";

        long endTime = System.currentTimeMillis() + (waitTimeInSeconds * 1000);  // End time in milliseconds
        boolean messageReceived = false;

        // Loop to fetch the latest message within the user-defined wait time
        while (System.currentTimeMillis() < endTime) {
            Process process = Runtime.getRuntime().exec(adbCommand);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Row: 0")) {
                    num = line.replaceFirst("^Row: \\d+ ", "");
                }
            }

            if (num != null) {
                String[] keyValuePairs = num.split(", ");
                for (String pair : keyValuePairs) {
                    String[] keyValue = pair.split("=");
                    if (keyValue[0].equals("date")) {
                        secondDateAndTime = keyValue[1];
                    } else if (keyValue[0].equals("body")) {
                        messageBody = keyValue[1];
                    }
                }

                if (secondDateAndTime != null) {
                    Long seconds = Long.parseLong(secondDateAndTime);
                    Instant instant = Instant.ofEpochMilli(seconds);
                    LocalDateTime messageTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
                    formattedDateTime = messageTime.format(formatter);

                    // Check if the message time is after the execution start time
                    if (messageTime.isAfter(executionStartTime)) {
                        messageReceived = true;
                        System.out.println("Latest message received after execution start time at: " + formattedDateTime);
                        System.out.println("Message body: " + messageBody);
                        return messageBody;
                    }
                }
            }

            // Wait for 1 second before checking again (to avoid high CPU usage in the loop)
            Thread.sleep(1000);
        }

        // If no new message is found within the wait time, throw an exception
        if (!messageReceived) {
            throw new Exception("No new message received within the specified wait time of " + waitTimeInSeconds + " seconds.");
        }

        return null; // This line won't be reached, but it's needed to satisfy method signature
    }

    public static void main(String[] args) {
        FetchLatestSmsWithDynamicWait fetcher = new FetchLatestSmsWithDynamicWait();

        try {
            // Pass the wait time (in seconds) and the start time for execution
            LocalDateTime executionStartTime = LocalDateTime.now();
            System.out.println("Execution started at: " + executionStartTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

            // Dynamic wait time provided by user (e.g., 30 seconds)
            int userDefinedWaitTime = 10;

            // Fetch the latest message body
            String latestMessage = fetcher.fetchLatestMessage(userDefinedWaitTime, executionStartTime);
            if (latestMessage != null) {
                System.out.println("Fetched latest message: " + latestMessage);
            }
        } catch (Exception e) {
            // Handle the error, print the exception message
            e.printStackTrace();
        }
    }
}
