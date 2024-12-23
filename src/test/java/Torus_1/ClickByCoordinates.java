package Torus_1;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.PointOption;

public class ClickByCoordinates {

    public static void main(String[] args) throws Exception {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android"); // Change to "iOS" for iOS devices

        AndroidDriver driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), caps);
        // Use IOSDriver for iOS
        // RemoteWebDriver driver = new IOSDriver(new URL("http://localhost:4723/wd/hub"), caps);

        // Locate the element using a generic locator strategy
		WebElement element = driver.findElement(By.xpath("//android.view.View[@resource-id='hv-form-checkbox-tick-derivativesCheckbox']"));  // Replace with your locator

        // Get the element’s position and size
        Point elementLocation = element.getLocation();
        Dimension elementSize = element.getSize();

        // Calculate the center of the element
        int elementCenterX = elementLocation.getX() + (elementSize.getWidth() / 2);
        int elementCenterY = elementLocation.getY() + (elementSize.getHeight() / 2);

        // Get the screen size to normalize the coordinates
        Dimension screenSize = driver.manage().window().getSize();
        int screenWidth = screenSize.getWidth();
        int screenHeight = screenSize.getHeight();

        // Normalize the coordinates (relative to the screen size)
        double normalizedX = (double) elementCenterX / screenWidth;
        double normalizedY = (double) elementCenterY / screenHeight;

        // Convert normalized coordinates back to actual device coordinates
        int tapX = (int) (normalizedX * screenWidth);
        int tapY = (int) (normalizedY * screenHeight);

        // Perform tap action using TouchAction
        TouchAction<?> touchAction = new TouchAction(driver);
        touchAction.tap(TapOptions.tapOptions().withPosition(PointOption.point(tapX, tapY))).perform();

        // Close the driver
        driver.quit();
    }
}
