package Practice;

public class SwitchCase_Operator {
	public static void main(String[] args) {

		String input = "100 + 23 = ?";
		int sum = 0;
		int multiply = 1;
		int num = 0;
		
		if (input.contains("+")) {
			String[] numbers = input.split("\\+");
			for (int i = 0; i < numbers.length; i++) {
				String zx = numbers[i];
				zx = zx.replaceAll("[^0-9]", "");
				int a = Integer.parseInt(zx);
				sum = sum + a;
				num = sum;
			}
		} else if (input.contains("-")) {
			String[] numbers = input.split("\\-");	        
	        // Parse the operands as integers
	        int operand1 = Integer.parseInt(numbers[0].replaceAll("[^0-9]", ""));
	        int operand2 = Integer.parseInt(numbers[1].replaceAll("[^0-9]", ""));
	        
	        // Calculate the result using ternary operator
	        int result = (operand1 - operand2 >= 0) ? operand1 - operand2 : 0;
	        num = result;
		} else if (input.contains("*")) {
			String[] number = input.split("\\*");
			for (int i = 0; i < number.length; i++) {
				int a = Integer.parseInt(number[i].replaceAll("[^0-9]", ""));
				multiply = multiply * a;
			}
		}
		if (multiply > num) {
			sum = multiply;
		}
		else {
			sum = num;
		}
		System.out.println(sum);
	}
}