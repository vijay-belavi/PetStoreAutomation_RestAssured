package Practice;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;

public class GetCSSValueAndroid {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        // Set up desired capabilities for Android
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("noReset", "true");
        caps.setCapability("appPackage", "com.synergy.sbimerchant");
        caps.setCapability("appActivity", "com.synergy.sbimerchant.SplashScreen");

        // Create an AndroidDriver instance
        WebDriver driver = new AppiumDriver(new URL("your-appium-server-url"), caps);
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        Thread.sleep(10000);

        // Find the element by its locator (e.g., ID, class name, etc.)
        WebElement element = driver.findElement(By.id("//android.widget.TextView[@text='Home']/.."));
        
        // Get the CSS value of a specific property
        String cssValue = element.getCssValue("background");
        System.out.println("CSS Value: " + cssValue);

        // Close the driver
        driver.quit();
    }
}

