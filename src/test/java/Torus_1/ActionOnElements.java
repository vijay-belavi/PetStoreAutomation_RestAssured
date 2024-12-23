package Torus_1;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.WebDriverManagerException;

public class ActionOnElements {

	public static void main(String[] args) throws Throwable {
		// Example usage:
		
	//	WebDriverManager.chromedriver().setup();
		WebDriver driver = new FirefoxDriver();// Initialize WebDriver (e.g., ChromeDriver)
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("https://samples.boldbi.com/#/solutions/education/student-performance-dashboard"); // Navigate to a
																										// website
		Thread.sleep(30000);
		// Get the list of elements
		List<WebElement> elements = driver.findElements(By.xpath(
				"//*[name()='g' and contains(@id,'dashboard_embeddedbichartItemChart1_svg_SeriesGroup_0')]//*[name()='rect' and contains(@id,'dashboard_embeddedbichartItemChart1') and contains(@fill,'#')]"));
		System.out.println("Element Size: "+elements.size());
		try {
			// Create an instance of the Actions class
			Actions actions = new Actions(driver);

			for (WebElement webElement : elements) {
				actions.moveToElement(webElement).perform();
				
				WebElement elementOne = driver.findElement(By.xpath("//td[contains(text(),'Students')]/following-sibling::td"));
				System.out.println("Element one text "+elementOne.getText());
			}
		} catch (Exception e) {
			// Handle exceptions gracefully
			System.out.println("An error occurred while processing the elements: " + e.getMessage());
		}
		driver.quit();
	}
}
