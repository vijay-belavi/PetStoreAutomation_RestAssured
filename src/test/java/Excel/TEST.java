package Excel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TEST {
public static void main(String[] args) throws Throwable{
	// Get the current date and time
    LocalDateTime currentDateTime = LocalDateTime.now();
    
    DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    // Format the current date and time as per the defined format
    String firstDateAndTime = currentDateTime.format(formatter1);
	
    System.out.println("Current Date And Time: "+firstDateAndTime);
    Thread.sleep(10000);
    String formattedDateTime = null;
	String adbCommand = "adb shell content query --uri content://sms/inbox";
    String num = null;
    String secondDateAndTime = null;
    String otpMessage = null;
    int count = 0;
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

        String formattedDateTime1 = dateTime.format(formatter1);
        System.out.println("Last Message Received Date and Time: "+formattedDateTime1);
        LocalDateTime currentDateAndTime = LocalDateTime.parse(firstDateAndTime, formatter1);
        LocalDateTime MessageReceivedTime = LocalDateTime.parse(formattedDateTime1, formatter1);

        if (currentDateAndTime.compareTo(MessageReceivedTime) < 0) {
        	while(count<=0) {
        		for (String pair : keyValuePairs) {
                    String[] keyValue = pair.split("=");
                    if (keyValue[0].equals("body")) {
    					otpMessage = keyValue[1];
    				}
        		}
            }
        	System.out.println("body: "+otpMessage);
        	System.out.println(currentDateAndTime.format(formatter1)+" is lesser than "+MessageReceivedTime.format(formatter1));
        } else {
        	System.out.println("Message Haven't Received Yet: "+otpMessage);
        	System.out.println(currentDateAndTime.format(formatter1)+" is greater than "+MessageReceivedTime.format(formatter1));
        }
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("Exception: "+e);
	}
}
}
