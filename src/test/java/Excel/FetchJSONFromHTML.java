package Excel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FetchJSONFromHTML {

    public static void main(String[] args) {
    	 WebDriver driver = new ChromeDriver();
         driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
         driver.manage().window().maximize();
         
         driver.get("https://www.fireflink.com/");

        // Extract the HTML source
        String htmlSource = driver.getPageSource();

        // Define a regex pattern to find script tags with JSON content
        Pattern pattern = Pattern.compile("<script ctn-preloader=\"application/json\">(.*?)</script>", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(htmlSource);

        // Loop through matches and extract JSON data
        if(matcher.find()) {
            String jsonData = matcher.group(1);
            System.out.println("Found JSON data: " + jsonData);
        }
        else {
        	System.out.println("JSON data NOT Found");
        }
        driver.quit();
    }
}