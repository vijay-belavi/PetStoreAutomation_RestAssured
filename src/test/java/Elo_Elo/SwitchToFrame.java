package Elo_Elo;
import io.appium.java_client.AppiumDriver;

public class SwitchToFrame {
    private AppiumDriver driver;

    public SwitchToFrame(AppiumDriver driver) {
        this.driver = driver;
    }

    public void switchToFrameById(String frameId) {
        driver.switchTo().frame(frameId);
    }

    // Add other methods if needed
}

