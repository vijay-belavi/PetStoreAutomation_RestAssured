package testNg;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class Test1 extends BaseClass{
	@Test
	public void test() {
		driver.get("https://www.google.com/");
		System.out.println(driver.getTitle());
	}
}