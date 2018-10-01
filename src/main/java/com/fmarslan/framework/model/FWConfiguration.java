package com.fmarslan.framework.model;

import java.util.HashMap;

public class FWConfiguration extends HashMap<String, Object> {

	private static final long serialVersionUID = -328942771967643993L;

	private static boolean RESPONSE_OBJECT = true;

	public static boolean isResponseObject() {
		return RESPONSE_OBJECT;
	}
	
}
