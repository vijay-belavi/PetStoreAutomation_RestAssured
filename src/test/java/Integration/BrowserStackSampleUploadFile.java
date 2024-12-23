package Integration;

import java.io.File;
import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserStackSampleUploadFile {
	public static final String AUTOMATE_USERNAME = "vijaybelavi_K9zSl1";
	public static final String AUTOMATE_KEY = "S7q9rdDu8brvtNcqWWmt";
	public static final String URL = "https://" + AUTOMATE_USERNAME + ":" + AUTOMATE_KEY
			+ "@hub-cloud.browserstack.com/wd/hub";

	public static void main(String[] args) throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("browserName", "chrome");
		// capabilities.setCapability("browserVersion", "11.0");
		HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
		browserstackOptions.put("os", "Windows");
		browserstackOptions.put("osVersion", "11");
		// browserstackOptions.put("projectName", "Sample Test");
		// browserstackOptions.put("buildName", "Sample_test");
		capabilities.setCapability("bstack:options", browserstackOptions);
		RemoteWebDriver driver = new RemoteWebDriver(new URL(URL), capabilities);
		driver.setFileDetector(new LocalFileDetector());
		driver.get("https://www.fileconvoy.com/");
		driver.findElement(By.id("upfile_0")).sendKeys("C:\\Users\\User\\Downloads\\browserstack upload.txt"); // File

		File file = driver.getScreenshotAs(OutputType.FILE);

		File f = new File("C:\\Users\\User\\OneDrive\\Desktop\\New folder\\Test Images\\varun.png");
		FileHandler.copy(file, f);
// path
		// in
		// remote
//																												// machine
//		driver.findElement(By.id("readTermsOfUse")).click();
//		driver.findElement(By.name("upload_button")).submit();
//		JavascriptExecutor jse = (JavascriptExecutor) driver;
//		try {
//			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("TopMessage")));
//			if (driver.findElement(By.id("TopMessage")).getText().contains("successfully uploaded")) {
//				jse.executeScript(
//						"browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"File uploaded successfully\"}}");
//			} else {
//				jse.executeScript(
//						"browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"failed\", \"reason\": \"File upload failed\"}}");
//			}
//		} catch (Exception e) {
//			jse.executeScript(
//					"browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"failed\", \"reason\": \"File could not be uploaded in 5 seconds\"}}");
//		}
		driver.quit();
	}
}