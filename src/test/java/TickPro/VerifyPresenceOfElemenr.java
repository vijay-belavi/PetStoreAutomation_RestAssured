package TickPro;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class VerifyPresenceOfElemenr {
public static void main(String[] args) throws Throwable{
	// Set the desired capabilities for your Android WebDriver
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("platformName", "Android");
			caps.setCapability("appPackage", "rs.reliance.com.screen");
			caps.setCapability("appActivity", "rs.reliancerest.com.screen.Main");
			caps.setCapability("noReset", "true");
			
			// Initialize the AndroidDriver
			AppiumDriver driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), caps);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			Thread.sleep(5000);
			String androidXPath = "//android.widget.ImageView[@resource-id='rs.reliance.com.screen:id/imgHomeMain']";
			String iosXpath = "//XCUIElementTypeButton[@name='home']";
			String b = "false";
			String platformName = caps.getCapability("platformName").toString();
			System.out.println(platformName);
			try {
					if (driver.findElement(By.xpath(androidXPath)).isDisplayed()) {
						b = "ANDROID true";
					}
					else if(driver.findElement(By.xpath(iosXpath)).isDisplayed()) {
						b = "IOS true";
					}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			System.out.println(b);
}
}
