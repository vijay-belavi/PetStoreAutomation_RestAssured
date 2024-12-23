package Torus_1;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class PressAndHoldAction2 {

	public static void performPressAndHold(AndroidDriver driver, WebElement element) throws InterruptedException {
        // Locate the element that you want to perform press and hold on
		
		  PointOption point = PointOption.point(element.getLocation().getX(),
		  element.getLocation().getY());
		  
		  // Create a TouchAction for press-and-hold TouchAction action = new
			/*
			 * // TouchAction(driver) .press(point)
			 * .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))) // Wait for 2
			 * seconds before releasing .release();
			 */
		  
		  // Perform the press-and-hold action action.perform();
		 
    	System.out.println("Time Start: ");
    	Thread.sleep(2000);
        // Capture the element value before it disappears
        WebElement dynamicElement = driver.findElement(By.xpath("//android.view.View[contains(@content-desc,'NAV')]"));  // Adjust selector accordingly
        String value = dynamicElement.getText();

        // Now you can use the value captured from the element
        System.out.println("Captured Value: " + value);

        // Optionally, handle any assertion or next steps
    }

	public static void main(String[] args) throws Throwable {
		try {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("platformName", "Android");

			AndroidDriver driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
		//	performPressAndHold(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
