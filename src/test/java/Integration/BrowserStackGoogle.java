package Integration;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserStackGoogle {

    public static final String USERNAME = "vijaybelavi_lavdDc";
    public static final String AUTOMATE_KEY = "x3PsWNHFa3xZuhsx25ae";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY
            + "@hub-cloud.browserstack.com/wd/hub";

    public static void main(String[] args) throws Exception {
    	ChromeOptions options = new ChromeOptions();
        DesiredCapabilities caps = new DesiredCapabilities();
        Map browserStackOptions = new HashMap();
        caps.setCapability("os", "Window");
        caps.setCapability("os_version", "11");
        caps.setCapability("browserName", "Chrome");
        caps.setCapability("browserVersion", "latest");
        caps.setCapability("name", "Sample Test");
        options.setCapability("browserStack:options", browserStackOptions);
        
        URL url = new URL(URL);
        WebDriver driver = new RemoteWebDriver(url, options);
        driver.get("https://www.google.com");
        System.out.println(driver.getTitle());
        driver.quit();
    }
}