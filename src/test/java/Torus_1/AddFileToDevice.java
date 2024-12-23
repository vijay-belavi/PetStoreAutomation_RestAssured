package Torus_1;

import java.io.File;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class AddFileToDevice {
	public static void main(String[] args) throws Throwable {

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");

		AndroidDriver driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		String inputFilePath = "C:\\Users\\User\\Downloads\\Image.png";
		String outputFilePath = "/sdcard/Download/Esign.png";
		String string3 = "";

		try {
			File imageFile = new File(inputFilePath);
			if (imageFile.exists()) {
				driver.pushFile(outputFilePath, imageFile);
				System.out.println("File pushed successfully to the device.");
			} else {
				System.out.println("File not found at the specified input path.");
			}
		} catch (Exception e) { // TODO: handle exception }

		}

	}
}
