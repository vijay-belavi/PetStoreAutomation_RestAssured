package Elo_Elo;

import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class SwipeUpUsingWithPointerInput {

	public static void main(String[] args) throws Throwable {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("noReset", "true"); // Change as needed

		AppiumDriver driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
		String dynamicXpath = "//android.widget.TextView[@resource-id='com.eloelo:id/nameTxt' and contains(@text,'sona')]";
		try {
			test(driver, dynamicXpath);
		} catch (Exception e) {
			System.out.println("Failed To Swipe");
			e.printStackTrace();
		}

	}

	public static void swipe(AppiumDriver driver, int startX, int startY, int endX, int endY, Duration duration) {
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence swipe = new Sequence(finger, 1);

		swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
		swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		swipe.addAction(finger.createPointerMove(duration, PointerInput.Origin.viewport(), endX, endY));
		swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		driver.perform(Arrays.asList(swipe));
	}

	public static void test(AppiumDriver driver, String dynamicXpath) {
		Dimension dimensions = driver.manage().window().getSize();
		int width = dimensions.getWidth();
		int height = dimensions.getHeight();
		int startX = width / 2;
		int startY = (height * 3) / 4;
		int endX = width / 2;
		int endY = (height * 1) / 4;
		try {
			while (driver.findElement(By.xpath(dynamicXpath)).isDisplayed()) {
				swipe(driver, startX, startY, endX, endY, Duration.ofSeconds(1));
				if (driver.findElement(By.xpath(dynamicXpath)).isDisplayed()) {
					System.out.println("Element Is Displayed");
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("Entered Exception");
			swipe(driver, startX, startY, endX, endY, Duration.ofSeconds(1));
			test(driver, dynamicXpath);
			e.printStackTrace();
		}
	}
}