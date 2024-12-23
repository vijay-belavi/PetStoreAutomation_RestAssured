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

public class Sort_Alphabets {
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

			Thread.sleep(20000);

			List<WebElement> element = driver.findElements(By.xpath(
					"//android.widget.ImageView[@content-desc='Holdings']/following-sibling::android.view.View/android.view.View/android.view.View/android.widget.ImageView"));
			ArrayList<String> array = new ArrayList<String>();
			System.out.println(element.size());
			for (WebElement webElement : element) {
				String elementAttribute = webElement.getAttribute("content-desc");
				array.add(elementAttribute);
			}
			String[] arr = new String [array.size()];
			for(int j=0;j<array.size();j++) {
				arr[j]=array.get(j);
				
			}
			for (int i = 0; i < arr.length-1; i++) {
				
					char ch = arr[i].charAt(0);
					char sh = arr[i+1].charAt(0);
					if(ch < sh) {
						System.out.println(ch+" is lesser than "+sh);
					}
					else if (ch == sh) {
						
						char ch1 = arr[i].charAt(1);
						char sh1 = arr[i+1].charAt(1);
						if (ch1 <= sh1) {
							System.out.println(ch1+" is lesser than "+sh1);
						}
					}
					else {
						System.out.println(ch+" is greater than "+sh);
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
