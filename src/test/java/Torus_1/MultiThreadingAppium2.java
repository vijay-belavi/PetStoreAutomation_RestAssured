package Torus_1;

import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class MultiThreadingAppium2 {
    private static AndroidDriver driver;

    public static void main(String[] args) throws Throwable {
        // Initialize the DesiredCapabilities object
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        
        // Initialize the Appium driver
        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        try {
            System.out.println("Starting press and hold action...");

            // Initialize the PointerInput object for simulating touch
            PointerInput pointer = new PointerInput(PointerInput.Kind.TOUCH, "finger1");

            // Create a sequence for press-and-hold (move, press, and hold)
            Sequence pressAndHoldSequence = new Sequence(pointer, 0);
            pressAndHoldSequence
                    .addAction(pointer.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), 500, 1000)) // Move to the desired coordinates
                    .addAction(pointer.createPointerDown(PointerInput.MouseButton.LEFT.asArg())) // Press down
                    .addAction(pointer.createPointerMove(Duration.ofSeconds(4), PointerInput.Origin.viewport(), 500, 1000)); // Hold at the same position

            // Wait until the element is present in the DOM
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[contains(@content-desc,'NAV')]")));

            System.out.println("Searching for the element...");
            System.out.println("Element found: " + element.getAttribute("content-desc"));

            // Perform the press-and-hold action (this part was missing in your code)
            driver.perform(Arrays.asList(pressAndHoldSequence)); // Perform the actions on the driver

            System.out.println("Press and hold action completed.");

        } catch (Exception e) {
            System.err.println("Failed to initialize the driver or handle exceptions: " + e.getMessage());
        } finally {
            if (driver != null) {
                driver.quit(); // Close the driver session
            }
        }
    }
}
