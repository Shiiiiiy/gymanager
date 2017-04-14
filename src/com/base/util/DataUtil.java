package com.base.util;

public class DataUtil {
	
	public static boolean isNull(String str) {
		return (str == null) || (str.trim().length() == 0);
	}

	public static boolean isNotNull(String str) {
		return !isNull(str);
	}

	public static boolean isNotNull(Object ob) {
		if (ob != null) {
			return !isNull(ob.toString());
		}
		return false;
	}
}
