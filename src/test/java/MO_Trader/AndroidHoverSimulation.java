package MO_Trader;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class AndroidHoverSimulation {
    public static void main(String[] args) throws Throwable {
        // Set the desired capabilities for your Android WebDriver
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("appPackage", "mosl.powerapp.com.uat");
        caps.setCapability("appActivity", "mosl.powerapp.com.MainActivity");
        caps.setCapability("noReset", "true");

        // Initialize the AndroidDriver
        AppiumDriver driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), caps);
        
        Thread.sleep(20000);
        // Find the element to hover over
        WebElement elementToHover = driver.findElement(By.xpath("//android.view.View[contains(@content-desc,'IAP')]/android.view.View/android.view.View/android.view.View"));

        Actions action = new Actions(driver);
        action.moveToElement(elementToHover).build().perform();

        int startX = elementToHover.getLocation().getX() + elementToHover.getSize().getWidth() - 10; // Adjust as needed
        int startY = elementToHover.getLocation().getY() + elementToHover.getSize().getHeight() / 2;
        int endX = elementToHover.getLocation().getX() + 10; // Adjust as needed
        int endY = startY;
        
        // Create a TouchAction instance
        Actions actions = new Actions(driver);
        actions.moveByOffset(100, 100);
        actions.perform();

        System.out.println("swiped successfully");
       
        driver.quit();
    }
}
