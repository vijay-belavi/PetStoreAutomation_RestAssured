package Torus_1;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.time.Duration;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class CaptureScreenshot {
	public static void main(String[] args) throws Exception {

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");

		// Initialize the Appium driver
		AndroidDriver driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Define the coordinates for the region you want to capture

		WebElement element = driver.findElement(By.xpath(
				"//android.widget.ImageView[@content-desc='Filters']/following-sibling::android.view.View/android.view.View/android.view.View[2]/android.widget.ImageView"));

		int x = (int) (element.getLocation().getX() + element.getSize().getWidth() * 0.04);
		int y = (int) (element.getLocation().getY() + element.getSize().getHeight() * 0.115); // Y-coordinate
		int width = (int) (element.getLocation().getX() + element.getSize().getWidth() * 0.155); // Width of the
																								// region
		int height = (int) (element.getLocation().getY() + element.getSize().getHeight() * 0.38); // Height of the

		// Capture the screenshot of the entire screen first
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		// Now, crop the screenshot to the defined region
		BufferedImage fullImg = ImageIO.read(screenshot);
		BufferedImage croppedImg = fullImg.getSubimage(x, y, width - x, height - y);

		// Save the cropped screenshot
		File outputfile = new File("C:\\Users\\User\\OneDrive\\Desktop\\New folder\\NewImage.png");
		ImageIO.write(croppedImg, "png", outputfile);

		System.out.println("First Screenshot saved successfully.");
		Thread.sleep(5000);

		WebElement element1 = driver.findElement(By.xpath("//android.widget.ImageView[contains(@content-desc,'%')]"));

		//// android.widget.ImageView[contains(@content-desc,'%')]
		int x1 = (int) (element1.getLocation().getX() + element1.getSize().getWidth() * 0.05);
		int y1 = (int) (element1.getLocation().getY() + element1.getSize().getHeight() * 0.035); // Y-coordinate
		int width1 = (int) (element1.getLocation().getX() + element1.getSize().getWidth() * 0.165); // Width of the
																									// region
		int height1 = (int) (element1.getLocation().getY() + element1.getSize().getHeight() * 0.115); // Height of the

		// Capture the screenshot of the entire screen first
		File screenshot1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		// Now, crop the screenshot to the defined region
		BufferedImage fullImg1 = ImageIO.read(screenshot1);
		BufferedImage croppedImg1 = fullImg.getSubimage(x1, y1, width1 - x1, height1 - y1);

		// Save the cropped screenshot
		File outputfile1 = new File("C:\\Users\\User\\OneDrive\\Desktop\\New folder\\NewImage1.png");
		ImageIO.write(croppedImg, "png", outputfile1);

		System.out.println("Second Screenshot saved successfully.");
	}
}
