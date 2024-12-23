package MobileWare;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ClickOnElementAndStoreValues {
	public static void main(String[] args) throws Throwable {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		driver.manage().window().maximize();

		driver.get("https://mwt.transxt.in:8443/transxt/portal/hitachi-admin/#/login");
		driver.findElement(By.xpath("//input[contains(@id,'inputEmail')]")).sendKeys("6363266159");
		driver.findElement(By.xpath("//INPUT[@formcontrolname='password']")).sendKeys("Password@123");
		driver.findElement(By.xpath("//BUTTON[normalize-space(.)='LOGIN']")).click();

		driver.findElement(By.xpath("//span[text()='Reports']/..")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Transaction History')]")).click();
		System.out.println("Start wait time:");
		Thread.sleep(30000);
		ArrayList<String> arrayList = new ArrayList();
		System.out.println("Start Execution: ");
		try {
			arrayList = test(driver, arrayList);
			System.out.println(arrayList.size());
			arrayList.remove(arrayList.size()-1);
			for (String string : arrayList) {
				System.out.println(string);
			}
			System.out.println(arrayList.size());
		} catch (Exception e) {
			
		}
	}
	public static ArrayList<String> test (WebDriver driver, ArrayList<String> arrayList) {
		try {
			
			boolean isDisplayed = true;
			while (isDisplayed) {
				List<WebElement> elements = driver
						.findElements(By.xpath("//tr[@class='ng-star-inserted']/td[1]//div[@class='text-right']"));

				for (WebElement webElement : elements) {
					arrayList.add(webElement.getText());
					if (!webElement.getText().contains("Aug 19")) {
						isDisplayed = false;
						break;
					}
				}
				if (arrayList.get(arrayList.size()-1).contains("Aug 19")) {
					driver.findElement(By.xpath("(//li[@class='custom-pagination-next']/a)[1]")).click();
				}
			}
		}
		catch(StaleElementReferenceException e) {
			System.out.println("Catch Block");
			test(driver, arrayList);
		}
		return arrayList;
	}
}
