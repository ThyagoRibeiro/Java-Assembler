package util;

public class Conversoes {

	public static int bin2dec(int number) {

		return Integer.parseInt(Integer.toString(number), 2);
	}

	public static int bin2hex(int number) {

		return dec2hex(Integer.parseInt(Integer.toString(number), 2));
	}

	public static int dec2bin(int number) {

		return Integer.parseInt(Integer.toBinaryString(number));
	}

	public static int dec2hex(int number) {

		return Integer.parseInt(Integer.toHexString(number));
	}

	public static int hex2bin(int number) {

		return dec2bin(Integer.parseInt(Integer.toString(number), 16));
	}

	public static int hex2dec(int number) {

		return Integer.parseInt(Integer.toString(number), 16);
	}

	public static int[] int2array(int number) {

		String temp = Integer.toString(number);
		int[] newGuess = new int[temp.length()];

		for (int i = 0; i < temp.length(); i++) {
			newGuess[i] = temp.charAt(i) - '0';
		}
		return newGuess;
	}
}
