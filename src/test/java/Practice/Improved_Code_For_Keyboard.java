/*
 * package Practice;
 * 
 * import java.net.URL; import java.time.Duration; import java.util.Arrays;
 * import java.util.HashMap; import java.util.Map;
 * 
 * import org.openqa.selenium.By; import
 * org.openqa.selenium.interactions.PointerInput; import
 * org.openqa.selenium.interactions.Sequence; import
 * org.openqa.selenium.remote.DesiredCapabilities;
 * 
 * import io.appium.java_client.AppiumDriver; import
 * io.appium.java_client.MobileElement;
 * 
 * public class Improved_Code_For_Keyboard {
 * 
 * public static void main(String[] args) throws Exception { // String
 * deviceName = "YOUR_DEVICE_NAME"; String appPackage =
 * "com.samsung.android.messaging"; String appActivity =
 * "com.android.mms.ui.ConversationComposer";
 * 
 * DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
 * desiredCapabilities.setCapability("platformName", "Android"); //
 * desiredCapabilities.setCapability("deviceName", deviceName);
 * desiredCapabilities.setCapability("appPackage", appPackage);
 * desiredCapabilities.setCapability("appActivity", appActivity);
 * desiredCapabilities.setCapability("noReset", true);
 * desiredCapabilities.setCapability("autoGrantPermissions", true);
 * 
 * AppiumDriver<MobileElement> driver = new AppiumDriver<>(new
 * URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
 * 
 * MobileElement messageIcon =
 * driver.findElement(By.id("com.samsung.android.messaging:id/fab"));
 * messageIcon.click(); Thread.sleep(2000);
 * 
 * String input = "He told to coordinates"; input = input.toUpperCase(); for
 * (char character : input.toCharArray()) { tapKey(driver, character); }
 * 
 * driver.quit(); }
 * 
 * public static void tapKey(AppiumDriver<MobileElement> driver, char key) {
 * int[] coordinates = KeyboardCoordinates.getCoordinates(key); if
 * (coordinates[0] != 0 && coordinates[1] != 0) { performTap(driver,
 * coordinates[0], coordinates[1]); } else {
 * System.out.println("Key not recognized: " + key); } }
 * 
 * public static void performTap(AppiumDriver<MobileElement> driver, int x, int
 * y) { PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH,
 * "finger"); Sequence clickPosition = new Sequence(finger, 1);
 * clickPosition.addAction(finger.createPointerMove(Duration.ZERO,
 * PointerInput.Origin.viewport(), x, y))
 * .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
 * .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
 * driver.perform(Arrays.asList(clickPosition)); } }
 * 
 * class KeyboardCoordinates { private static final Map<Character, int[]>
 * CHARACTER_COORDINATES = new HashMap<>();
 * 
 * static { // ----> Alphabets CHARACTER_COORDINATES.put('A', new int[] { 120,
 * 1885 }); CHARACTER_COORDINATES.put('B', new int[] { 641, 2031 });
 * CHARACTER_COORDINATES.put('C', new int[] { 435, 2031 });
 * CHARACTER_COORDINATES.put('D', new int[] { 336, 1885 });
 * CHARACTER_COORDINATES.put('E', new int[] { 275, 1743 });
 * CHARACTER_COORDINATES.put('F', new int[] { 439, 1885 });
 * CHARACTER_COORDINATES.put('G', new int[] { 542, 1885 });
 * CHARACTER_COORDINATES.put('H', new int[] { 641, 1885 });
 * CHARACTER_COORDINATES.put('I', new int[] { 800, 1743 });
 * CHARACTER_COORDINATES.put('J', new int[] { 749, 1885 });
 * CHARACTER_COORDINATES.put('K', new int[] { 852, 1885 });
 * CHARACTER_COORDINATES.put('L', new int[] { 960, 1885 });
 * CHARACTER_COORDINATES.put('M', new int[] { 856, 2031 });
 * CHARACTER_COORDINATES.put('N', new int[] { 744, 2031 });
 * CHARACTER_COORDINATES.put('O', new int[] { 904, 1743 });
 * CHARACTER_COORDINATES.put('P', new int[] { 1013, 1743 });
 * CHARACTER_COORDINATES.put('Q', new int[] { 69, 1743 });
 * CHARACTER_COORDINATES.put('R', new int[] { 374, 1743 });
 * CHARACTER_COORDINATES.put('S', new int[] { 228, 1885 });
 * CHARACTER_COORDINATES.put('T', new int[] { 486, 1743 });
 * CHARACTER_COORDINATES.put('U', new int[] { 697, 1743 });
 * CHARACTER_COORDINATES.put('V', new int[] { 534, 2031 });
 * CHARACTER_COORDINATES.put('W', new int[] { 176, 1743 });
 * CHARACTER_COORDINATES.put('X', new int[] { 331, 2031 });
 * CHARACTER_COORDINATES.put('Y', new int[] { 572, 1743 });
 * CHARACTER_COORDINATES.put('Z', new int[] { 228, 2031 });
 * 
 * // ---->Numbers
 * 
 * CHARACTER_COORDINATES.put('0', new int[] { 1025, 1597 });
 * CHARACTER_COORDINATES.put('1', new int[] { 64, 1597 });
 * CHARACTER_COORDINATES.put('2', new int[] { 166, 1597 });
 * CHARACTER_COORDINATES.put('3', new int[] { 277, 1597 });
 * CHARACTER_COORDINATES.put('4', new int[] { 380, 1597 });
 * CHARACTER_COORDINATES.put('5', new int[] { 487, 1597 });
 * CHARACTER_COORDINATES.put('6', new int[] { 593, 1597 });
 * CHARACTER_COORDINATES.put('7', new int[] { 709, 1597 });
 * CHARACTER_COORDINATES.put('8', new int[] { 815, 1597 });
 * CHARACTER_COORDINATES.put('9', new int[] { 918, 1597 });
 * 
 * // ---->Action Buttons
 * 
 * CHARACTER_COORDINATES.put(' ', new int[] { 615, 2169 });
 * CHARACTER_COORDINATES.put('\b', new int[] { 994, 2031 }); // Backspace
 * 
 * // ---->Special Characters
 * 
 * }
 * 
 * public static int[] getCoordinates(char key) { return
 * CHARACTER_COORDINATES.getOrDefault(key, new int[] { 0, 0 }); } }
 */