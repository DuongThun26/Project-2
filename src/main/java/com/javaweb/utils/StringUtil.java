package com.javaweb.utils;

public class StringUtil {
	public static boolean checkString(String s) {
		if(s != null && s.equals("") == false) {
			return true;
		}
		else{
			return false;
		}
	}
}
