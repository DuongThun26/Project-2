package com.javaweb.utils;

public class NumberUtil {
	public static boolean checkNumber(String s) {
		if(s != null && !s.equals("")) {
			try {
				Double.parseDouble(s);
				return true;
			}catch(NumberFormatException ex) {
				return false;
			}
		}
		return false;
	}
}
