package Torus_1;

import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class SwipeUpStoreValues_Android {

	public static void main(String[] args) throws Throwable {

		HashSet hashSet = new HashSet();

		ArrayList<String> arrayList = new ArrayList();

		try {
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("platformName", "Android");

			AndroidDriver driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), caps);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			Thread.sleep(10000);
			String xpath = "//android.view.View[@content-desc=\"Mutual Fund\"]/../../following-sibling::android.view.View/android.view.View/android.view.View/android.widget.ImageView";
			String attributeValue = "content-desc";
			arrayList = swipeAndStoreValues(driver, hashSet, arrayList, xpath, attributeValue);
			/*
			 * for (String object : arrayList) { System.out.println(object); }
			 */
			System.out.println(arrayList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ArrayList swipeAndStoreValues(AndroidDriver driver, HashSet<String> uniqueElements,
			ArrayList<String> arrayList, String xpath, String attributeValues) {
		try {
			boolean newElementsFound;

			do {
				newElementsFound = false;
				List<WebElement> elementsValues = driver.findElements(By.xpath(xpath));

				for (WebElement webElement : elementsValues) {
					String attributeValue = webElement.getAttribute(attributeValues); // Change to your required
																						// attribute
					if (uniqueElements.add(attributeValue)) {
						arrayList.add(attributeValue);
						newElementsFound = true; // New element was added
					}
				}

				// Swipe up if new elements were found
				if (newElementsFound) {

					Dimension size = driver.manage().window().getSize();
					int width = size.getWidth();
					int height = size.getHeight();

					int startX = width / 2;
					int startY = (int) (height * 0.85);
					int endY = (int) (height * 0.2);

					// Using PointerInput to perform swipe
					PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
					Sequence swipe = new Sequence(finger, 0);

					swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(),
							startX, startY));
					swipe.addAction(finger.createPointerDown(0));
					swipe.addAction(finger.createPointerMove(Duration.ofMillis(2000), PointerInput.Origin.viewport(),
							startX, endY));
					swipe.addAction(finger.createPointerUp(0));

					driver.perform(List.of(swipe));
				}

			} while (newElementsFound); // Continue swiping until no new elements are found

		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}
}
