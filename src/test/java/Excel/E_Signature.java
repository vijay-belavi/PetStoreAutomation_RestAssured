package Excel;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class E_Signature {
public static void main(String[] args) throws Throwable {
	WebDriver driver = new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.manage().window().maximize();
	
	driver.get("https://signaturely.com/online-signature/draw/");
	try {
		WebElement ele = driver.findElement(By.xpath("//div[@id='react-app-root']//*[local-name()='svg']"));
		System.out.println(ele.isDisplayed());
		
		int width = ele.getSize().getWidth();
		int height = ele.getSize().getHeight();
		int x = ele.getLocation().getX();
		int y = ele.getLocation().getY();
		
		System.out.println("width: "+width+" height: "+height);
		System.out.println("x: "+x+" y: "+y);
		   // Generate or load the signature (for demonstration purposes, let's assume we have generated an image)
        String signatureImage = "C:\\Users\\User\\Downloads\\signature.png";
        
     // Scroll down by 500 pixels
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500);");
		
        // Create Actions object
        Actions actions = new Actions(driver);
        
        actions.moveToElement(ele)
        .clickAndHold()
        .moveByOffset(20, 50) // Move right to start drawing
        .release()
        .perform();
 
		 actions.moveToElement(ele)
		 .clickAndHold()
		 .moveByOffset(-20, 50)
		 .release()
		 .perform();
        
		 actions.moveToElement(ele)
		 	.clickAndHold()
		 	.moveByOffset(-20, 45)
		 	.moveByOffset(50, -30)
		 	.release()
		 	.perform();
		 
     // Convert the WebElement into a Screenshot object
        TakesScreenshot screenshot = (TakesScreenshot)ele;

        // Capture the screenshot of the element
        File screenshotFile = screenshot.getScreenshotAs(OutputType.FILE);

     // Define the path to store the screenshot file
        String localFilePath = "C:\\Users\\User\\Downloads\\screenshot.png";

        // Copy the screenshot file to the specified location
        FileUtils.copyFile(screenshotFile, new File(localFilePath));

        System.out.println("Element Screenshot captured successfully");
        
        WebElement ele1 = driver.findElement(By.xpath("//div[@id='react-app-root']//*[local-name()='svg']//*[local-name()='path']"));
		System.out.println("Signature is "+ele.isDisplayed());
	}
	catch(IOException e) {
		System.out.println("File Not Found");
	}
	catch (Exception e) {
		System.out.println("Exception "+e);
	}
	driver.quit();
}
}
