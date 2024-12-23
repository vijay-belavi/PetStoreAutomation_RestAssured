package Torus_1;

import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class CompareMonth {
	public static void main(String[] args) throws Throwable {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");

		// Initialize the Appium driver
		AndroidDriver driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		String userMonth = "December";
		try {

			// Xpath for IOS
			// XCUIElementTypeStaticText[@name="{Dynamic Val1}"]
			WebElement element = driver.findElement(By.xpath("//android.widget.TextView[@text='" + userMonth + "']")); // Replace
																														// with
			Dimension deviceSize = driver.manage().window().getSize();
			int deviceHeight = deviceSize.getHeight();

			// Define threshold (3/4th of the device height)
			int thresholdY = deviceHeight * 3 / 4;

			// Fetch element coordinates
			Point elementLocation = element.getLocation();
			int elementY = elementLocation.getY();

			// Compare element coordinates with the threshold
			Month givenMonth = Month.valueOf(userMonth.toUpperCase(Locale.ENGLISH));

			// Get the current month
			Month currentMonth = LocalDate.now().getMonth();

			// Compare the given month with the current month
			if (givenMonth.getValue() > currentMonth.getValue()) {
				System.out.println(givenMonth.getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " is after "
						+ currentMonth.getDisplayName(TextStyle.FULL, Locale.ENGLISH));
				while (elementLocation.getY() > thresholdY) {
					swipeUp(driver);
					System.out.println(element.getLocation().getY());
					if (element.getLocation().getY() > thresholdY) {
						break;
					}
				}

			} else if (givenMonth.getValue() < currentMonth.getValue()) {
				System.out.println(givenMonth.getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " is before "
						+ currentMonth.getDisplayName(TextStyle.FULL, Locale.ENGLISH));
				while (element.getLocation().getY() < thresholdY) {
					swipeDown(driver);
					if (element.getLocation().getY() > thresholdY) {
						break;
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			driver.findElements(By.xpath(""));
		}
	}

	public static void swipeUp(AndroidDriver driver) {
		Dimension deviceSize = driver.manage().window().getSize();
		int startX = deviceSize.getWidth() / 2; // Center X
		int startY = deviceSize.getHeight() * 3 / 4; // Start Y
		int endX = startX; // Center X
		int endY = deviceSize.getHeight() / 4; // End Y

		Duration duration = Duration.ofSeconds(1);
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence swipe = new Sequence(finger, 1);

		swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
		swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		swipe.addAction(finger.createPointerMove(duration, PointerInput.Origin.viewport(), endX, endY));
		swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		driver.perform(Arrays.asList(swipe)); // Execute the swipe action

	}

	public static void swipeDown(AndroidDriver driver) {
		Dimension deviceSize = driver.manage().window().getSize();
		int startX = deviceSize.getWidth() / 2; // Center X
		int startY = deviceSize.getHeight() * 3 / 4; // Start Y
		int endX = startX; // Center X
		int endY = deviceSize.getHeight() * 7 / 8; // End Y

		Duration duration = Duration.ofSeconds(1);
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence swipe = new Sequence(finger, 1);

		swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
		swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		swipe.addAction(finger.createPointerMove(duration, PointerInput.Origin.viewport(), endX, endY));
		swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		driver.perform(Arrays.asList(swipe)); // Execute the swipe action
	}
}
