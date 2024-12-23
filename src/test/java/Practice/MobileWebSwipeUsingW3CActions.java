package Practice;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;

import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class MobileWebSwipeUsingW3CActions  {

    public static void main(String[] args) throws Throwable {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("automationName", "UiAutomator2");

        try {
            AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

            // Open a URL
            driver.get("http://www.cricbuzz.com");

            // Wait for the page to load
            Thread.sleep(5000);

            // Perform swipe action
            swipe(driver, 0.8, 0.5, 0.2, 0.5);

            // Quit the driver
            driver.quit();

        } catch (MalformedURLException | InterruptedException e) {
            e.printStackTrace();
        }
    }
	public static void swipe(AndroidDriver driver, double startX, double startY, double endX, double endY) {
        int startXCoord = (int) (startX * driver.manage().window().getSize().width);
        int startYCoord = (int) (startY * driver.manage().window().getSize().height);
        int endXCoord = (int) (endX * driver.manage().window().getSize().width);
        int endYCoord = (int) (endY * driver.manage().window().getSize().height);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startXCoord, startYCoord))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), endXCoord, endYCoord))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(swipe));
    }
}
