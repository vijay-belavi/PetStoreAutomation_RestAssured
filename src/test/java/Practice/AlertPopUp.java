package Practice;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AlertPopUp {
public static void main(String[] args) throws Throwable {
	WebDriver driver  = new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	
	String username = "admin";
	String password = "admin";
	driver.get("https://"+username+":"+password+"@"+"the-internet.herokuapp.com/basic_auth");
	
	//Passing the username and password into URL directly in handling Alert Pop Ups
	
	driver.quit();
}
}
