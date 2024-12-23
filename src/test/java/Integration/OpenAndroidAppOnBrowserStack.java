package Integration;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class OpenAndroidAppOnBrowserStack {
    public static void main(String[] args) throws MalformedURLException {
        // Set your BrowserStack credentials
        String USERNAME = "newtesting_NOZHxI";
        String ACCESS_KEY = "38KLHqvsVsKrQFDMFNp1";

        // URL to BrowserStack hub
        String URL = "http://" + USERNAME + ":" + ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";

        // Set the DesiredCapabilities using W3C syntax
        Map<String, Object> browserstackOptions = new HashMap<String, Object>();
        browserstackOptions.put("userName", USERNAME);
        browserstackOptions.put("accessKey", ACCESS_KEY);
        browserstackOptions.put("appium:app", "bs://c63f8c1ff88d59e2a653c41326c97081f4a95ef4"); // Replace with the app_url received from the upload
        browserstackOptions.put("deviceName", "Samsung Galaxy S23 Ultra");
        browserstackOptions.put("os_version", "13.0");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("bstack:options", browserstackOptions);
        capabilities.setCapability("platformName", "Android");

        // Create the driver instance
        AppiumDriver driver = new AndroidDriver(new URL(URL), capabilities);

        // Interact with the app (add your test code here)
        // Example: Find an element and interact with it
        // driver.findElement(By.id("element_id")).click();

        // Close the app
        driver.quit();
    }
}
