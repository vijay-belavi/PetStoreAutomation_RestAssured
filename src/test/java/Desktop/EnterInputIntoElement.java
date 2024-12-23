package Desktop;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.windows.WindowsDriver;

public class EnterInputIntoElement {
	public static void main(String[] args) throws Throwable {
		// Set Desired Capabilities
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("appium:platformName", "Windows");
		capabilities.setCapability("appium:app", "C:\\Rupeeseed\\ADMIN\\RRS.exe");

		// Initialize Windows Driver
		WindowsDriver driver = new WindowsDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		Thread.sleep(3000);
		boolean element = driver.findElement(By.name("Login")).isDisplayed();
		System.out.println(element);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("TxtPassword"))).sendKeys("SYSADMIN2");

	//	driver.findElement(By.id("TxtLoginID")).sendKeys("SYSADMIN2");
		driver.findElement(By.id("TxtPassword")).sendKeys("SYSADMIN2");
		driver.findElement(By.id("TxtPanDOB")).sendKeys("SYSADMIN2");
		
		// Quit driver
		driver.quit();
	}
}
