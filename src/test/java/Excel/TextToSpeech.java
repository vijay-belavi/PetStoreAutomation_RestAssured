package Excel;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TextToSpeech {

    public static void main(String[] args) {
    	
    	WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.get("https://freetts.com/");
		
		driver.findElement(By.xpath("//tOextarea[@id='textarea']")).sendKeys("1 2 3");
		driver.findElement(By.xpath("//button[contains(@class,'primary_background') and position()='1']")).click();
		driver.findElement(By.xpath("//div[contains(@class,'primary_hover_green') and position()='1']")).click();
		
		driver.quit();
		System.out.println("hello");
    }
}
