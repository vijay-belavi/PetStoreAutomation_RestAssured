package MO_Trader;

import java.util.ArrayList;
import java.util.List;

public class Comparing_list1_with_list2 {
	public static void main(String[] args) {
		List l1 = new ArrayList();
		l1.add("Hi");
		l1.add("Hlo");
		l1.add("bye");

		List l2 = new ArrayList();
		l2.add("Hi");
		boolean booleanValue = false;
		
		boolean tezt = test(l1, l2, booleanValue);
		System.out.println(tezt);
	}
	public static boolean test(List l1, List l2, boolean booleanValue) {
		for (Object object2 : l2) {
			if (l1.contains(object2)) {
				booleanValue = true;
			}
			else {
				booleanValue = false;
			}
		}
		return booleanValue;
	}
}
