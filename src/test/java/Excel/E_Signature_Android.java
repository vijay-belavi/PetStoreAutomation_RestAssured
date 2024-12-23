package Excel;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.PointerInput.Kind;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class E_Signature_Android {
public static void main(String[] args) throws Throwable {
	
	DesiredCapabilities caps = new DesiredCapabilities();
	caps.setCapability("platformName", "Android");
	caps.setCapability("noReset", "true");
	caps.setCapability("autoGrantPermission", "true");

	AppiumDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
	
	try {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebElement ele = driver.findElement(By.xpath("//android.widget.TextView[@text='This signature should match with the PAN card signature.']/following-sibling::android.view.View/android.widget.Image"));
		System.out.println(ele.isDisplayed());
		
		int width = ele.getSize().getWidth();
		int height = ele.getSize().getHeight();
		int x = ele.getLocation().getX();
		int y = ele.getLocation().getY();
		
		System.out.println("width: "+width+" height: "+height);
		System.out.println("x: "+x+" y: "+y);
	
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
        
		System.out.println("Signature is "+ele.isDisplayed());
	}
	catch(IOException e) {
		System.out.println("File Not Found");
	}
	catch (Exception e) {
		System.out.println("Exception "+e);
	}
	driver.quit();
	
		PointerInput point = new PointerInput(Kind.PEN, "");
}
}