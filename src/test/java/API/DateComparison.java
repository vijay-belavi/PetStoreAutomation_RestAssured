package API;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DateComparison {
    public static void main(String[] args) throws ParseException {
        // Assume you have a WebDriver instance
        WebDriver driver = new ChromeDriver();

        // Locate "from" and "to" date input fields
        WebElement fromDateElement = driver.findElement(By.id("fromDate"));
        WebElement toDateElement = driver.findElement(By.id("toDate"));

        // Get date strings from input fields
        String fromDateStr = fromDateElement.getAttribute("value");
        String toDateStr = toDateElement.getAttribute("value");

        // Convert date strings to Date objects
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        Date fromDate = format.parse(fromDateStr);
        Date toDate = format.parse(toDateStr);

        // Compare dates
        if (fromDate.compareTo(toDate) > 0) {
            System.out.println("From date is greater than To date.");
        } else {
            System.out.println("From date is not greater than To date.");
        }
    }
}
