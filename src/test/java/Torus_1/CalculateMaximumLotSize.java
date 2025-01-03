package Torus_1;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CalculateMaximumLotSize {
public static void main(String[] args) {
	
	WebDriverManager.chromedriver().setup();
	WebDriver driver = new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.manage().window().maximize();
	
	
	driver.get("https://www.nseindia.com/market-data/all-upcoming-issues-ipo");
	List<WebElement> element = driver.findElements(By.xpath("//table[@id='publicIssuesCurrent']/tbody/tr/td/a"));
	System.out.println(element.size());
	for (WebElement webElement : element) {
		webElement.getText();
	}
	
	driver.quit();
}
}
