package MobileWare;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Base_Class {
	
	public void app_open() {
		WebDriver driver = new ChromeDriver();
		Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("https://ybluat.transxt.in/dmtadminui/#/login");
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("sujan.r@fireflink.com");
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("q$nsCjVQ2T");
		
		driver.quit();
		
		String browserName = capabilities.getBrowserName();
		String browserVersion = capabilities.getBrowserVersion();
		String platformName = capabilities.getPlatformName().toString();

		System.out.println("Browser Name: " + browserName);
		System.out.println("Browser Version: " + browserVersion);
		System.out.println("Platform: " + platformName);
		
		if (capabilities.toString().contains("[--remote-allow-origins")) {
			System.out.println("Hi");
		}
	}
	public static void main(String[] args) {
		Base_Class b = new Base_Class();
		//b.app_open();
		System.out.println("Done");
	}
}
