package com.fmarslan.framework.system;

import java.util.HashMap;

public class FMConfiguration extends HashMap<String, Object> {

	private static final long serialVersionUID = -328942771967643993L;

	private static boolean activeProcessTimer = false;

	public static void setActiveProcessTimer(boolean activeProcessTimer) {
		FMConfiguration.activeProcessTimer = activeProcessTimer;
	}

	public static boolean isActiveProcessTimer() {
		return activeProcessTimer;
	}

}
