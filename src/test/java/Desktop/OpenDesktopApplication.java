package Desktop;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.windows.WindowsDriver;

public class OpenDesktopApplication {
	public static void main(String[] args) throws Throwable {

	
		try {

			// Set Desired Capabilities
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("appium:platformName", "Windows");
			capabilities.setCapability("appium:app", "C:\\Rupeeseed\\ADMIN\\RRS.exe");

			// Initialize Windows Driver
			WindowsDriver driver = new WindowsDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			Thread.sleep(5000);

			// Quit driver
			driver.quit();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());

		}

	}
}
