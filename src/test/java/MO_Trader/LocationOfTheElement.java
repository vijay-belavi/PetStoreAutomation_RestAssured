package MO_Trader;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class LocationOfTheElement {
public static void main(String[] args) throws Throwable {
	DesiredCapabilities capabilities = new DesiredCapabilities();
	capabilities.setCapability("platformName", "Android");
	capabilities.setCapability("appPackage", "mosl.powerapp.com.uat");
	capabilities.setCapability("appActivity", "mosl.powerapp.com.MainActivity");
	capabilities.setCapability("noReset", "true");

	AppiumDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	Thread.sleep(3000);
	
	WebElement element = driver.findElement(By.xpath("//android.view.View[@content-desc='Verify Date of Birth / PAN']"));
	WebElement continueElement = driver.findElement(By.xpath("//android.widget.Button[@content-desc='Continue']"));
	
	Dimension deviceSize = driver.manage().window().getSize();
    int deviceWidth = deviceSize.getWidth() / 2;
    int deviceHeight = deviceSize.getHeight() / 2;

    System.out.println("Device Width: " + deviceWidth);
    System.out.println("Device Height: " + deviceHeight);
	
    int x = element.getLocation().getX() / 2;
    int y = element.getLocation().getY() / 2;
    
    System.out.println("Element Width: "+x);
    System.out.println("Element Height: "+y);  
    
    int offsetX = x - deviceWidth;
    int offsetY = y - deviceHeight;
    
    System.out.println(offsetX);
    System.out.println(offsetY);
    
    if(offsetX<0 && offsetY<0) {
    	System.out.println("Element is at top left corner");
    }
    else if (offsetX<0 && offsetY>0) {
    	System.out.println("Element is at bottom left corner");
    }
    else if (offsetX>0 && offsetY<0) {
    	System.out.println("Element is at Right top corner");
    }
    else if(offsetX>0 && offsetY>0) {
    	System.out.println("Element is at Right bottom corner");
    }
    
    driver.findElement(By.xpath("//android.widget.Button[@content-desc='Continue']/..//android.widget.EditText")).sendKeys("05071994");
    continueElement.click();
    Thread.sleep(10000);

    String a = "//android.view.View[@content-desc='Indices']";
    String b = "//android.view.View[@content-desc='VIEW ALL']";
    String c = "//android.widget.ImageView[@content-desc='Home']";
    String d = "//android.widget.ImageView[@content-desc='Trades']";
    String [] arr = {a,b,c,d};
    for (String string : arr) {
    	WebElement ele = driver.findElement(By.xpath(string));
    	
    	int eleX = ele.getLocation().getX();
        int eleY = ele.getLocation().getY();
        
        System.out.println("Element Width: "+eleX);
        System.out.println("Element Height: "+eleY);
        
        int offsetXx = eleX - deviceWidth;
        int offsetYy =  eleY - deviceHeight;
        
        if(offsetXx<0 && offsetYy<0) {
        	System.out.println("Element is at top left corner");
        }
        else if (offsetXx<0 && offsetYy>0) {
        	System.out.println("Element is at bottom left corner");
        }
        else if (offsetXx>0 && offsetYy<0) {
        	System.out.println("Element is at Right top corner");
        }
        else if(offsetXx>0 && offsetYy>0) {
        	System.out.println("Element is at Right bottom corner");
        }
    }    
}
}
