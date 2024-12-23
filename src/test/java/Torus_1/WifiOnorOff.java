package Torus_1;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class WifiOnorOff {
public static void main(String[] args) throws MalformedURLException {
	try {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");

		// Initialize the Appium driver
		AndroidDriver driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		boolean element = driver.findElement(By.xpath("//*[contains(@content-desc,'You are offline!')]")).isDisplayed();
		System.out.println(element);
		
	} catch (Exception e) {
		e.printStackTrace();
	}

}
}
