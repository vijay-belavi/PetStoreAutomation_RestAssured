package Navadhan;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class FetchAPItoken {
	public static void main(String[] args) throws Throwable {
		try {
			 // Set the desired capabilities
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("platformName", "Android");
			long wait = 100;
			capabilities.setCapability("waitForIdleTimeout", wait);
			capabilities.setCapability("disableWindowAnimations", "true");
			capabilities.setCapability("autoGrantPermissions", "true");

			// Initialize the Appium driver
			AndroidDriver driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			System.out.println("Application Opened");
			WebElement username = driver.findElement(
					By.xpath("//android.widget.EditText[@resource-id='com.acenapp.android:id/ac_login_et_username']"));
			username.click();
			username.sendKeys("ne10205");
			driver.hideKeyboard();
			WebElement password = driver.findElement(By.xpath("//android.widget.EditText[@text='Password']"));
			password.click();
			password.sendKeys("Test@123");
			driver.hideKeyboard();
			WebElement loginButton = driver.findElement(
					By.xpath("//android.widget.Button[@resource-id='com.acenapp.android:id/ac_login_btn_login']"));
			loginButton.click();

			WebElement saveButton = driver
					.findElement(By.xpath("//android.widget.Button[@resource-id='android:id/button1']"));
			saveButton.click();
			 // Fetch the logcat output
            Process logcat = Runtime.getRuntime().exec("adb logcat -d");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(logcat.getInputStream()));
            StringBuilder log = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                log.append(line).append("\n");
            }

            String logMessage = "";
            // For example, search for HTTP response logs
            String logOutput = log.toString();
            String[] logLines = logOutput.split("\n");
            for (String logLine : logLines) {
                if (logLine.contains("OkHttp") && logLine.contains("api-token")) {
                	logMessage = logLine.replaceAll("^[{}]", "");
                    System.out.println("Logline msg: "+logMessage);
                    break;
                }
            }
            
            String regex = "api-token:\\s*(.*)$";

            // Use Pattern and Matcher to extract the token
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(logMessage);
            String apiToken = "";
            if (matcher.find()) {
                apiToken = matcher.group(1); // Extract the captured group
            }
            System.out.println("api-token: " + apiToken);
            System.out.println("Executed");
            driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}