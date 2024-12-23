package MO_Trader;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Sorting_Of_Arrays {

	public static boolean isSortedAscending(int[] arr) {
		int n = arr.length;
		for (int i = 0; i < n - 1; i++) {
			if (arr[i] > arr[i + 1]) {
				return false;
			}
		}
		return true;
	}

	public static boolean isSortedDescending(int[] arr) {
		int n = arr.length;
		for (int i = 0; i < n - 1; i++) {
			if (arr[i] < arr[i + 1]) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) throws InterruptedException {
	
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the Sort Type");
		String sort = sc.nextLine();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://www.myntra.com/nike-shoes?rawQuery=Nike%20Shoes&sort=price_asc");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		Thread.sleep(10000);
		
		boolean booleanValue = false;

		List<WebElement> price = driver.findElements(By.xpath("//span[@class='product-discountedPrice']"));
		ArrayList<Integer> prices= new ArrayList<Integer>();

		for(WebElement cost:price) {	
			String elePrice=cost.getText();
			elePrice = elePrice.replaceAll("[^0-9]", "");
			int i = Integer.parseInt(elePrice);
			prices.add(i);
		}
		
		for(int k=0;k<prices.size();k++) {
			System.out.println(prices.get(k));
		}
		
		int a[]=new int[prices.size()];
		for(int j=0;j<prices.size();j++) {
			a[j]=prices.get(j); 	
		}
		
		if(sort.contains("asc")) {
			booleanValue = isSortedAscending(a);
			System.out.println("done with ascending");
		}
		else if (sort.contains("des")) {
			System.out.println(isSortedDescending(a));
		}
		
		System.out.println(booleanValue);
		System.out.println("Done");
		driver.quit();
	}
}
