package Torus_1;

import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class SwipeUpUntilElementIsVisibleWithinScreen {
    public static void main(String[] args) {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("platformName", "Android");

            // Initialize the Appium driver
            AndroidDriver driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            int count = 0;
            String message = "";
            String xpath = "//android.widget.ImageView[contains(@content-desc,'Old Bridge Asset Management Pvt Ltd')]"; // Replace with your element's XPath
            if (driver != null) {
                try {
                    boolean elementFound = false;

                    // Screen dimensions
                    Dimension dimensions = driver.manage().window().getSize();
                    int screenHeight = dimensions.getHeight();

                    // Keep trying for 5 swipes
                    while (count < 5) {
                        List<WebElement> elements = driver.findElements(By.xpath(xpath));

                        // Check if element is displayed and within 90% of the screen height
                        if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
                            int elementY = elements.get(0).getLocation().getY();
                            if (elementY <= (screenHeight * 0.8)) {
                                elementFound = true;
                                message = "Element found within visible screen after " + count + " swipes.";
                                break;
                            }
                        }

                        // Perform the swipe if element not found or out of visible screen
                        swipeUpAndroid(driver);
                        count++;
                    }

                    // If element is still not found after 5 swipes, throw an exception
                    if (!elementFound) {
                        message = "Element not found within visible screen after 5 swipe attempts.";
                        throw new Exception(message);
                    }

                } catch (Exception e) {
                    message = message + ": " + e.getMessage();
                } finally {
                    System.out.println(message);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to perform swipe action using PointerInput
    public static void swipeUpIos(IOSDriver driver) {
        try {
            // Get screen dimensions
            Dimension dimensions = driver.manage().window().getSize();
            int width = dimensions.getWidth();

            // Define the starting and ending Y coordinates for the swipe
            int startY = (int) (dimensions.getHeight() * 0.5);
            int endY = (int) (dimensions.getHeight() * 0.2);
            int startX = (int) (width / 2); // Center of the screen (X coordinate)

            // Create PointerInput instance to simulate touch action
            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence swipe = new Sequence(finger, 0);

            // Perform swipe up action
            swipe.addAction(
                    finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY));
            swipe.addAction(finger.createPointerDown(0));
            swipe.addAction(
                    finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), startX, endY));
            swipe.addAction(finger.createPointerUp(0));

            // Execute the swipe action
            driver.perform(List.of(swipe));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to perform swipe action using PointerInput
    public static void swipeUpAndroid(AndroidDriver driver) {
        try {
            // Get screen dimensions
            Dimension dimensions = driver.manage().window().getSize();
            int width = dimensions.getWidth();

            // Define the starting and ending Y coordinates for the swipe
            int startY = (int) (dimensions.getHeight() * 0.5);
            int endY = (int) (dimensions.getHeight() * 0.2);
            int startX = (int) (width / 2); // Center of the screen (X coordinate)

            // Create PointerInput instance to simulate touch action
            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence swipe = new Sequence(finger, 0);

            // Perform swipe up action
            swipe.addAction(
                    finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY));
            swipe.addAction(finger.createPointerDown(0));
            swipe.addAction(
                    finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), startX, endY));
            swipe.addAction(finger.createPointerUp(0));

            // Execute the swipe action
            driver.perform(List.of(swipe));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
