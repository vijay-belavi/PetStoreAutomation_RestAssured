package Practice;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.TimeZone;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class TimeZoneExample {

    public static void main(String[] args) throws Throwable{
    	
    	DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("platformName", "Android");
		caps.setCapability("noReset", "true");
		caps.setCapability("autoGrantPermission", "true");

		AppiumDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
		
        
        String timeZone = "Africa/Accra";
        try {
            // Construct the ADB command to change the device's time zone
            String adbCommand = "adb shell settings put system time_zone " + timeZone;

            // Execute the ADB command
            Process process = Runtime.getRuntime().exec(adbCommand);

            // Read the output of the command for debugging purposes
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Wait for the process to finish
            process.waitFor();

            // Close the reader
            reader.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }}
}
