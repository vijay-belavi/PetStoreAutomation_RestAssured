package Integration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class LambdaTestSampleTest {

    public static final String USERNAME = "vijaybelavi1432";
    public static final String ACCESS_KEY = "ryy3yq9g4wRmHpBRwYRNEn7yIYBJxNeyRSG7DIXycPAyXI3L5b";
    public static final String LT_URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@hub.lambdatest.com/wd/hub";

    public static void main(String[] args) throws Throwable {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserName", "chrome");
        caps.setCapability("version", "latest");
        caps.setCapability("platform", "WIN10");
        caps.setCapability("build", "Sample LambdaTest Test");
        caps.setCapability("name", "LambdaTest Example Test");

        RemoteWebDriver driver = new RemoteWebDriver(new URL(LT_URL), caps);
        driver.get("https://www.google.com");
        System.out.println(driver.getTitle());
        driver.quit();
    }
}
