package MO_Trader;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class AndroidSwipeUsingPointerInput {
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("platformName", "Android");
		caps.setCapability("appPackage", "mosl.powerapp.com.uat");
		caps.setCapability("appActivity", "mosl.powerapp.com.MainActivity");
		caps.setCapability("noReset", "true");
		caps.setCapability("autoGrantPermission", "true");

		AppiumDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);

		Thread.sleep(25000);

		try {
			List<WebElement> elements = driver.findElements(By.xpath(
					"//android.view.View[contains(@content-desc,'IAP')]/android.view.View/android.view.View/android.view.View"));
			ArrayList array = new ArrayList();
			for (int i = 0; i < 3; i++) {

				for (WebElement webElement : elements) {
					String elementAttribute = webElement.getAttribute("content-desc");
					System.out.println(elementAttribute);
					array.add(elementAttribute);
					Thread.sleep(5000);
				}
			}
			System.out.println(array);
			System.out.println(array.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}