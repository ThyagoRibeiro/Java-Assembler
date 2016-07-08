package util;

public class Conversoes {

	public static int bin2dec(String number) {

		return Integer.parseInt(number, 2);
	}

	public static String bin2hex(String number) {

		return dec2hex(Integer.parseInt(number, 2)).toUpperCase();
	}

	public static int dec2bin(int number) {

		return Integer.parseInt(Integer.toBinaryString(number));
	}

	public static String dec2hex(int number) {

		return Integer.toHexString(number).toUpperCase();
	}

	public static int hex2bin(String number) {

		return dec2bin(Integer.parseInt(number, 16));
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
	
	public static String bin(int[] array){
		
		String number = "";
		
		for (int i : array) {
			number = number + i;
		}
		
		return number;
		
	}
	
	
}
