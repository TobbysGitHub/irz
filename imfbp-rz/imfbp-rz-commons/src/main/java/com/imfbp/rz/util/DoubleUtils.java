package com.imfbp.rz.util;

public class DoubleUtils {

	public static double getDoubleNullAsZero(Double value){
		if(value == null){
			return 0.0;
		}
		
		return value.doubleValue();
	}
	
}
