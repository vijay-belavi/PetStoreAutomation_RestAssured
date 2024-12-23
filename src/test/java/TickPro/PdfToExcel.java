package TickPro;
import java.io.File;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PdfToExcel {
public static void main(String[] args) throws InterruptedException, Exception {
	convertPdfToWord("C:\\Users\\User\\Downloads\\Transactions-OrderBook (2).pdf");
	
	
}
private static String convertPdfToWord(String pdfFilePath) throws Exception, InterruptedException {

	// Set download directory
	String downloadPath = System.getProperty("java.io.tmpdir");
	HashMap<String, Object> chromePrefs = new HashMap<>();
	chromePrefs.put("download.default_directory", downloadPath);

	ChromeOptions options = new ChromeOptions();
	options.setExperimentalOption("prefs", chromePrefs);
	options.addArguments("--headless");
	DesiredCapabilities cap = new DesiredCapabilities();
	cap.setBrowserName("chrome");
	cap.setCapability(ChromeOptions.CAPABILITY, options);

	WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
	driver.get("https://www.ilovepdf.com/pdf_to_excel");

	String fullPath = pdfFilePath;
	driver.findElement(By.xpath("//input[@type='file']")).sendKeys(pdfFilePath);
	Thread.sleep(2000);
	WebElement element = driver.findElement(By.id("processTask"));

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	wait.until(ExpectedConditions.visibilityOf(element));
	element.click();
	String fileFullPath = downloadPath + new File(fullPath).getName().replaceAll(".pdf", ".xlsx");
	System.out.println(fileFullPath);
	waitForDownload(fileFullPath);
	driver.close();
	return fileFullPath;
}	
private static void waitForDownload(String fullFilePath) throws InterruptedException   {
	File file = new File(fullFilePath);
	while (true) {
		try {
			if (file.exists())
				break;
			else
				throw new Exception("Need Some time to download");
		} catch (Exception e) {
			Thread.sleep(1000);
		}
	}
}
}