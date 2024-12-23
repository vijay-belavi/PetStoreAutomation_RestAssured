package MobileWare;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserStackGetFileUploadDetails {
	public static final String USERNAME = "vijaybelavi_lavdDc";
	public static final String AUTOMATE_KEY = "x3PsWNHFa3xZuhsx25ae";
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";


	public static void main(String[] args) throws Throwable{
    	DesiredCapabilities capabilities = new DesiredCapabilities();
    	capabilities.setCapability("bstack:options", new JSONObject()
    	    .put("os", "Windows")
    	    .put("osVersion", "10")
    	    .put("projectName", "Sample Test")
    	    .put("buildName", "Sample_test")
    	    .put("uploadMedia", new JSONArray().put("media://64da07ac01d8ee812e47ba66149495b646b3275a"))
    	);
    	capabilities.setCapability("browserName", "IE");
    	capabilities.setCapability("browserVersion", "11.0");
    	ChromeOptions browserOptions = new ChromeOptions();
		browserOptions.setPlatformName("Windows 11");
		browserOptions.setBrowserVersion("latest");
		Map<String, Object> browserStackOptions = new HashMap<>();
		browserStackOptions.put("name", "Sample Test");
		browserOptions.setCapability("browserStack:options", browserStackOptions);
		browserOptions.setCapability("uploadMedia", new JSONArray().put("media://64da07ac01d8ee812e47ba66149495b646b3275a"));
		URL url = new URL(URL);
		WebDriver driver = new RemoteWebDriver(url, browserOptions);
    	try {
    	    driver.get("https://www.fileconvoy.com");
    	    WebElement uploadElement = driver.findElement(By.id("upfile_0"));
    	    uploadElement.sendKeys("C:\\Users\\User\\Downloads\\agentFile.csv");
    	    ((JavascriptExecutor) driver).executeScript("document.getElementById('readTermsOfUse').click();");
    	    driver.findElement(By.name("upload_button")).submit();
    	    WebElement topMessage = driver.findElement(By.id("TopMessage"));
    	    if (topMessage.getText().contains("successfully uploaded")) {
    	        ((JavascriptExecutor) driver).executeScript("browserstack_executor: {'action': 'setSessionStatus', 'arguments': {'status':'passed','reason': 'File upload successful'}}");
    	    } else {
    	        ((JavascriptExecutor) driver).executeScript("browserstack_executor: {'action': 'setSessionStatus', 'arguments': {'status':'failed','reason': 'File upload failed'}}");
    	    }
    	} catch (Exception e) {
    	    ((JavascriptExecutor) driver).executeScript("browserstack_executor: {'action': 'setSessionStatus', 'arguments': {'status':'failed','reason': 'Something wrong with script'}}");
    	} finally {
    	    driver.quit();
    	}
    }
}
