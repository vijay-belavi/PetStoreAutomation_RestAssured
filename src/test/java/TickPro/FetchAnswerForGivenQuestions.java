package TickPro;

import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FetchAnswerForGivenQuestions {
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
		
		String question1 = driver.findElement(By.xpath("//input[@id='answer1']/..")).getText();
		question1 = question1.replaceAll("(?m)^[ \t]*\r?\n", "");
		String[] splitQuestions = question1.split("\\?");
		for (String question : splitQuestions) {
            if (!question.trim().isEmpty()) {
                list.add(question.trim() + "?");
            }
        }
		System.out.println(list);
		System.out.println(list.size());
		driver.quit();
	}
}