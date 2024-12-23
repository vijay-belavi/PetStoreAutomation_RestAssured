package Torus_1;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class CompareImagesWithSikuliAppium {
    public static void main(String[] args) throws Throwable{
        try {
            // Set up Appium driver
			/*
			 * DesiredCapabilities caps = new DesiredCapabilities();
			 * caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
			 * 
			 * AndroidDriver driver = new AndroidDriver(new
			 * URL("http://127.0.0.1:4723/wd/hub"), caps);
			 */
            // Take a screenshot of the main image on the app
          //  File mainImageFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String mainImagePath = 	"C:\\Users\\User\\Downloads\\MainImage.png";
           // mainImageFile.renameTo(new File(mainImagePath));

            // Path to the sub-image
            String subImagePath = "C:\\Users\\User\\Downloads\\subImage.png";

            // SikuliX comparison
            Screen screen = new Screen();

            // Load main and sub-images
            Pattern subImagePattern = new Pattern(subImagePath);
            Match match = screen.exists(subImagePattern, 5); // Wait for 5 seconds to find the sub-image

            if (match != null) {
                System.out.println("Sub-image found in the main image!");
                System.out.println("Match found at: " + match.getRect());
            } else {
                System.out.println("Sub-image not found in the main image.");
            }

            // Close the Appium session
          //  driver.quit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
