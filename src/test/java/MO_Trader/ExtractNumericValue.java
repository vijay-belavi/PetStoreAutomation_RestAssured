package MO_Trader;

public class ExtractNumericValue {
	public static void main(String[] args) {
		String input = "SENSEX 65,410.26 334.44 (0.51%)";
		String[] array = input.split(" ");
		for (int i = 0; i < array.length; i++) {
			if (array[i].contains(",") && array[i].contains(".")) {
				System.out.println(array[i]);
				if (!array[i].contains("-")) {
					double indicesValue = Integer.parseInt(array[i]);
				} else if (array[i].contains("-")) {
					double indicesValue = Integer.parseInt(array[i]);

				}
			}
		}
	}

	public static void positive() {

	}

	public static void negative() {

	}
}