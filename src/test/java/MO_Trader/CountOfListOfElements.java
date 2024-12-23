package MO_Trader;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class CountOfListOfElements {
	public static void main(String[] args) throws Throwable {
		ArrayList arr = new ArrayList();
		for (int i = 0; i < arr.size(); i++) {
			Object index = arr.get(i);
		}

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("appPackage", "mosl.powerapp.com.uat");
		capabilities.setCapability("appActivity", "mosl.powerapp.com.MainActivity");
		capabilities.setCapability("noReset", "true");

		AppiumDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		Thread.sleep(30000);

		List<WebElement> elements = driver.findElements(By.xpath(
				"//android.view.View[contains(@content-desc,'IAP')]/android.view.View/android.view.View/android.view.View"));
		System.out.println(elements.size());
	}
}