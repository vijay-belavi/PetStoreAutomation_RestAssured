package Torus_1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Test {
	public static void main(String[] args) throws Throwable {
		WebDriver driver = new ChromeDriver();
		String file = "C:\\Users\\User\\Downloads\\Api3.txt";
		String email = "sujantyss7@fireflink.com";
		String subject = "API Response";
		try {
			writeEmail(file, email, subject, driver);
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}

	// Helper method to check if an element is present
	public static boolean isElementPresent(WebDriver driver, By locator) {
		try {
			driver.findElement(locator);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static void writeEmail(String file, String email, String subject, WebDriver driver) throws Exception {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		// Open Yahoo Mail
		driver.get("https://mail.yahoo.com/?.lang=en-IN");

		// Sign in button
		if (isElementPresent(driver, By.xpath("//a[text()='Sign in']"))) {
			driver.findElement(By.xpath("//a[text()='Sign in']")).click();
		}

		// Enter email
		if (isElementPresent(driver, By.xpath("//INPUT[contains(@class,'phone-no')]"))) {
			driver.findElement(By.xpath("//INPUT[contains(@class,'phone-no')]")).sendKeys("fireflinktorus@yahoo.com");
		}

		// Click Sign In
		if (isElementPresent(driver, By.xpath("//input[@name='signin']"))) {
			driver.findElement(By.xpath("//input[@name='signin']")).click();
		}
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		// Wait for a specific element to be visible
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("password")));

		// Enter password
		if (isElementPresent(driver, By.xpath("//INPUT[contains(@class,'password')]"))) {
			driver.findElement(By.xpath("//INPUT[contains(@class,'password')]")).sendKeys("FireTorus@2024");
		}

		// Submit password

		if (isElementPresent(driver, By.xpath("//button[@id='login-signin']"))) {
			driver.findElement(By.xpath("//button[@id='login-signin']")).click();
		}

		// Click Compose
		if (isElementPresent(driver, By.xpath("//A[normalize-space(.)='Compose']"))) {
			driver.findElement(By.xpath("//A[normalize-space(.)='Compose']")).click();
		}

		// Enter recipient email
		if (isElementPresent(driver, By.xpath("//SPAN[.='To']/following::INPUT[@role='combobox']"))) {
			driver.findElement(By.xpath("//SPAN[.='To']/following::INPUT[@role='combobox']")).sendKeys(email);
		}

		// Enter subject
		if (isElementPresent(driver, By.xpath("//*[@data-test-id='compose-subject']"))) {
			driver.findElement(By.xpath("//*[@data-test-id='compose-subject']")).sendKeys(subject);
		}

		// Enter body
		if (isElementPresent(driver, By.xpath("//DIV[@data-test-id='rte']"))) {
			driver.findElement(By.xpath("//DIV[@data-test-id='rte']"))
					.sendKeys("Please find out the Execution Report attached below");
		}

		// Attach file
		if (isElementPresent(driver, By.xpath("//input[@type='file']"))) {
			driver.findElement(By.xpath("//input[@type='file']")).sendKeys(file);
		}

		// Click Send
		if (isElementPresent(driver, By.xpath("//BUTTON[normalize-space(.)='Send']"))) {
			driver.findElement(By.xpath("//BUTTON[normalize-space(.)='Send']")).click();
		}

		// Verify email sent message (Optional, no assertion)
		if (isElementPresent(driver, By.xpath("//*[contains(text(),'has been sent.')]"))) {
			System.out.println("Email sent successfully.");
		} else {
			System.out.println("Email sent confirmation not found.");
		}

		driver.quit();
	}
}