package Torus_1;

import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.ios.IOSDriver;

public class SliderIcon {
	public static void swipeSlider(IOSDriver driver, int percentage) {
	    // Locate the slider element
	    WebElement slider = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='Choose SIP Amount']/following-sibling::XCUIElementTypeOther")); // Update the locator as needed

	    // Get the location and size of the slider
	    int startX = (int) (slider.getLocation().getX() * 1.5);
	    int startY = slider.getLocation().getY();
	    int sliderWidth = slider.getSize().getWidth();
	    int endX = startX + sliderWidth;
	    int centerY = slider.getLocation().getY() + slider.getSize().getHeight() / 2;

	    // Ensure the percentage is within the valid range [0, 100]
	    if (percentage <= 0) {
	        throw new IllegalArgumentException("Percentage must be greater than 0.");
	    } else if (percentage > 100) {
	        throw new IllegalArgumentException("Percentage must be less than or equal to 100.");
	    }

	    // Calculate the target X coordinate based on the input percentage
	    int targetX = startX + (int) ((sliderWidth * percentage) / 100.0);

	    System.out.println("Start X: " + startX + ", End X: " + endX + ", Target X for " + percentage + "%: " + targetX + ", Center Y: " + centerY);

	    // Define the duration of the swipe
	    Duration duration = Duration.ofMillis(1000);

	    PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

	    // Create a sequence of actions
	    Sequence swipeSequence = new Sequence(finger, 0);

	    // Add actions to the sequence
	    swipeSequence.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), new Point(startX, centerY))); // Move to start position
	    swipeSequence.addAction(finger.createPointerDown(0)); // Press down to start
	    swipeSequence.addAction(finger.createPointerMove(duration, PointerInput.Origin.viewport(), new Point(targetX, centerY))); // Move to target position
	    swipeSequence.addAction(finger.createPointerUp(0));  // Release

	    // Perform the action
	    driver.perform(Arrays.asList(swipeSequence));
	    System.out.println("Swiped to " + percentage + "%");
	}

	// Usage example
	public static void main(String[] args) throws Throwable {
		
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("appium:platformName", "iOS");
		caps.setCapability("appium:platformVersion", "17.5.1"); // Update to match your iOS version
		caps.setCapability("appium:deviceName", "Ast");
		caps.setCapability("appium:automationName", "XCUITest"); // Path to your .app or .ipa file
		caps.setCapability("appium:udid", "00008110-000C45143C32401E"); // Path to your .app or .ipa file

		caps.setCapability("appium:bundleId", "com.google.Gmail"); // Path to your .app or .ipa file
		caps.setCapability("appium:agentPath",
				"/Applications/FireFlinkClient.app/Contents/Resources/flinko-client/exec/appium-webdriveragent/WebDriverAgent.xcodeproj");
		caps.setCapability("appium:bootstrapPath",
				"/Applications/FireFlinkClient.app/Contents/Resources/flinko-client/exec/appium-webdriveragent");

		
	    IOSDriver driver = new IOSDriver(new URL("http://localhost:4723/wd/hub"), caps);
	    Thread.sleep(5000);

	    swipeSlider(driver, 59); // Example usage with 59%
	    swipeSlider(driver, 0);   // Example usage with 0%
	    swipeSlider(driver, 150); // Example usage with 150% (out of bounds)
	}

}
