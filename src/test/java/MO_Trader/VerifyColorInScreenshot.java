package MO_Trader;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.chrome.ChromeDriver;

public class VerifyColorInScreenshot {
    public static void main(String[] args) {
		/*
		 * System.setProperty("webdriver.chrome.driver", "path_to_chromedriver.exe");
		 * ChromeDriver driver = new ChromeDriver();
		 */
        try {
			/*
			 * driver.get("your_webpage_url_here"); WebElement element =
			 * driver.findElement(By.id("elementId"));
			 * 
			 * // Capture a screenshot of the element File screenshotFile =
			 * element.getScreenshotAs(OutputType.FILE);
			 */
        	
        	File screenshotFile = new File ("C:\\Users\\User\\OneDrive\\Desktop\\New folder\\color.png");
            BufferedImage screenshot = ImageIO.read(screenshotFile);

            // Define the color you want to verify (in RGB format)
            Color targetColor = new Color(0, 255, 0); // Red color

            // Verify if the color is present in the screenshot
            boolean colorFound = isColorPresent(screenshot, targetColor);

            if (colorFound) {
                System.out.println("Color is present in the screenshot.");
            } else {
                System.out.println("Color is not present in the screenshot.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
           // driver.quit();
        }
    }

    private static boolean isColorPresent(BufferedImage image, Color targetColor) {
        int targetRGB = targetColor.getRGB();

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int pixelRGB = image.getRGB(x, y);
                if (pixelRGB == targetRGB) {
                    return true;
                }
            }
        }

        return false;
    }
}