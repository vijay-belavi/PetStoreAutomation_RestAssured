package TickPro;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OpenChromeBrowsser {
	public static void main(String[] args) throws Throwable {

		ArrayList<String> list = new ArrayList();
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();

		driver.get("https://tick.reliancesmartmoney.com/");

		driver.findElement(By.xpath("//*[@placeholder='Enter User ID']")).sendKeys("RS8884");
		driver.findElement(By.xpath("//BUTTON[normalize-space(.)='Login']")).click();
		driver.findElement(By.xpath("//INPUT[@placeholder='ENTER PASSWORD']")).sendKeys("Prasad@123");
		driver.findElement(By.xpath("//BUTTON[normalize-space(.)='Verify Password']")).click();
		driver.findElement(By.xpath("//INPUT[@id='answer1']")).sendKeys("a");
		driver.findElement(By.xpath("//DIV[contains(@class,'field-wrapper input')]/INPUT[2]")).sendKeys("a");
		driver.findElement(By.xpath("//BUTTON[normalize-space(.)='Login']")).click();
		Thread.sleep(5000);

		// Get the current time
		LocalTime currentTime = LocalTime.now();
		
		// Define a formatter for hours and minutes
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        // Format the current time to hours and minutes
        String formattedTime = currentTime.format(formatter);

		// Define the total duration in milliseconds (20 seconds)
		long totalDuration = 5000; // 20 seconds
		// Define the iteration interval in milliseconds (1 second)
		long interval = 1000; // 1 second

		String attributeValue = null;
		// Get the start time
		long startTime = System.currentTimeMillis();

		// Start the while loop
		String initialTime = "09:15";
		String endTime = "15:30";
		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("HH:mm");
		LocalTime marketStartTime = LocalTime.parse(initialTime, formatter1);
		LocalTime marketEndTime = LocalTime.parse(endTime, formatter1);

		while (System.currentTimeMillis() - startTime < totalDuration) {
			WebElement element = driver.findElement(By.xpath(
					"(//span[contains(@class,'tickwebspan')]/following-sibling::ul//div[@class=\"widget-heading\"]//span[contains(@class,'text')])[3]"));

			if (currentTime.isBefore(marketStartTime)) {
				attributeValue = element.getText().replaceAll("[^0-9.]", "");
				list.add(attributeValue);
				System.out.println("Current time " + formattedTime + " which is before the market hours, The Market opens at "
						+ marketStartTime);
				break;
			} else if (currentTime.isAfter(marketEndTime)) {
				attributeValue = element.getText().replaceAll("[^0-9.]", "");
				list.add(attributeValue);
				System.out.println("Current time " + formattedTime + " which is after the market hours, The Market closes at "
						+ marketEndTime);

				break;
			} else {
				attributeValue = element.getText().replaceAll("[^0-9.]", "");
				list.add(attributeValue);
				Thread.sleep(interval);
				if (list.size() == 2) {
					if (list.get(0).equals(list.get(list.size() - 1))) {
						continue;
					} else {
						break;
					}
				}
			}

		}
		System.out.println("All the values fetched within 20 seconds: ");
		for (Object object : list) {
			System.out.println(object);
		}
		System.out.println("Loop has ended after 5 seconds.");

		driver.quit();
	}
}
