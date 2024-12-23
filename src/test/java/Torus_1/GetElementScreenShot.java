package Torus_1;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class GetElementScreenShot {

	public static void captureElementScreenshotAndFetchRGB(WebElement element, String saveDirectory) {
		try {
			// Capture the element screenshot and save it to a file
			File elementScreenshotFile = element.getScreenshotAs(OutputType.FILE);
			File savedElementScreenshotFile = new File(saveDirectory + "/MainImage.png");
			FileHandler.copy(elementScreenshotFile, savedElementScreenshotFile);
			System.out.println("Element screenshot saved as: " + savedElementScreenshotFile.getAbsolutePath());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Throwable {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");

		// Initialize the Appium driver
		AndroidDriver driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Locate the element
		WebElement element = driver.findElement(
				By.xpath("(//android.view.View[@content-desc='Mutual Fund']/../../following-sibling::android.view.View/android.view.View/android.view.View/android.widget.ImageView)[1]"));

		// Specify the directory to save the screenshots
		String saveDirectory = "C:\\Users\\User\\Downloads"; // Update this to your desired
																					// directory path

		captureElementScreenshotAndFetchRGB(element, saveDirectory);

		// Close the driver after use
		driver.quit();
	}
}
