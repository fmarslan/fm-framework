package com.fmarslan.framework.log;

import com.fmarslan.framework.management.FMApplication;

public class DebugLog {

	public static void Log(LogType logType, String format,Object... args) {
		switch (logType) {
		case ERROR:{
			Error(format, args);
			break;
		}
		case WARNING:{
			Warning(format, args);
			break;
		}
		default:{
			Info(format, args);
			break;
		}
		}
	}
	
	public static void Warning(String format,Object... args) {		
		if(FMApplication.isDebug())
			System.out.println(String.format(format, args));		
	}
	
	public static void Error(String format,Object... args) {		
		if(FMApplication.isDebug())
			System.err.println(String.format(format, args));		
	}
	
	public static void Error(Throwable t) {		
		if(FMApplication.isDebug())
			System.err.println(t);		
	}
	
	public static void Info(String format,Object... args) {		
		System.out.println(String.format(format, args));		
	}
	
}