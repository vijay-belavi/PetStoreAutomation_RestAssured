package Torus_1;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class qwertyui {
	private AndroidDriver driver;

	public qwertyui(AndroidDriver driver) {
		this.driver = driver;
	}

	public static void main(String[] args) {
		try {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("platformName", "Android");

			// Initialize the Appium driver
			AndroidDriver driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			WebElement element = driver.findElement(By.xpath(
					"//android.widget.ImageView[@content-desc=\"Filters\"]/following-sibling::android.view.View/android.view.View/android.view.View[1]/android.widget.ImageView"));

			int midX = (int) (element.getSize().getWidth()/2);
			int midY = (int) (element.getSize().getHeight()/2);
			
			int endX = 0;
			int endY = 0;
			System.out.println("Tap on Coordinates");
			Actions actions = new Actions(driver);
			actions.moveToElement(element, 100, 350).click().perform();
			System.out.println(midX+" "+midY);
			
			
			Dimension screenSize = driver.manage().window().getSize();
	        int screenWidth = screenSize.getWidth();
	        int screenHeight = screenSize.getHeight();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
