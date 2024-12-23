package Torus_1;

import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class PressAndHoldAction {

	public static void performPressAndHold(AndroidDriver driver) {
		// Create a thread to perform the press-and-hold action in parallel
		Thread pressAndHoldThread = new Thread(() -> {
			try {
				// Locate the position for the press-and-hold action
				PointOption point = PointOption.point(500, 1000);
				// Create a TouchAction for press-and-hold
				TouchAction action = new TouchAction(driver).press(point)
						.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))) ;// Wait for 2 seconds before
																					// releasing
						

				// Perform the press-and-hold action
				action.perform();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		// Create a thread to capture the element value while the press-and-hold is
		// happening
		Thread captureElementValueThread = new Thread(() -> {
			try {
				// Wait for the press-and-hold to have some effect before capturing the element
				Thread.sleep(1000); // Adjust this sleep time based on your app's behavior

				// Capture the element's value while the press-and-hold is in progress
				WebElement dynamicElement = driver
						.findElement(By.xpath("//android.view.View[contains(@content-desc,'NAV')]"));
				String value = dynamicElement.getAttribute("content-desc");

				// Print captured value
				System.out.println("Captured Value: " + value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		// Start both threads
		pressAndHoldThread.start();
		captureElementValueThread.start();

		try {
			// Ensure both threads complete before proceeding
			pressAndHoldThread.join();
			captureElementValueThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Throwable {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");

		AndroidDriver driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
		//performPressAndHold(driver);
		
		 int x = 500;
	        int y = 1000;

	        // Create a PointerInput instance for touch interactions
	        PointerInput pointer = new PointerInput(PointerInput.Kind.TOUCH, "finger");

	        // Create a sequence for press-and-hold
	        Sequence pressAndHoldSequence = new Sequence(pointer, 0);

	        // Move to the element's position
	        pressAndHoldSequence.addAction(pointer.createPointerMove(Duration.ofMillis(200), PointerInput.Origin.viewport(), x, y));

	        // Press down on the element (start the press and hold)
	        pressAndHoldSequence.addAction(pointer.createPointerDown(0));

	        // Execute the press-and-hold action
	        driver.perform(Arrays.asList(pressAndHoldSequence));

	        // Wait for the desired duration (e.g., hold for 2 seconds)
	        try {
	            Thread.sleep(2000);  // Adjust this duration based on the required hold time
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

	        // Release the press
	        pressAndHoldSequence.addAction(pointer.createPointerUp(0));
	        driver.perform(Arrays.asList(pressAndHoldSequence));

	        // Now retrieve the dynamic element after the hold action
	        WebElement dynamicElement = driver
	                .findElement(By.xpath("//android.view.View[contains(@content-desc,'NAV')]"));

	        // Get and print the text of the element
	        String value = dynamicElement.getAttribute("content-desc");
	        System.out.println("Dynamic element content-desc: " + value);
	    
	}
}
