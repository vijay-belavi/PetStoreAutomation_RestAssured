package MO_Trader;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;


public class DragAndDrop {

    public static void main(String[] args) throws Throwable{
    	
    	DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("platformName", "Android");
		caps.setCapability("appPackage", "mosl.powerapp.com.uat");
		caps.setCapability("appActivity", "mosl.powerapp.com.MainActivity");
		caps.setCapability("noReset", "true");
		caps.setCapability("autoGrantPermission", "true");
    	
    	
        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);

        // Find the source element.
        WebElement sourceElement = driver.findElement(By.xpath("com.example.app:id/source"));

        // Find the target element.
        WebElement targetElement = driver.findElement(By.xpath("com.example.app:id/target"));

		/*
		 * // Create a new PointerInput object. PointerInput pointerInput = new
		 * PointerInput(driver);
		 * 
		 * // Create a new Sequence object. Sequence sequence = new
		 * Sequence(pointerInput, 0);
		 * 
		 * // Add a drag gesture to the sequence.
		 * sequence.add(pointerInput.down(sourceElement));
		 * sequence.add(pointerInput.moveTo(targetElement));
		 * sequence.add(pointerInput.up(targetElement));
		 * 
		 * // Perform the sequence. sequence.perform();
		 */
    }
}
