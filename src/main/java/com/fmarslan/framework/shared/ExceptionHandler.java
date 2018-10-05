package com.fmarslan.framework.shared;

import com.fmarslan.framework.model.InvokeContext;

public interface ExceptionHandler {
	
	/**
	* this method is used for exception handling
	* @param 
	* @return if the result is "false" throw the exception
	* @version 1.0
	*/
	boolean handle(Throwable t,InvokeContext<?,?> context);
	
}
