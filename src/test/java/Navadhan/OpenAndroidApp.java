package Navadhan;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class OpenAndroidApp {
    public static void main(String[] args) throws Throwable {
    	try {
            // Set the desired capabilities
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("appPackage", "com.acenapp.android");
            capabilities.setCapability("appActivity", "com.acenapp.android.commonactivity.SplashActivity");
            capabilities.setCapability("disableWindowAnimations", "true");
            capabilities.setCapability("autoGrantPermissions", "true");
            
            // Initialize the Appium driver
            AndroidDriver driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            
            System.out.println("Application Opened");
            driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}
    } 
}
