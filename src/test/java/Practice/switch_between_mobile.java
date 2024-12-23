package Practice;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class switch_between_mobile {
public static void main(String[] args) throws Throwable{
	DesiredCapabilities capabilities = new DesiredCapabilities();
	capabilities.setCapability("platformName", "Android");
	capabilities.setCapability("udid", "RZ8T50D55XW");
	capabilities.setCapability("appPackage", "org.telegram.messenger");
	capabilities.setCapability("appActivity", "org.telegram.messenger.DefaultIcon");
	capabilities.setCapability("noReset", "true");
	
	AppiumDriver driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
	
	/*
	 * DesiredCapabilities caps = new DesiredCapabilities();
	 * caps.setCapability("platformName", "Android"); caps.setCapability("udid",
	 * "R9ZRB071HJF"); caps.setCapability("appPackage", "com.eloelo");
	 * caps.setCapability("appActivity", "com.eloelo.HomeActivity");
	 * caps.setCapability("noReset", "true");
	 * 
	 * AppiumDriver driver1 = new AndroidDriver(new
	 * URL("http://localhost:4723/wd/hub"), caps);
	 */}
}
