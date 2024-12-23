package Torus_1;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ToConnect {

	public static void main(String[] args) throws MalformedURLException {
		
		DesiredCapabilities cap =new DesiredCapabilities();
	   
		ChromeOptions option = new ChromeOptions();
		// option.addArguments("--remote-debugging-port=9222");
		option.setExperimentalOption("debuggerAddress", "localhost:9222");
		cap.merge(option);
		RemoteWebDriver driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),option);
		

		driver.findElement(By.xpath("//div[text()='Pictures or Images']")).click();
		
	}

}
