package com.fmarslan.framework.management;

import com.fmarslan.framework.base.BaseMiddleware;
import com.fmarslan.framework.event.Handle;
import com.fmarslan.framework.model.InvokeContext;

public class FMApplication {

	private static FMApplication application;
	
	static BaseMiddleware current;
	private static Handle<?> resultPackager;
	private static Handle<?> exceptionHandler;
		
	private FMApplication() {

	}

	static {
		application = new FMApplication();
	}
	
	public static BaseMiddleware build(BaseMiddleware middleware) {
		if (current == null) {
			current = middleware;
			return current;
		} else {
			return current.build(middleware);
		}
	}


	public static FMApplication current() {
		return application;
	}

	public static boolean isDebug() {
		boolean isDebug = java.lang.management.ManagementFactory.getRuntimeMXBean().getInputArguments().toString()
				.indexOf("-agentlib:jdwp") > 0;
		return isDebug;
	}
	
	

	public static void setResultPackager(Handle<?> resultPackager) {
		FMApplication.resultPackager = resultPackager;
	}

	public static void setExceptionHandler(Handle<?> exceptionHandler) {
		FMApplication.exceptionHandler = exceptionHandler;
	}
	
	public static Object resultPackage(InvokeContext<?, ?> context) {
		if (resultPackager != null)
			return resultPackager.invoke(context);
		else
			return context.getResponse().getData();
	}
	
	public static void handleException(InvokeContext<?, ?> context) {		
		exceptionHandler.invoke(context);		
	}
	
}
