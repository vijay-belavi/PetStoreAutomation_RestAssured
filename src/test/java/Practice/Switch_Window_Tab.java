package Practice;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Switch_Window_Tab {
public static void main(String[] args) throws InterruptedException {
	WebDriver driver = new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.manage().window().maximize();
	driver.get("https://www.myntra.com/");
	
	String parentID = driver.getWindowHandle();
	
	Set<String> childID = driver.getWindowHandles();
	for (String string : childID) {
		System.out.println(string);
		if (!string.contains(parentID)) {
			driver.switchTo().window(string);
			WebElement ele = driver.findElement(By.xpath("//h1[@class='pdp-title']"));
			
			System.out.println(ele.getText());
		}
	}
}
}
