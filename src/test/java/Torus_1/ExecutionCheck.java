package Torus_1;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class ExecutionCheck {

	public static void main(String[] args) throws Throwable {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("appium:platformName", "Android");
		capabilities.setCapability("appium:udid", "RZ8T60ALREV");

		AndroidDriver driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		System.out.println(capabilities);
		
//		String driverUrl = ((RemoteWebDriver) driver).getCommandExecutor().getAddressOfRemoteServer().toString();

		if (capabilities.getCapability("appium:udid") != null) {
			System.out.println("Physical Device");
		} else {
			System.out.println("Cloud Device");
		}
	}
}
