package Practice;

import java.util.Random;

public class GenerateRandomNumber {
public static void main(String[] args) {

	int b = 10;
	double a = Math.pow(10, b);
	while(1==1) {
	Random rmd = new Random();
	int randomNumber = rmd.nextInt();
	if (randomNumber<a && randomNumber>0) {
		String randomNumber1 = Integer.toString(randomNumber);
		if(randomNumber1.charAt(0)=='9') {
		System.out.println(randomNumber1);
		break;
		}
	}
	}
}
}
