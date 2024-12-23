package API;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CSM_Reset {
public static void main(String[] args) throws Throwable{
	WebDriver driver = new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	
	driver.manage().window().maximize();
	
	driver.get("https://bskycms.odisha.gov.in:8443/#/login");
	driver.findElement(By.xpath("//input[@id='userInput']")).sendKeys("21162002");
	driver.findElement(By.xpath("//input[@id='inputPassword']")).sendKeys("Admin@123");
	Thread.sleep(5000);
	driver.findElement(By.xpath("//a[@id='loginBtn']")).click();
	Thread.sleep(30000);
	
	WebElement element = driver.findElement(By.xpath("//button[@type='reset']"));
	element.click();
	
	
	
	
	
	
	driver.quit();
}
	
}
