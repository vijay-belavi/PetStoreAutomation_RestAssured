/*
 * package Practice;
 * 
 * import java.net.URL;
 * 
 * import org.openqa.selenium.By; import
 * org.openqa.selenium.remote.DesiredCapabilities;
 * 
 * import io.appium.java_client.AppiumDriver; import
 * io.appium.java_client.MobileElement; import
 * io.appium.java_client.android.AndroidDriver;
 * 
 * 
 * public class Mint_keyboard { public static void main(String[] args) throws
 * Throwable { String appPackage = "com.samsung.android.messaging"; String
 * appActivity = "com.samsung.android.messaging.ui.view.search.SearchActivity";
 * 
 * DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
 * desiredCapabilities.setCapability("platformName", "Android"); //
 * desiredCapabilities.setCapability("deviceName", deviceName);
 * desiredCapabilities.setCapability("appPackage", appPackage);
 * desiredCapabilities.setCapability("appActivity", appActivity);
 * desiredCapabilities.setCapability("noReset", true);
 * desiredCapabilities.setCapability("autoGrantPermissions", true);
 * 
 * AppiumDriver<MobileElement> driver = new AndroidDriver<MobileElement>(new
 * URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
 * 
 * MobileElement messageIcon =
 * driver.findElement(By.id("com.samsung.android.messaging:id/fab"));
 * messageIcon.click(); Thread.sleep(2000); } }
 */