package Torus_1;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class ScreenshotCapture3 {

	public static void captureScreenshotRegion(AndroidDriver driver, int x, int y, int width, int height,
			String outputFilePath) throws IOException {
		// Step 1: Take a full screenshot
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		// Step 2: Read the screenshot file into a BufferedImage
		BufferedImage fullImage = ImageIO.read(screenshotFile);

		// Step 3: Crop the image based on the given coordinates and dimensions
		BufferedImage croppedImage = fullImage.getSubimage(x, y, x, y);

		// Step 4: Save the cropped image to the specified output path
		ImageIO.write(croppedImage, "PNG", new File(outputFilePath));

		System.out.println("Cropped screenshot saved to: " + outputFilePath);
	}

	public static void main(String[] args) throws Throwable {
		try {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("platformName", "Android");

			// Initialize the Appium driver
			AndroidDriver driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			WebElement element = driver.findElement(By.xpath(
					"//android.widget.ImageView[@content-desc=\"Filters\"]/following-sibling::android.view.View/android.view.View/android.view.View[1]/android.widget.ImageView"));

			// Coordinates (x, y) and size (width, height) of the region to capture
			int x = (int) (element.getLocation().getX() + element.getSize().getWidth() * 0.04);
			int y = (int) (element.getLocation().getY() + element.getSize().getHeight() * 0.13); // Y-coordinate
			int width = (int) (element.getLocation().getX() + element.getSize().getWidth() * 0.16); // Width of the
																									// region
			int height = (int) (element.getLocation().getY() + element.getSize().getHeight() * 0.38); // Height of the

			// Path to save the cropped screenshot
			String outputFilePath = "C:\\Users\\User\\OneDrive\\Desktop\\New folder\\NewImage.png";

			// Step 3: Capture and crop screenshot
			captureScreenshotRegion(driver, x, y, width, height, outputFilePath);

			// Close the driver
			driver.quit();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
