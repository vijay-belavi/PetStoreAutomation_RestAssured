package Practice;

import java.util.Random;

public class GenerateRandomNumberWithinSpecificRange {
	public static void random(String num1, String num2)
	{
		Random random = new Random();
		if(num1.contains(".") || num2.contains(".")) { 
			  System.out.println("Double Format");
			  double a1 = Double.parseDouble(num1); 
			  double b1 = Double.parseDouble(num2);
			  int a2 = (int) a1; 
			  int b2 = (int) b1;
			  while(1==1) { 
				  	int randomNumber = random.nextInt(b2) + a2;
				  	if(a2 < randomNumber && randomNumber < b2) {
				  		System.out.println(randomNumber);
				  		break;
				  	}
		  } 
		} 
		else if(!num1.contains(".") && !num2.contains(".")) 
		{
			int a = Integer.parseInt(num1); // Lower bound 
			int b =	Integer.parseInt(num2);
			while(1==1) {
				
				int randomNumber = random.nextInt(b) + a;
			  	if(a < randomNumber && randomNumber < b) {
			  		break;
			  	}
			}
		}
	}
	public static void main(String[] args) {
		String num1 = "18"; 
		String num2 = "27";
		random(num1, num2);
	}
}