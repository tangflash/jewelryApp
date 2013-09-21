package com.flash.jewelry.common;

public class StringUtil {
	private StringUtil(){
		
	}
	
	public static boolean isEmpty(String str){
		if ((str == null) || str.trim().length() < 1)
			return true;
		else 
			return false;
	}
	
	public static String makeAddedStr(String str,int length, String addedStr){
		if (addedStr == null) addedStr = "_";
		int strLen = str.length();
		if (strLen == length) return str;
		
		StringBuffer stringBuffer = new StringBuffer();
		
		if (length > 0){
			if (strLen < length){
				for (int i = 0; i < (length - strLen); i++) {
					stringBuffer.append(addedStr);
				}
			}
			stringBuffer.append(str);
		}
		else{
			length = Math.abs(length);
			stringBuffer.append(str);
			if (strLen < length){
				for (int i = 0; i < (length - strLen); i++) {
					stringBuffer.append(addedStr);
				}
			}			
		}
		return stringBuffer.toString();
	}
}
