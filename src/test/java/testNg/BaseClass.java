package testNg;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseClass {
	public static final String USERNAME = "vijaybelavi_lavdDc";
	public static final String AUTOMATE_KEY = "x3PsWNHFa3xZuhsx25ae";
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
	WebDriver driver;

	@BeforeTest
	public void test() throws Throwable {
		/*
		 * ChromeOptions options = new ChromeOptions(); DesiredCapabilities caps = new
		 * DesiredCapabilities(); Map browserStackOptions = new HashMap();
		 * caps.setCapability("os", "Windows"); caps.setCapability("os_version", "11");
		 * caps.setCapability("browserName", "Chrome");
		 * caps.setCapability("browserVersion", "latest"); caps.setCapability("name",
		 * "Sample Test"); options.setCapability("browserStack:options",
		 * browserStackOptions);
		 * 
		 * URL url = new URL(URL); driver = new RemoteWebDriver(url, options);
		 */
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
	}
}