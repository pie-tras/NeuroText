package com.draglantix.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reader {

	/**
	 * Loads a file as a String.
	 * 
	 * @param path String - The path to the file in the res folder (exclude "res/")
	 * @return String - The file as a String
	 */
	public static String loadFileAsString(String path) {
		StringBuilder builder = new StringBuilder();

		try {
			BufferedReader br = new BufferedReader(new FileReader("res/" + path));
			String line;
			while((line = br.readLine()) != null)
				builder.append(line + "\n");

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return builder.toString();
	}

	/**
	 * Converts a String to an int.
	 * 
	 * @param number String - The number as as a String
	 * @return int - The number as an int
	 */
	public static int parseInt(String number) {
		try {
			return Integer.parseInt(number);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}

}
