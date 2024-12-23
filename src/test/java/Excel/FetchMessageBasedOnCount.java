package Excel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class FetchMessageBasedOnCount {
public static void main(String[] args) {
	
	LocalDateTime currentDateTime = LocalDateTime.now();

    // Define the format for displaying date and time
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Format the current date and time as per the defined format
    String currentDateAndTime = currentDateTime.format(formatter);

	DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	String formattedDateTime = null;
	String adbCommand = "adb shell content query --uri content://sms/inbox";
    String num = null;
    String secondDateAndTime = null;
    String formattedDateTime1 = " ";
    String otpMessage = "";
	try {
		
		Process process = Runtime.getRuntime().exec(adbCommand);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
        	if(line.contains("Row: 0")) {
        		num = line.replaceFirst("^Row: \\d+ ", "");
        	}
        }
        String[] keyValuePairs = num.split(", ");
        for (String pair : keyValuePairs) {
            String[] keyValue = pair.split("=");
            if (keyValue[0].equals("date")) {
				secondDateAndTime = keyValue[1];
			}
        }
        
        Long seconds = Long.parseLong(secondDateAndTime);
        Instant instant = Instant.ofEpochMilli(seconds);
        
        // Convert Instant to LocalDateTime in default time zone
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

        formattedDateTime1 = dateTime.format(formatter1);
        
        LocalDateTime currentTimeDate = LocalDateTime.parse(currentDateAndTime, formatter1);
        LocalDateTime secondTimeDate = LocalDateTime.parse(formattedDateTime1, formatter1);
        
        	for (String pair : keyValuePairs) {
                String[] keyValue = pair.split("=");
                if (keyValue[0].equals("body")) {
					otpMessage = keyValue[1];
				}
        	}
	}
	 catch (Exception e) {
		e.printStackTrace();
	}
	System.out.println(otpMessage);
}
}
