package Torus_1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CheckElementDisabled {
	public static void main(String[] args) {
		// Set up WebDriver (make sure the driver is in your system's PATH)
		WebDriver driver = new ChromeDriver();

		// Navigate to the desired web page
		driver.get("https://www.google.com");

		// Locate the element (for example, a button with the ID "myButton")
		WebElement myButton = driver.findElement(By.id("myButton"));

		// Check if the element is disabled
		boolean isDisabled = !myButton.isEnabled(); // Returns true if disabled, false if enabled

		// Output result
		if (isDisabled) {
			System.out.println("Button is disabled.");
		} else {
			System.out.println("Button is enabled.");
		}

		// Close the driver
		driver.quit();
	}
}