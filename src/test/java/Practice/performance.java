package Practice;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class performance {
public static void main(String[] args) throws Throwable{
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("https://uatweb.indialends.com");
	Thread.sleep(30000);
	driver.findElement(By.xpath("//button[text()='Proceed']")).click();
	String xpath = "//h5[contains(text(),'OTP')]";
	String expTime = "5";
	double endTime = 0.0;
	Duration impli = driver.manage().timeouts().getImplicitWaitTimeout();
	String diffTime = null;
	System.out.println("Xpath is: " + xpath);
	System.out.println("Exp time is: " + expTime);
	System.out.println("fireflink implicit: " + impli);
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	long timeOut = impli.toMillis();
	double countTime = 0.0;
	while (true) {
		if (countTime <= timeOut) {
			try {
				System.out.println("Try block Entered");
				if (driver.findElement(By.xpath(xpath)).isDisplayed()) {
					endTime = countTime;
					diffTime = String.valueOf(Math.round(endTime / 1000.0));
					break;
				}
			} catch (StaleElementReferenceException e) {
				System.out.println("Stale element block");
				endTime = countTime;
				diffTime = String.valueOf(Math.round(endTime / 1000.0));
				break;

			} catch (NoSuchElementException e) {
				System.out.println("No such element block");
				countTime += 140.0;
			}
		} else {
			System.out.println("Element not found");
			break;
		}
	}
	System.out.println("End Time: "+endTime);
	System.out.println("Diff Time: "+diffTime);
	driver.quit();
}
}
