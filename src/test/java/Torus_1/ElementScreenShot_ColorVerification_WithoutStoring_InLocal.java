package Torus_1;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class ElementScreenShot_ColorVerification_WithoutStoring_InLocal {

    public static void captureElementScreenshotAndFetchRGB(WebElement element, int[] targetRGB) {
        try {
            // Capture the element screenshot directly in memory
        	
        	// ...
        	BufferedImage elementImage = ImageIO.read(new ByteArrayInputStream(element.getScreenshotAs(OutputType.BYTES)));

            // Iterate through each pixel to get the RGB values and check for the target RGB
            boolean targetFound = false;
            for (int x = 0; x < elementImage.getWidth(); x++) {
                for (int y = 0; y < elementImage.getHeight(); y++) {
                    int pixelColor = elementImage.getRGB(x, y);
                    int red = (pixelColor >> 16) & 0xff;
                    int green = (pixelColor >> 8) & 0xff;
                    int blue = pixelColor & 0xff;

                    // Check if the current pixel matches the target RGB value
                    if (red == targetRGB[0] && green == targetRGB[1] && blue == targetRGB[2]) {
                        targetFound = true;
                        System.out.println("Target RGB value found at pixel (" + x + "," + y + ")");
                        break;
                    }
                }
                if (targetFound)
                    break;
            }

            if (!targetFound) {
                System.out.println("Target RGB value not found in the element screenshot.");
            }

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
        WebElement element = driver
                .findElement(By.xpath("//android.widget.Button[contains(@content-desc,'Returns')]/android.view.View[1]"));

        // Specify the target RGB value to check (e.g., {255, 0, 0} for red)
        int[] targetRGB = {97, 218, 167};

        // Call the method to capture the screenshot, fetch RGB values, and validate the target
        captureElementScreenshotAndFetchRGB(element, targetRGB);

        // Close the driver after use
        driver.quit();
    }
}