package Practice;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class NoorplayAndroid {
public static void main(String[] args) throws InterruptedException, MalformedURLException {
	
	DesiredCapabilities capabilities = new DesiredCapabilities();
	capabilities.setCapability("platformName", Platform.ANDROID);
	//capabilities.setCapability("appPackage", "ps.goldendeveloper.alnoor");
	//capabilities.setCapability("appActivity", "com.mobiotics.vlive.android.ui.main.MainActivity");
	capabilities.setCapability("noReset", "true");


	String s1="http://localhost:4723/wd/hub";
	AndroidDriver driver = new AndroidDriver(new URL(s1),capabilities);	  
	Thread.sleep(15000);
	System.out.println("Hello");
	driver.quit();	//driver.findElement(By.xpath("//android.widget.FrameLayout[@content-desc='MORE']")).click();
}
}
