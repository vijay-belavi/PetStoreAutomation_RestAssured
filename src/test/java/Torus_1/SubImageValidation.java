package Torus_1;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class SubImageValidation {
	private static AndroidDriver driver;

	public static void main(String[] args) throws Throwable {
		// Set up DesiredCapabilities for Appium
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");

		// Initialize the AndroidDriver
		driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);

		// Find the element which contains the sub-image
		WebElement element = driver.findElement(By.xpath(
				"//android.widget.ImageView[@content-desc=\"Filters\"]/following-sibling::android.view.View/android.view.View/android.view.View[4]/android.widget.ImageView"));

		// Capture a screenshot of the element
		File screenshot = element.getScreenshotAs(OutputType.FILE);

		// Load the screenshot as a BufferedImage
		BufferedImage elementImage = ImageIO.read(screenshot);

		// Load the reference image (sub-image) you want to compare
		BufferedImage referenceImage = ImageIO
				.read(new File("C:\\Users\\User\\OneDrive\\Desktop\\New folder\\elementScreenshot.png"));

		// Validate if the element image contains the reference sub-image
		if (containsSubImage(elementImage, referenceImage)) {
			System.out.println("Sub-image found in the element.");
		} else {
			System.out.println("Sub-image not found in the element.");
		}

		// Close the driver session
		driver.quit();
	}

	// Method to compare if the element image contains the reference sub-image
	public static boolean containsSubImage(BufferedImage elementImage, BufferedImage referenceImage) {
		int width = elementImage.getWidth();
		int height = elementImage.getHeight();
		int subImageWidth = referenceImage.getWidth();
		int subImageHeight = referenceImage.getHeight();

		// Check all possible positions where the sub-image could be inside the element
		// image
		for (int x = 0; x < width - subImageWidth; x++) {
			for (int y = 0; y < height - subImageHeight; y++) {
				boolean match = true;

				// Check if the sub-image matches at the (x, y) position
				for (int i = 0; i < subImageWidth; i++) {
					for (int j = 0; j < subImageHeight; j++) {
						if (elementImage.getRGB(x + i, y + j) != referenceImage.getRGB(i, j)) {
							match = false;
							break;
						}
					}
					if (!match)
						break;
				}

				if (match)
					return true; // Sub-image found at this position
			}
		}

		return false; // No match found
	}
}
