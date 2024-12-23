package Excel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Fetch_Latest_Message {
	public static void main(String[] args) throws Throwable{
		
		String adbCommand = "adb shell content query --uri content://sms/inbox";
        String num = null;
        String size = null;
        ArrayList<String> list = new ArrayList();
		try {
			Process process = Runtime.getRuntime().exec(adbCommand);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
            		num = line.toString();
            }
            String[] keyValuePairs = num.split(", ");
            for (String pair : keyValuePairs) {
                String[] keyValue = pair.split("=");
                if (keyValue[0].equals("date")) {
                	System.out.println(keyValue[1]);
                    list.add(keyValue[1]);
                }
            }
		}
		catch(Exception e) {
			
		}
}
}