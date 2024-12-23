package testNg;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Test2 {
	@BeforeMethod
	public void beforeMethod() {
		System.out.println("Before Method");
	}

	@Test
	public void test() {
		System.out.println("Test");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("After Method");
	}
}