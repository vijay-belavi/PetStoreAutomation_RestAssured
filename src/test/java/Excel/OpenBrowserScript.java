package Excel;

import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class OpenBrowserScript {
    public static void main(String[] args) {
        // Deserialize the WebDriver instance from the file
        try (FileInputStream fileIn = new FileInputStream("webdriver.ser");
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            WebDriver driver = (WebDriver) objectIn.readObject();

            // Perform actions on the existing browser session
            // For example, navigate to a website
            driver.get("https://www.google.com");

            // Close the browser instance
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


