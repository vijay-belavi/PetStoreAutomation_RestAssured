package Practice;
import java.time.Duration;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import com.warrenstrange.googleauth.GoogleAuthenticator;

public class FetchLogsOnNavigation {
    public static void main(String[] args) {
    	
    	String lastMatch = "";
		  String userId = "733045";
		  String password = "Arun@11ee11";
		  String url = "https://ant.aliceblueonline.com/";
		  String secretKey = "MRQNLSBJKNNLCMMTHNVATRPLOOOZRRGS";
		  String totp = null;
        // Set path to chromedriver executable
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\AppData\\Roaming\\fire-flink-client\\localnode\\chromedriver.exe");
        
        // Create ChromeOptions object
        ChromeOptions chromeOptions = new ChromeOptions();

        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        
        // Enable browser logging
        chromeOptions.addArguments("--enable-logging");
        chromeOptions.addArguments("--headless");
        chromeOptions.setCapability("goog:loggingPrefs", logPrefs);
        

        // Initialize Chrome driver with logging prefs
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        // Navigate to a webpage
        driver.get("https://ant.aliceblueonline.com/");
        
        driver.findElement(By.xpath("//input[@id='userid_inp']")).sendKeys(userId);
		
		driver.findElement(By.xpath("//button[@id='userId_btn']")).click();
					
		driver.findElement(By.xpath("//input[@id='password_inp']")).sendKeys(password);
		
		driver.findElement(By.xpath("//button[@id='password_btn']")).click();
		
		GoogleAuthenticator gAuth = new GoogleAuthenticator();
		totp = gAuth.getTotpPassword(secretKey) + "";

        // Fetch browser logs
        LogEntries logs = driver.manage().logs().get(LogType.PERFORMANCE);

        // Print browser logs
        for (LogEntry entry : logs) {
            System.out.println("Log Level: " + entry.getLevel());
            System.out.println("Log Message: " + entry.getMessage());
            // Process other log properties as needed
        }

        // Close the browser
        driver.quit();
    }
}
