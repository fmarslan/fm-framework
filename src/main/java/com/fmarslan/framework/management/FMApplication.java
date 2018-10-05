package com.fmarslan.framework.management;

import com.fmarslan.framework.model.InvokeContext;
import com.fmarslan.framework.shared.BaseMiddleware;
import com.fmarslan.framework.shared.ExceptionHandler;

public class FMApplication {

	private static FMApplication application;
	private static ExceptionHandler handler;

	static BaseMiddleware current;

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

	public static void setExceptionHandler(ExceptionHandler handler) {
		FMApplication.handler = handler;
	}

	public static boolean exceptionHandle(Throwable t, InvokeContext<?,?> context) {
		if (handler != null) {
			return handler.handle(t, context);
		} else
			return false;
	}

	public static FMApplication run() {
		return application;
	}

	public static boolean isDebug() {
		boolean isDebug = java.lang.management.ManagementFactory.getRuntimeMXBean().getInputArguments().toString()
				.indexOf("-agentlib:jdwp") > 0;
		return isDebug;
	}
}
