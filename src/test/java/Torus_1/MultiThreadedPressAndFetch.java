package Torus_1;

import java.net.URL;
import java.util.concurrent.CountDownLatch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.ios.IOSDriver;

public class MultiThreadedPressAndFetch {
    public static void main(String[] args) throws Throwable{
    	
    	 // Initialize Appium driver
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "iOS");
        caps.setCapability("deviceName", "iPhone 14 Pro"); // Replace with your device name
        caps.setCapability("udid", "YOUR_DEVICE_UDID"); // Use the same UDID
        caps.setCapability("automationName", "XCUITest");
        caps.setCapability("app", "/path/to/your/app"); // Replace with the path to your app

        IOSDriver driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);

        // CountDownLatch to ensure synchronization
        CountDownLatch latch = new CountDownLatch(1);

        // Thread for Press-and-Hold Action
        Thread pressAndHoldThread = new Thread(() -> {
            try {
                String deviceUdid = "YOUR_DEVICE_UDID"; // Replace with your device's UDID
                int x = 200; // Replace with your desired X-coordinate
                int y = 300; // Replace with your desired Y-coordinate
                String holdCommand = String.format("xcrun simctl io %s touch-and-hold %d %d", deviceUdid, x, y);

                System.out.println("Performing press-and-hold at coordinates: " + x + ", " + y);
                Process process = Runtime.getRuntime().exec(holdCommand);

                // Wait for the command to complete
                int exitCode = process.waitFor();
                if (exitCode == 0) {
                    System.out.println("Press-and-hold action completed successfully.");
                } else {
                    System.out.println("Press-and-hold action failed. Exit code: " + exitCode);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // Signal that press-and-hold is complete
                latch.countDown();
            }
        });

        // Thread for Fetching Attribute Value
        Thread fetchAttributeThread = new Thread(() -> {
            try {
                // Wait for the press-and-hold thread to complete
                latch.await();

                // Fetch attribute value
                WebElement element = driver.findElement(By.xpath("")); // Replace with your locator
                String attributeValue = element.getAttribute("value"); // Replace 'value' with your desired attribute
                System.out.println("Fetched Attribute Value: " + attributeValue);

                // Quit the driver
                driver.quit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Start both threads
        pressAndHoldThread.start();
        fetchAttributeThread.start();

        // Wait for both threads to complete
        try {
            pressAndHoldThread.join();
            fetchAttributeThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Both actions completed.");
    }
}
