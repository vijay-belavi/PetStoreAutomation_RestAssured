package Torus_1;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Set;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;



public class TestingMobileWeb {
	public static void main(String[] args) throws MalformedURLException {
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("chromedriverExecutable", "C:\\Users\\User\\AppData\\Roaming\\fire-flink-client\\localnode\\chromedriver.exe");
		AndroidDriver driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		System.out.println("hi");

		
		System.out.println(driver.getContextHandles());
		driver.context("WEBVIEW_chrome");
System.out.println(driver.getCurrentUrl());		

Set string =driver.getWindowHandles();
for (Object object : string) {
	driver.switchTo().window(object.toString());
	if (driver.getCurrentUrl().contains("digitalloc")) {
		System.out.println("done");
		break;
	}
}

driver.findElement(By.xpath("//input[@class=\"col-aadhar-input\"][position()=1]")).sendKeys("1237");
driver.findElement(By.xpath("//input[@class=\"col-aadhar-input\"][position()=2]")).sendKeys("1253");
driver.findElement(By.xpath("//input[@class=\"col-aadhar-input\"][position()=3]")).sendKeys("1283");


	}
	
	


}
