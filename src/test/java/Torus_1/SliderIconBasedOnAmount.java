package Torus_1;

import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class SliderIconBasedOnAmount {
	public static void swipeSlider(AndroidDriver driver, int value) {
		// Define the range
		int minValue = 0;
		int maxValue = 100000;

		// Validate the input value
		if (value < minValue || value > maxValue) {
			throw new IllegalArgumentException("Value must be between " + minValue + " and " + maxValue);
		}

		// Calculate the percentage based on the input value
		int percentage = (int) ((value - minValue) * 100.0 / (maxValue - minValue));

		// Locate the slider element
		WebElement slider = driver.findElement(By.xpath("//android.widget.SeekBar[contains(@content-desc,'%')]"));

		// Get the location and size of the slider
		int startX = slider.getLocation().getX(); // Starting X-coordinate
		int endX = startX + slider.getSize().getWidth(); // Ending X-coordinate
		int centerY = slider.getLocation().getY() + (slider.getSize().getHeight() / 2); // Center Y-coordinate

		// Calculate the target X coordinate
		int targetX = startX + (int) ((endX - startX) * (percentage / 100.0));

		System.out.println("Value: " + value + ", Percentage: " + percentage + "%");
		System.out.println("Start X: " + startX + ", Target X: " + targetX + ", Center Y: " + centerY);

		// Define the duration of the swipe
		Duration duration = Duration.ofMillis(1000);

		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

		// Create a sequence of actions
		Sequence swipeSequence = new Sequence(finger, 0);

		// Add actions to the sequence
		swipeSequence.addAction(
				finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), new Point(startX, centerY))); // Move
																														// to
																														// start
																														// position
		swipeSequence.addAction(finger.createPointerDown(0)); // Press down to start
		swipeSequence.addAction(
				finger.createPointerMove(duration, PointerInput.Origin.viewport(), new Point(targetX, centerY))); // Move
																													// to
																													// target
																													// position
		swipeSequence.addAction(finger.createPointerUp(0)); // Release

		// Perform the action
		driver.perform(Arrays.asList(swipeSequence));
		System.out.println("Swiped to " + value + " (corresponding to " + percentage + "%)");
	}

	// Usage example
	public static void main(String[] args) throws Throwable {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("platformName", "Android");

		AndroidDriver driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), caps);
		System.out.println("App Opened");

		Random ran = new Random();
		int num = ran.nextInt(100000);
		System.out.println(num);
		swipeSlider(driver, num); // Example usage with a value of 59,000
		// swipeSlider(driver, 0); // Example usage with a value of 0
		// swipeSlider(driver, 150000); // Example usage with an out-of-range value
		// (should throw an exception)
	}
}