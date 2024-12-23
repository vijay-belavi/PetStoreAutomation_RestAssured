package MobileWare;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class test {
    public static final String USERNAME = "newtest_kJ98gW";
    public static final String AUTOMATE_KEY = "vu5WrGaKu7QD2qDAo1dG";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    public static void main(String[] args) throws Throwable {

        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 11");
        browserOptions.setBrowserVersion("latest");
        Map<String, Object> browserStackOptions = new HashMap<>();
        browserStackOptions.put("name", "Sample Test");
        browserOptions.setCapability("browserStack:options", browserStackOptions);
        URL url = new URL(URL);

        WebDriver driver = new RemoteWebDriver(url, browserOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();

        driver.get("https://the-internet.herokuapp.com/upload");
		/*
		 * driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys(
		 * "sujan.r@fireflink.com");
		 * driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(
		 * "q$nsCjVQ2T"); String captcha =
		 * driver.findElement(By.xpath("//span[@id='captchaText']")).getText().
		 * replaceAll("[^a-zA-Z0-9]", "");
		 * driver.findElement(By.xpath("//input[@placeholder='Captcha']")).sendKeys(
		 * captcha); driver.findElement(By.xpath("//button[text()='Login']")).click();
		 * 
		 * // Hover over the 'More' menu WebElement elementToHover =
		 * driver.findElement(By.xpath("//a[@id='reports' and contains(text(),'More')]")
		 * ); Actions actions = new Actions(driver);
		 * actions.moveToElement(elementToHover).perform();
		 * 
		 * // Wait for and click the 'Bulk Upload' option WebDriverWait wait = new
		 * WebDriverWait(driver, Duration.ofSeconds(10)); WebElement clickableElement =
		 * wait.until(ExpectedConditions.elementToBeClickable( By.
		 * xpath("//div[@aria-labelledby='reports']//a[contains(text(),'Bulk Upload')]")
		 * )); JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		 * jsExecutor.executeScript("arguments[0].click();", clickableElement);
		 * 
		 * // Select 'Agent' from the dropdown WebElement element =
		 * driver.findElement(By.xpath("//select[@id='fileTypeList']")); Select select =
		 * new Select(element); select.selectByVisibleText("Agent");
		 */
        // Upload the file to BrowserStack
        String apiEndpoint = "https://api-cloud.browserstack.com/automate/upload-media";
        String filePath = "C:\\Users\\User\\Downloads\\agentFile.csv"; // Replace with your file path
       // String mediaUrl = uploadFile(apiEndpoint, filePath, USERNAME, AUTOMATE_KEY);

            // Use the media URL to upload the file in your test
            WebElement uploadElement = driver.findElement(By.xpath("//input[@type='file']"));
            //System.out.println(mediaUrl);
            uploadElement.click();
            uploadElement.sendKeys("media://d6fd63e763e1f2f490ac70df8c85ee7ba0a2ff8f");
            System.out.println("File Uploaded");// Give time for the upload
        
        driver.quit();
    }

    public static String uploadFile(String apiEndpoint, String filePath, String username, String accessKey)
            throws IOException {
        File file = new File(filePath);
        String boundary = "Boundary-" + System.currentTimeMillis();
        URL url = new URL(apiEndpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        String auth = username + ":" + accessKey;
        String encodedAuth = java.util.Base64.getEncoder().encodeToString(auth.getBytes());
        connection.setRequestProperty("Authorization", "Basic " + encodedAuth);
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

        try (DataOutputStream out = new DataOutputStream(connection.getOutputStream())) {
            out.writeBytes("--" + boundary + "\r\n");
            out.writeBytes("Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getName() + "\"\r\n");
            out.writeBytes("Content-Type: " + Files.probeContentType(file.toPath()) + "\r\n\r\n");
            Files.copy(file.toPath(), out);
            out.writeBytes("\r\n--" + boundary + "--\r\n");
        }

        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                String jsonResponse = response.toString();
                // Extract media_url from the JSON response
                return jsonResponse;
            }
        } else {
            System.out.println("Failed to upload file, Response Code: " + responseCode);
        }
        connection.disconnect();
        return null;
    }
}
