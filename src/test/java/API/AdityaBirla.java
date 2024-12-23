package API;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

public class AdityaBirla {
	public static void main(String[] args) {
		WebDriver driver = null;
		try {
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			driver.manage().window().maximize();
			
			driver.get("https://mtpre.adityabirlahealth.com/healthinsurance/homepage");
			Thread.sleep(30000);
			driver.switchTo().newWindow(WindowType.TAB);
			driver.findElement(By.xpath("//p[text()='Sum Insured']/..//div[contains(@class,'mat-select-arrow')]")).click();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
