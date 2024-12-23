package Practice;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class ElementResponseTime {

	public static void main(String[] args) throws Throwable{
    	DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("platformName", "Android");
		caps.setCapability("noReset", "true");
		caps.setCapability("autoGrantPermission", "true");

		AppiumDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
		
		
		  // Find the element you want to measure the response time for 
		WebElement
		  element = driver.findElement(By.id("yourElementId"));
		  
		  // Get the current system time before interacting with the element 
		long
		  startTime = System.currentTimeMillis();
		  
		  // Interact with the element (click, hover, etc.) element.click(); 
		// Example  action, replace with the action you want to measure
		  
		  // Get the current system time after interacting with the element 
		long
		  endTime = System.currentTimeMillis();
		  
		  // Calculate the response time in milliseconds 
		long responseTime = endTime -
		  startTime;
		  
		  // Print the response time
		  System.out.println("Response time of the element: " + responseTime +
		  " milliseconds");
		  
		  // Close the WebDriver driver.quit();
		 
		System.out.println("hello");
    }
}
