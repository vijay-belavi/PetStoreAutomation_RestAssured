package MO_Trader;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.PointOption;

public class DragAndDropCoordinates {
    public static void main(String[] args) {
        // Set desired capabilities for the Android device and app
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "device_name");
        capabilities.setCapability("appPackage", "app_package_name");
        capabilities.setCapability("appActivity", "app_activity_name");

        // Initialize AndroidDriver instance
        AndroidDriver driver;
        try {
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

            // Coordinates of source and target elements
            int sourceX = 100; // Replace with actual x-coordinate
            int sourceY = 200; // Replace with actual y-coordinate
            int targetX = 300; // Replace with actual x-coordinate
            int targetY = 400; // Replace with actual y-coordinate

            
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}