package Integration;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SauceLabsSampleTest {

    public static final String USERNAME = "oauth-vijaybelavi108-b012a";
    public static final String ACCESS_KEY = "0bf86ae5-cb5c-4e1b-a33e-b6d05685aecd";
    public static final String SAUCE_URL = "https://ondemand.saucelabs.com/wd/hub";

    public static void main(String[] args) throws Throwable {
    	ChromeOptions browserOptions = new ChromeOptions();
    	browserOptions.setPlatformName("Windows 11");
    	browserOptions.setBrowserVersion("latest");
    	Map<String, Object> sauceOptions = new HashMap<>();
    	sauceOptions.put("username", "oauth-vijaybelavi1432-4d294");
    	sauceOptions.put("accessKey", "a263e61a-19ef-4897-bb88-acdeb2dee8af");
    	sauceOptions.put("build", "selenium-build-JKJR5");
    	sauceOptions.put("name", "Sample Test");
    	browserOptions.setCapability("sauce:options", sauceOptions);

    	URL url = new URL("https://ondemand.eu-central-1.saucelabs.com:443/wd/hub");
    	RemoteWebDriver driver = new RemoteWebDriver(url, browserOptions);
        driver.get("https://www.google.com");
        System.out.println(driver.getTitle());
        driver.quit();
    }
}
