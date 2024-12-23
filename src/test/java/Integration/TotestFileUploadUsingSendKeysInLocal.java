package Integration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TotestFileUploadUsingSendKeysInLocal {

	public static void main(String[] args) {

		
		WebDriver driver =new ChromeDriver();
		driver.get("https://www.fileconvoy.com/");
		driver.findElement(By.id("upfile_0")).sendKeys("C:\\Users\\User\\Downloads\\browserstack upload.txt"); // File
								
	}

}
