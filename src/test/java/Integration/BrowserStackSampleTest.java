package Integration;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserStackSampleTest {

	public static final String USERNAME = "vijaybelavi_lavdDc";
	public static final String AUTOMATE_KEY = "x3PsWNHFa3xZuhsx25ae";
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

	public static void main(String[] args) throws Exception {

		ChromeOptions browserOptions = new ChromeOptions();
		browserOptions.setPlatformName("Windows 11");
		browserOptions.setBrowserVersion("latest");
		Map<String, Object> browserStackOptions = new HashMap<>();
		browserStackOptions.put("name", "Sample Test");
		browserOptions.setCapability("browserStack:options", browserStackOptions);
		URL url = new URL(URL);
		WebDriver driver = new RemoteWebDriver(url, browserOptions);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		driver.manage().window().maximize();
		driver.get("https://www.shoppersstack.com/");
		driver.findElement(By.xpath("//button[@name='loginBtn']")).click();
		driver.findElement(By.xpath("//input[@name='Email']")).sendKeys("deeksham0815@gmail.com");
		driver.findElement(By.xpath("//input[@name='Password']")).sendKeys("Deeksha08@");
		driver.findElement(By.xpath("//button[@name='Login']")).click();
		String userName = driver
				.findElement(By.xpath("//button[@aria-label='Account settings']/../preceding-sibling::h3")).getText();

		System.out.println(browserOptions.getPlatformName());
		System.out.println(browserOptions.toString());

		if (browserOptions.toString().contains("[--remote-allow-origins")) {
			System.out.println("BrowserStack");
		}

		System.out.println(userName);
		driver.quit();
	}
}
