package com.draglantix.util;

import java.util.ArrayList;
import java.util.List;

public class DataHandler {

	public static double[] toArray(String array) {
		String[] elements = array.substring(1, array.length()-1).split(",");
		double[] out = new double[elements.length];
		for(int i = 0; i < elements.length; i++) {
			out[i] = Double.parseDouble(elements[i]);
		}
		return out;
	}
	
	public static double[][] toDoubleArray(String array) {
		array = array.replace("], ", "]S");
		String[] arrays = array.substring(1, array.length()-1).split("S");
		List<double[]> tmpArray = new ArrayList<double[]>();
		int width = 0;
		for(String a : arrays) {
			String[] elements = a.substring(1, a.length()-1).split(",");
			width = elements.length;
			double[] out = new double[width];
			for(int i = 0; i < width; i++) {
				out[i] = Double.parseDouble(elements[i]);
			}
			tmpArray.add(out);
		}
		double[][] end = new double[arrays.length][width];
		tmpArray.toArray(end);
		return end;
	}
	
}
