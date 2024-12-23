package testNg;

import java.time.Duration;

import org.apache.maven.model.ReportSet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NewTest {
	
	WebDriver driver;
	@BeforeClass
	public void openBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
		driver.get("https://google.com/");
	}
	
	@Test
	public void Newtest() {
		
	}
	
	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}

}
