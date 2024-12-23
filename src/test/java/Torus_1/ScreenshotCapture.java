package Torus_1;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class ScreenshotCapture {
	private AndroidDriver driver;

	public ScreenshotCapture(AndroidDriver driver) {
		this.driver = driver;
	}

	public void captureScreenshotRegion(int x, int y, int width, int height, String outputFilePath) throws IOException {
		// Step 1: Capture screenshot of the specific element
		WebElement element = driver.findElement(By.xpath(
				"//android.widget.ImageView[@content-desc=\"Filters\"]/following-sibling::android.view.View/android.view.View/android.view.View[1]/android.widget.ImageView"));
		File screenshotFile = element.getScreenshotAs(OutputType.FILE);

		// Step 2: Read the screenshot file into a BufferedImage
		BufferedImage fullImage = ImageIO.read(new ByteArrayInputStream(element.getScreenshotAs(OutputType.BYTES)));

		// Step 3: Ensure that the cropping area is within bounds of the image
		int imgWidth = fullImage.getWidth();
		int imgHeight = fullImage.getHeight();

		// Adjust width and height if they go out of bounds
		if (x + width > imgWidth) {
			width = imgWidth - x;
		}
		if (y + height > imgHeight) {
			height = imgHeight - y;
		}

		// Ensure the cropping area is valid (x, y should be within bounds)
		if (x < 0)
			x = 0;
		if (y < 0)
			y = 0;

		// Step 4: Crop the image based on the given coordinates and dimensions
		BufferedImage croppedImage = fullImage.getSubimage(x, y, width, height);

		// Step 5: Save the cropped image to the specified output path
		ImageIO.write(croppedImage, "PNG", new File(outputFilePath));

		System.out.println("Cropped screenshot saved to: " + outputFilePath);
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
			// Coordinates (x, y) and size (width, height) of the region to capture
			int x = (int) (element.getLocation().getX() + element.getSize().getWidth() * 0.04);
			int y = (int) (element.getLocation().getY() + element.getSize().getHeight() * 0.13); // Y-coordinate
			int width = (int) (element.getLocation().getX() + element.getSize().getWidth() * 0.16); // Width of the
																									// region
			int height = (int) (element.getLocation().getY() + element.getSize().getHeight() * 0.38); // Height of the
																										// region
			System.out.println(x + " " + y + " " + width + " " + height);
			// Path to save the cropped screenshot
			String outputFilePath = "C:\\Users\\User\\OneDrive\\Desktop\\New folder\\elementScreenshot.png";

			// Capture and crop screenshot
			ScreenshotCapture screenshotCapture = new ScreenshotCapture(driver);
			screenshotCapture.captureScreenshotRegion(x, y, width, height, outputFilePath);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
