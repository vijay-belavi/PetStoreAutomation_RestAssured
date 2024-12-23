package Navadhan;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class SwipeUpUntilElementIsVisible {
	
    public static void main(String[] args) throws Throwable {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("noReset", "true"); // Change as needed

        AppiumDriver driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
        String usernameInComment = "//android.widget.TextView[@resource-id='com.acenapp.android:id/textHouseHolId']";
        ArrayList<String> arrayList = new ArrayList();
        String  username = "";
        String userNames = "MP003LE003782";
        boolean isDisplayed = true;
        try {
        	test(driver, usernameInComment, arrayList, username, userNames, isDisplayed);
        	System.out.println("Execution completed");
		} catch (Exception e) {
			System.out.println("Failed To Swipe");
			e.printStackTrace();
		}
        
    }

    public static void swipe(AppiumDriver driver, int startX, int startY, int endX, int endY, Duration duration) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(duration, PointerInput.Origin.viewport(), endX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(swipe));
    }
    
    public static void test (AppiumDriver driver, String usernameInComment, ArrayList<String> arrayList, String username, String userNames, boolean isDisplayed)
	{
    	 //Jaswinder
		try{
			Dimension size = driver.manage().window().getSize();
        	int startX =size.getWidth()/2;
        	int startY =size.getHeight();
        	int endX =size.getWidth()/2;
        	int endY =size.getHeight()/2;
        	int num = 0;
        	
        	while(driver.findElement(By.xpath(usernameInComment)).isDisplayed() && isDisplayed) {
        		if (driver.findElement(By.xpath(usernameInComment)).isDisplayed()) {
        			List<WebElement> elements = driver.findElements(By.xpath(usernameInComment));
        			for (WebElement webElement : elements) {
        				username = webElement.getText();
        				arrayList.add(username);
        				if (username.contains(userNames)) {
    						System.out.println(username);
    						isDisplayed=false;
    						break;
    					}
					}
				}
        		if (!arrayList.get(arrayList.size()-1).contains(userNames)) {
        			swipe(driver, startX, startY, endX, endY, Duration.ofSeconds(1));
				}
        	}
		}catch(StaleElementReferenceException E) {
			test(driver, usernameInComment, arrayList, username, userNames, isDisplayed);
		}catch(NoSuchElementException e) {
			System.out.println("NoSuchElment Exception: "+e);
		}
	}
}