package Torus_1;

import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class MultiThreadingAppium {
    private static AndroidDriver driver;

    public static void main(String[] args) throws Throwable {
        // Initialize the AndroidDriver here and ensure the session is active
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");

        // Initialize the Appium driver
        AndroidDriver driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        try {
            // Code to initialize the driver
            // Ensure driver initialization is successful before proceeding
            if (driver != null && driver.getSessionId() != null) {
                ExecutorService executor = Executors.newFixedThreadPool(2);

                Runnable pressAndHoldTask = () -> {
                    try {
                        synchronized (driver) {
                            System.out.println("Starting press and hold action...");

                            // Initialize the PointerInput object
                            PointerInput pointer = new PointerInput(PointerInput.Kind.TOUCH, "finger1");

                            // Create a sequence of actions (press, wait, release)
                            Sequence pressAndHoldSequence = new Sequence(pointer, 0);
                            pressAndHoldSequence.addAction(pointer.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), 500, 1000)) // Move to coordinates
                                                 .addAction(pointer.createPointerDown(PointerInput.MouseButton.LEFT.asArg())) // Pointer down (press)
                                                 .addAction(pointer.createPointerMove(Duration.ofSeconds(3), PointerInput.Origin.viewport(), 500, 1000)); // Hold (move to same position with delay)

                            // Perform the press-and-hold sequence directly
                            driver.perform(Arrays.asList(pressAndHoldSequence));  // This performs the press and hold sequence

                            System.out.println("Press and hold action completed.");
                        }
                    } catch (NoSuchSessionException e) {
                        System.err.println("Error: Driver session is terminated or not started.");
                    } catch (WebDriverException e) {
                        System.err.println("WebDriver exception occurred: " + e.getMessage());
                    } catch (Exception e) {
                        System.err.println("An unexpected error occurred: " + e.getMessage());
                    }
                };

                Runnable searchElementTask = () -> {
                    try {
                        synchronized (driver) {
                            System.out.println("Searching for the element...");
                            WebElement element = driver.findElement(By.xpath("//android.view.View[contains(@content-desc,'NAV')]"));
                            System.out.println("Element found: " + element.getText());
                        }
                    } catch (NoSuchSessionException e) {
                        System.err.println("Error: Driver session is terminated or not started.");
                    } catch (WebDriverException e) {
                        System.err.println("WebDriver exception occurred while searching for the element: " + e.getMessage());
                    } catch (Exception e) {
                        System.err.println("An unexpected error occurred while searching for the element: " + e.getMessage());
                    }
                };

                // Execute the tasks
                executor.execute(pressAndHoldTask);
                executor.execute(searchElementTask);

                // Shutdown the executor
                executor.shutdown();
            } else {
                System.out.println("Driver is not initialized or session is inactive.");
            }
        } catch (Exception e) {
            System.err.println("Failed to initialize the driver or handle exceptions: " + e.getMessage());
        }
    }
}
