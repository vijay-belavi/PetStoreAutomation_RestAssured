package TickPro;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class HideKeyBoard {

    public static void main(String[] args) throws Throwable{
    	DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("noReset", "true");

        // Initialize the AndroidDriver
        AndroidDriver driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), caps);
        
        // Assuming 'driver' is an instance of AppiumDriver
        driver.hideKeyboard();
        
        System.out.println("Keyboard hidden");

    }
}
