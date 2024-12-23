package Practice;

import java.util.Iterator;

public class forLoop {
public static void main(String[] args) {
	String input = "123";
	for (int i = input.length()-1; i >= 0; i--) {
		System.out.println(input.charAt(i));
	}
}
}