package Excel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class CaptureInstanceScript {
    public static void main(String[] args) {

        // Initialize ChromeDriver and capture the instance
        WebDriver driver = new ChromeDriver();

        // Serialize the WebDriver instance to a file
        try (FileOutputStream fileOut = new FileOutputStream("webdriver.ser");
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Close the browser instance
        driver.quit();
    }
}