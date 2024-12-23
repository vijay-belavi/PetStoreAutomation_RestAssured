package Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class ADBExecutor {

    public static void main(String[] args) throws Throwable{
    	
        // Replace this with your actual ADB command
        String adbCommand = "adb shell content query --uri content://call_log/calls";
        String num = null;
        try {
            Process process = Runtime.getRuntime().exec(adbCommand);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
            	if(line.contains("Row: 0")) {
            		num = line.replaceFirst("^Row: \\d+ ", "");
            	}
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String key = "normalized_number";
        String duration = null;
        String[] keyValuePairs = num.split(", ");
        for (String pair : keyValuePairs) {
            String[] keyValue = pair.split("=");
            if (keyValue[0].equals(key)) {
                duration = keyValue[1];
                break; // Assuming duration is unique
            }
        }
        System.out.println("normalized_number: " + duration);
    }
    
}