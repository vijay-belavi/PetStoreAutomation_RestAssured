package Torus_1;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HandleUnexpectedAlert {

    public static void main(String[] args) {
        // Configure WebDriver to ignore unhandled alerts (Optional)
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
        WebDriver driver = new ChromeDriver();
        
        try {
            driver.get("https://example.com");
            
            // Scenario 1: Using try-catch to handle the alert
            try {
                WebElement submitButton = driver.findElement(By.id("submit"));
                submitButton.click(); // This might trigger an alert
            } catch (Exception e) {
                System.out.println("Caught an UnexpectedAlertPresentException!");
                handleAlert(driver); // Custom method to handle alert
            }
            
            // Scenario 2: Explicitly waiting for alert
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            try {
                Alert alert = wait.until(ExpectedConditions.alertIsPresent());
                System.out.println("Alert found: " + alert.getText());
                alert.accept(); // Accept the alert
            } catch (TimeoutException e) {
                System.out.println("No alert appeared within the specified time.");
            }
            
            // Scenario 3: Handling alerts using a custom utility method
            handleAlertIfPresent(driver);
            
        } finally {
            // Close the browser
            driver.quit();
        }
    }
    
    // Custom utility method to handle alert if present
    public static void handleAlert(WebDriver driver) {
        try {
            Alert alert = driver.switchTo().alert();
            System.out.println("Handling alert: " + alert.getText());
            alert.accept(); // Accepts the alert
        } catch (NoAlertPresentException e) {
            System.out.println("No alert present to handle.");
        }
    }
    
    // Another utility method to check if an alert is present and handle it
    public static void handleAlertIfPresent(WebDriver driver) {
        try {
            Alert alert = driver.switchTo().alert();
            System.out.println("Unexpected alert found: " + alert.getText());
            alert.accept(); // Accepts the alert
        } catch (NoAlertPresentException e) {
            System.out.println("No alert found, continuing with the script.");
        }
    }
}
