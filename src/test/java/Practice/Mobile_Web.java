package Practice;
import java.net.URL;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class Mobile_Web {
    public static void main(String[] args) {
        try {
            // Set the Desired Capabilities
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", "RZ8T50WVHTE");
            capabilities.setCapability("browser", "Chrome");
            capabilities.setCapability("noReset", true);
            capabilities.setCapability("chromedriverExecutable", "C:\\Users\\User\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

            // Initialize the AndroidDriver
            AndroidDriver driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);

            // Open a URL in the first tab
            driver.get("https://indialends.com");

            // Open a new tab using JavaScript
            driver.executeScript("window.open('https://cricbuzz.com', '_blank');");

            // Get all window handles
            Set<String> windowHandles = driver.getWindowHandles();
            String firstTabHandle = driver.getWindowHandle();
            String secondTabHandle = null;

            // Find the new tab handle
            for (String handle : windowHandles) {
                if (!handle.equals(firstTabHandle)) {
                    secondTabHandle = handle;
                    break;
                }
            }

            // Switch to the new tab
            if (secondTabHandle != null) {
                driver.switchTo().window(secondTabHandle);
                // Perform actions on the new tab
                driver.get("https://cricbuzz.com");
                Thread.sleep(5000);
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("window.scrollBy(0,1000)");
                System.out.println("Scroll Action Performed");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}