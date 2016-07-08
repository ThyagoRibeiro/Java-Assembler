package util;

public class Conversoes {

	public static int bin2dec(String number) {
		return (int) Long.parseLong(number, 2);
	}

	public static String bin2hex(String number) {

		return Long.toHexString(Long.parseLong(number,2)).toUpperCase();
	}

	public static String dec2bin(int number) {

		return Integer.toBinaryString(number);
	}

	public static String dec2hex(int number) {

		return Integer.toHexString(number).toUpperCase();
	}

	public static String hex2bin(String number) {

		return Long.toBinaryString(Long.parseLong(number,16));
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
	
	public static String array2String(int[] array){
		  
		  String number = "";
		  
		  for (int i : array) {
		   number = number + i;
		  }
		  
		  return number;
		  
	}
	
	public static int[] string2IntArray(String number){
		  
		  int[] array = new int[number.length()];
		  
		  for (int i = 0; i < array.length; i++) {
		   array[i] = number.charAt(i) - 48;
		  }
		  
		  return array;
	}
}
