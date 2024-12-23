package MO_Trader;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import lombok.val;

public class SortAndVerify {
	public static boolean isSortedAscending(int[] arr) {
		int n = arr.length;
		for (int i = 0; i < n - 1; i++) {
			if (arr[i] > arr[i + 1]) {
				return true;
			}
		}
		return true;
	}

	public static boolean isSortedDescending(int[] arr) {
		int n = arr.length;
		for (int i = 0; i < n - 1; i++) {
			if (arr[i] < arr[i + 1]) {
				return false;
			}
		}
		return true;
	}
	public static void main(String[] args) throws Throwable {

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("appPackage", "mosl.powerapp.com.uat");
		capabilities.setCapability("appActivity", "mosl.powerapp.com.MainActivity");
		capabilities.setCapability("noReset", "true");

		// Initialize AndroidDriver instance
		AndroidDriver driver;
		try {
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

			Thread.sleep(2000);
			driver.findElement(By.xpath(
					"//android.view.View[@content-desc='Verify Date of Birth / PAN']/../android.widget.EditText"))
					.sendKeys("05/07/1994");

			driver.findElement(By.xpath("//android.widget.Button[@content-desc='Continue']")).click();

			Thread.sleep(15000);

			driver.findElement(By.xpath("//android.view.View[@content-desc='VIEW ALL']")).click();

			List<WebElement> element = driver.findElements(By.xpath(
					"//android.widget.Button[@content-desc='VIEW MORE']/following-sibling::android.view.View/android.view.View/android.view.View"));
			ArrayList<Integer> array = new ArrayList<Integer>();

			for (WebElement webElement : element) {
				Thread.sleep(5000);
				String elementAttribute = webElement.getAttribute("content-desc");

				if (elementAttribute.contains("Cr")) {
					Pattern pattern = Pattern.compile("\\d+\\.\\d+Cr");
					// Create a Matcher object and apply the pattern to the input string
					Matcher matcher = pattern.matcher(elementAttribute);
					if(matcher.find()) {
					String attribute = matcher.group();
					attribute = attribute.replaceAll("[a-z]", "");
					attribute = attribute.replaceAll("[A-Z]", "");
					int value = Integer.parseInt(attribute);
					value = value * 10000000;
					array.add(value);
					}
				} else if (elementAttribute.contains("L")) {
					Pattern pattern = Pattern.compile("\\d+\\.\\d+L");
					// Create a Matcher object and apply the pattern to the input string
					Matcher matcher = pattern.matcher(elementAttribute);
					if(matcher.find()) {
					String attribute = matcher.group();
					attribute = attribute.replaceAll("[A-Z]", "");
					System.out.println(attribute);
					int value = Integer.parseInt(attribute);
					value = value * 100000;
					array.add(value);
					}
					}
			}
			int[] arr = new int [array.size()];
			for(int j=0;j<array.size();j++) {
				arr[j]=array.get(j);
				System.out.println(arr[j]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
