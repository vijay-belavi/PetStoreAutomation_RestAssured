package TickPro;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class LogintoApp {
	public static void main(String[] args) throws Throwable {
		// Set the desired capabilities for your Android WebDriver
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("platformName", "Android");
		caps.setCapability("appPackage", "rs.reliance.com.screen");
		caps.setCapability("appActivity", "rs.reliancerest.com.screen.Main");
		caps.setCapability("noReset", "true");

		// Initialize the AndroidDriver
		AppiumDriver driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), caps);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		driver.findElement(By.xpath("//android.widget.ImageView[@resource-id='rs.reliance.com.screen:id/imgHomeMain']"))
				.click();
		/*
		 * driver.findElement(By.id("rs.reliance.com.screen:id/etLoginIDL")).sendKeys(
		 * "RS8883");
		 * driver.findElement(By.id("rs.reliance.com.screen:id/btnValidateL")).click();
		 * driver.findElement(By.id("rs.reliance.com.screen:id/etPwdL")).sendKeys(
		 * "Vijay@123");
		 * driver.findElement(By.id("rs.reliance.com.screen:id/btnValidateL")).click();
		 */
		driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'Fund Transfer')]")).click();
		WebElement element = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='rs.reliance.com.screen:id/tvTitleMH']"));
		String attributeValue = element.getAttribute("text");
		System.out.println(attributeValue);
		if (attributeValue.contains("Limits")) {
			System.out.println("Hello");
		} else {
			System.out.println("Hi");
		}
	}
}
