package Torus_1;

import java.net.URL;
import java.time.Duration;
import java.util.Collections;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.PointerInput.Origin;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class TapOnXcoordinateYcoordinate {
	public static void main(String[] args) throws Throwable {
		int x = 0;
		int y = 0;
		try {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("platformName", "Android");

			AndroidDriver driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			WebElement element = driver.findElement(By.xpath(
					"//android.widget.ImageView[@content-desc='Filters']/following-sibling::android.view.View/android.view.View/android.view.View[1]/android.widget.ImageView"));
			x = (int) ((element.getLocation().getX() + element.getSize().getWidth() * 0.16));
			y = (int) ((element.getLocation().getY() + element.getSize().getHeight() * 0.38));
			
			

			System.out.println(x + " " + y);

			// Create a PointerInput instance for touch actions
			PointerInput touchInput = new PointerInput(PointerInput.Kind.TOUCH, "finger1");

			// Create a tap sequence at the given coordinates (x, y)
			Sequence tapSequence = new Sequence(touchInput, 0);
			tapSequence.addAction(touchInput.createPointerMove(Duration.ofMillis(0), Origin.viewport(), x, y));
			tapSequence.addAction(touchInput.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
			tapSequence.addAction(touchInput.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

			// Perform the sequence
			driver.perform(Collections.singletonList(tapSequence));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
