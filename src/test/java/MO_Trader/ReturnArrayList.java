package MO_Trader;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ReturnArrayList {
public static void main(String[] args) {

	WebDriver driver = new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	driver.manage().window().maximize();
	driver.get("https://www.icc-cricket.com/rankings/mens/player-rankings/odi/batting");
	ArrayList array = new ArrayList();
	array = test(driver, array);
	System.out.println(array);
	driver.quit();
}
public static ArrayList test(WebDriver driver, ArrayList array) {
	List<WebElement> elements = driver.findElements(By.xpath("//td[@class='table-body__cell rankings-table__name name']/a"));
	for (WebElement webElement : elements) {
		String elementText = webElement.getText();
		array.add(elementText);
	}
	System.out.println(array);
	return array;
}
}
