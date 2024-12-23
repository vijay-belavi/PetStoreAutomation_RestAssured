package Practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Switch_between_browser {
	public static void main(String[] args) {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://www.google.com/");

		String chromeBrowser = driver.getWindowHandle();
		
		WebDriver driver1 = new FirefoxDriver();
		driver1.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver1.manage().window().maximize();
		driver1.get("https://www.google.com/");

		String firefoxBrowser = driver1.getWindowHandle();

		driver.switchTo().window(chromeBrowser);
		boolean element = driver.findElement(By.xpath("//img[@alt=\"Google\"]")).isDisplayed();
		driver.close();

		driver1.switchTo().window(firefoxBrowser);

		driver1.quit();

	}
}
