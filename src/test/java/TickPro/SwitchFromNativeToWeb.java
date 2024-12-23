package TickPro;
import java.net.URL;
import java.util.Set;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.SupportsContextSwitching;

public class SwitchFromNativeToWeb {
    public static void main(String[] args) throws Throwable {
        // Set the desired capabilities for your Android WebDriver
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("noReset", "true");

        // Initialize the AndroidDriver
        AppiumDriver driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), caps);
        Thread.sleep(5000);
        Set<String> contextHandles = ((SupportsContextSwitching) driver).getContextHandles();
		for (String contextHandle : contextHandles) {
			System.out.println(contextHandle);
		}
        
        //driver.quit();
    }
}
