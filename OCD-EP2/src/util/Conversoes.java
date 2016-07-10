package util;

public class Conversoes {

	public static int bin2dec(String number) {
		return (int) Long.parseLong(number, 2);
	}

	public static String bin2hex(String number) {

		return Long.toHexString(Long.parseLong(number, 2)).toUpperCase();
	}

	public static String dec2bin(int number) {

		return Integer.toBinaryString(number);
	}

	public static String dec2hex(int number) {

		return Integer.toHexString(number).toUpperCase();
	}

	public static String hex2bin(String number) {

		return Long.toBinaryString(Long.parseLong(number, 16));
	}

	public static int hex2dec(String number) {

		return Integer.parseInt(number, 16);
	}

	public static int[] int2array(int number) {

		String temp = Integer.toString(number);
		int[] newGuess = new int[temp.length()];

		for (int i = 0; i < temp.length(); i++) {
			newGuess[i] = temp.charAt(i) - '0';
		}
		return newGuess;
	}

	public static String array2String(int[] array) {

		String number = "";

		for (int i : array) {
			number = number + i;
		}

		return number;

	}

	public static String array2String(int[] array, String separator) {

		String number = "{" + array[0];

		for (int i = 1; i < array.length; i++) {
			number = number + separator + array[i];
		}

		number = number + "}";

		return number;

	}

	public static int[] string2IntArray(String number) {

		int[] array = new int[number.length()];

		for (int i = 0; i < array.length; i++) {
			array[i] = number.charAt(i) - 48;
		}

		return array;
	}

	public static String changeStringLenght(String number, int radix) {

		switch (radix) {
		case 2:

			if (number.length() < 16) {

				char[] charArray = "0000000000000000".toCharArray();

				for (int i = 0; i < number.length(); i++) {
					charArray[15 - i] = number.charAt((number.length() - 1) - i);
				}

				return String.valueOf(charArray);

			} else {

				String newNumber = "";
				for (int i = 0; i < 16; i++) {
					newNumber = number.charAt((number.length() - 1) - i) + newNumber;
				}
				return newNumber;

			}

		case 10:

			if (number.length() < 5) {
				boolean negative = false;

				if (number.contains("-")) {
					number = number.replace("-", "");
					negative = true;
				}

				char[] charArray = " 00000".toCharArray();

				for (int i = 0; i < number.length(); i++) {
					charArray[5 - i] = number.charAt((number.length() - 1) - i);
				}

				if (negative)
					charArray[0] = '-';

				return String.valueOf(charArray);
			}

		case 16:

			if (number.length() < 4) {

				char[] charArray = "0000".toCharArray();

				for (int i = 0; i < number.length(); i++) {
					charArray[3 - i] = number.charAt((number.length() - 1) - i);
				}

				return String.valueOf(charArray);

			} else {

				String newNumber = "";
				for (int i = 0; i < 4; i++) {
					newNumber = number.charAt((number.length() - 1) - i) + newNumber;
				}
				return newNumber;

			}
		}

		return number;
	}

	
	
	public static int[] changeSizeArray(int[] number, int n, int c) {

		int[] array = new int[n];

		for (int i = 0; i < array.length; i++) {
			array[i] = c;
		}

		if (n > number.length) {

			for (int i = 0; i < number.length; i++) {
				array[(array.length - 1) - i] = number[(number.length - 1) - i];
			}

		} else {
			for (int i = 0; i < array.length; i++) {
				array[(array.length - 1) - i] = number[(number.length - 1) - i];
			}
		}
		return array;
	}

}
