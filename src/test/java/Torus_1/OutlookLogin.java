package Torus_1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class OutlookLogin {
	public static void main(String[] args) throws Throwable {

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		driver.get("https://login.live.com/login.srf?wa=wsignin1.0&rpsnv=160&ct=1726121017&rver=7.0.6738.0&wp=MBI_SSL&wreply=https%3a%2f%2foutlook.live.com%2fowa%2f%3fnlp%3d1%26cobrandid%3dab0455a0-8d03-46b9-b18b-df2f57b9e44c%26culture%3den-us%26country%3dus%26RpsCsrfState%3da2b6c48e-002f-219e-97e3-2fe1c464485d&id=292841&aadredir=1&CBCXT=out&lw=1&fl=dob%2cflname%2cwld&cobrandid=ab0455a0-8d03-46b9-b18b-df2f57b9e44c");
		driver.findElement(By.xpath("//input[@name='loginfmt']")).sendKeys("vijaybelavi108@gmail.com");
		driver.findElement(By.xpath("//button[@id='idSIButton9']")).click();
		driver.findElement(By.xpath("//input[@name='passwd']")).sendKeys("Vijay@021899");
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();
		driver.findElement(By.xpath("//button[text()='Yes']")).click();
		
	}
}
