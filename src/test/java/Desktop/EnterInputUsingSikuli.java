package Desktop;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.sikuli.script.Screen;
import org.sikuli.script.Pattern;

import io.appium.java_client.windows.WindowsDriver;

public class EnterInputUsingSikuli {
    public static void main(String[] args) throws Exception {
    	
    	System.load("C:\\Users\\User\\Downloads\\opencv_java490 (1).dll");

        // Set Desired Capabilities
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium:platformName", "Windows");
        capabilities.setCapability("appium:app", "C:\\Rupeeseed\\ADMIN\\RRS.exe");

        // Initialize Windows Driver
        WindowsDriver driver = new WindowsDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Create a Screen object for Sikuli interactions
        Screen screen = new Screen();

        // Specify the image path for the target element
        String imagePath = "C:\\Users\\User\\OneDrive\\Pictures\\Screenshots\\Screenshot 2024-12-16 172825.png"; // Replace with your image path

        // Create a Pattern object from the image
        Pattern elementPattern = new Pattern(imagePath);

        // Define the value to be entered
        String valueToEnter = "SYSADMIN2";

        try {
            // Check if the element is present on the screen
            if (screen.exists(elementPattern) != null) {
                System.out.println("Element found!");

                // Click on the element to focus
                screen.click(elementPattern);

                // Type the value
                screen.type(valueToEnter);

                System.out.println("Value entered successfully.");
            } else {
                System.out.println("Element not found.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            // Quit the driver
            driver.quit();
        }
    }
}
