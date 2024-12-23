package Practice;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ShadowElement {
public static void main(String[] args) {
	WebDriver driver = new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	
	driver.get("https://www.google.com");
	
	JavascriptExecutor js = (JavascriptExecutor) driver;
	
	/*
	 * js. // Find the host element containing the Shadow DOM const hostElement =
	 * document.querySelector('your-shadow-host-selector');
	 * 
	 * // Open the Shadow DOM const shadowRoot = hostElement.shadowRoot;
	 * 
	 * // Now you can use XPath to access elements within the Shadow DOM const
	 * shadowElement = shadowRoot.querySelector('your-shadow-element-selector');
	 */
}
}
