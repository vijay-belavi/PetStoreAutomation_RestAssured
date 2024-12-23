package Excel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GetMessageCount {
public static void main(String[] args) {
	
	String key = null;
	String adbCommand = "adb shell content query --uri content://sms/inbox";
    String num = null;
    String keyValues = null;
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
            if (keyValue[0].equals(key)) {
                keyValues = keyValue[1];
                break; // Assuming duration is unique
            }
        }
        
	} catch (Exception e) {
		System.out.println("Exception: "+e);
	}
	System.out.println(keyValues);
}
}
