package com.fmarslan.framework.management;

import java.util.IdentityHashMap;
import java.util.Map;

import com.fmarslan.framework.base.BaseMiddleware;
import com.fmarslan.framework.event.Handle;
import com.fmarslan.framework.handler.DefaultExceptionHandler;
import com.fmarslan.framework.handler.DefaultPackager;
import com.fmarslan.framework.middlewares.LoggingMiddleware;
import com.fmarslan.framework.model.InvokeContext;

public class FMApplication {

	private static FMApplication application;

	static BaseMiddleware current;
	private static Handle<?> resultPackager = new DefaultPackager();
	private static Handle<?> exceptionHandler = new DefaultExceptionHandler();
	static boolean isDebug;
	static Map<Integer, String> process = new IdentityHashMap<>();

	private FMApplication() {

	}

	static {
		application = new FMApplication();
		isDebug = java.lang.management.ManagementFactory.getRuntimeMXBean().getInputArguments().toString()
				.indexOf("-agentlib:jdwp") > 0;
	}

	public static int addProcess(String name) {
		int index = process.size();
		process.put(index, name);
		return index;
	}

	public static String getProcessName(int id) {
		return process.get(id);
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
		return isDebug;
	}

	public static void Default() {
		build(new LoggingMiddleware());
	}

	public static void setResultPackager(Handle<?> resultPackager) {
		if (resultPackager != null)
			FMApplication.resultPackager = resultPackager;
		else
			FMApplication.resultPackager = new DefaultPackager();
	}

	public static void setExceptionHandler(Handle<?> exceptionHandler) {
		if (exceptionHandler != null)
			FMApplication.exceptionHandler = exceptionHandler;
		else
			FMApplication.exceptionHandler = new DefaultExceptionHandler();
	}

	@SuppressWarnings("unchecked")
	public static <RESULT> RESULT resultPackage(InvokeContext<?> context) {
		if (resultPackager != null)
			return (RESULT) resultPackager.invoke(context);
		else
			return (RESULT) context.getResponse().getData();
	}

	public static void handleException(InvokeContext<?> context) {
		if (exceptionHandler == null)
			return;
		exceptionHandler.invoke(context);
	}

}
