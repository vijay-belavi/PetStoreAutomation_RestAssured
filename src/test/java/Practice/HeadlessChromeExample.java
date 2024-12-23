package Practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import com.warrenstrange.googleauth.GoogleAuthenticator;

public class HeadlessChromeExample {
	public static void main(String[] args) {
		
		String userId = "733045";
		  String password = "Arun@11ee11";
		  String url = "https://ant.aliceblueonline.com/";
		  String secretKey = "MRQNLSBJKNNLCMMTHNVATRPLOOOZRRGS";
		  String totp = null;
		 // Set path to chromedriver executable
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\AppData\\Roaming\\fire-flink-client\\localnode\\chromedriver.exe");

        // Create ChromeOptions object
        ChromeOptions chromeOptions = new ChromeOptions();

        // Set headless mode
        chromeOptions.addArguments("--headless");

        // Initialize Chrome driver with headless option
        WebDriver driver = new ChromeDriver(chromeOptions);

        // Example: Visit a website
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        
        driver.get("https://ant.aliceblueonline.com/");
        
        // Perform further actions on the webpage
        driver.findElement(By.xpath("//input[@id='userid_inp']")).sendKeys(userId);
		
		driver.findElement(By.xpath("//button[@id='userId_btn']")).click();
					
		driver.findElement(By.xpath("//input[@id='password_inp']")).sendKeys(password);
		
		driver.findElement(By.xpath("//button[@id='password_btn']")).click();
		
		GoogleAuthenticator gAuth = new GoogleAuthenticator();
		totp = gAuth.getTotpPassword(secretKey) + "";
		
		driver.findElement(By.xpath("//input[@id='totp_otp_inp']")).sendKeys(totp);
		
		
		
		  LogEntries logsBrowser = driver.manage().logs().get(LogType.BROWSER);
		  System.out.println("Logs Browser "+logsBrowser); LogEntries logsClient =
		  driver.manage().logs().get(LogType.CLIENT);
		  System.out.println("Logs Client "+ logsClient); LogEntries logsDriver =
		  driver.manage().logs().get(LogType.DRIVER);
		  System.out.println("Logs Driver "+ logsDriver); LogEntries logsServer =
		  driver.manage().logs().get(LogType.SERVER);
		  System.out.println("Logs Server "+ logsServer); LogEntries logsPerformance =
		  driver.manage().logs().get(LogType.PERFORMANCE);
		  System.out.println("Logs Performance "+ logsPerformance); LogEntries
		  logsProfiler = driver.manage().logs().get(LogType.PROFILER);
		  System.out.println("Logs Profiler "+ logsProfiler); for (LogEntry entry :
		  logsBrowser) { System.out.println(entry.getMessage()); }
		 
		
		
        System.out.println("Logged into Codifi");
        // Close the browser
        driver.quit();
		
	}
}