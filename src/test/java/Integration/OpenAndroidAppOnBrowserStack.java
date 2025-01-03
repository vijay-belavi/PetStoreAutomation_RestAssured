package Integration;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.android.AndroidDriver;

public class OpenAndroidAppOnBrowserStack {
    public static void main(String[] args) throws MalformedURLException {
        // Set your BrowserStack credentials
        String USERNAME = "newtest_kJ98gW";
        String ACCESS_KEY = "vu5WrGaKu7QD2qDAo1dG";

        // URL to BrowserStack hub
        String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";

        // Set the BrowserStack options
        Map<String, Object> browserstackOptions = new HashMap<>();
        browserstackOptions.put("userName", USERNAME);
        browserstackOptions.put("accessKey", ACCESS_KEY);
        browserstackOptions.put("deviceName", "Samsung Galaxy S23 Ultra");
        browserstackOptions.put("osVersion", "13.0");
        browserstackOptions.put("app", "bs://e8339cdbc1a00d07badaff93331518646fc3f0b5");
        
        
        
        // Set DesiredCapabilities
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("bstack:options", browserstackOptions);
        capabilities.setCapability("platformName", "ANDROID");

        // Create the driver instance
        AndroidDriver driver = (AndroidDriver) (new RemoteWebDriver(new URL(URL), capabilities));

        try {
            // Example: Interact with the app
            System.out.println("Driver initialized successfully on BrowserStack!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the app
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
