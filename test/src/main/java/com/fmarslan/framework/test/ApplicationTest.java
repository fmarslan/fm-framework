package com.fmarslan.framework.test;

import com.fmarslan.framework.log.Logger;
import com.fmarslan.framework.management.FMApplication;
import com.fmarslan.framework.management.ProxyService;
import com.fmarslan.framework.middlewares.ExceptionMiddleware;
import com.fmarslan.framework.middlewares.InvokingMiddleware;
import com.fmarslan.framework.middlewares.LoggingMiddleware;
import com.fmarslan.framework.test.service.TestService;

public class ApplicationTest {

	public static void main(String[] args) {
		FMApplication.build(new LoggingMiddleware()).build(new ExceptionMiddleware()).build(new InvokingMiddleware());

		ProxyService<TestService> service = new ProxyService<TestService>(new TestService());
		TestService ins = new TestService();

		// MethodCall call = new MethodCall(0,null, null, null);
		String result = service.run((x) -> ins.writeMessage("exp"));
		// Proxy<TestService> p = new Proxy<TestService>(new TestService());
		Logger.Info(result);
	}

	/*
	 * public static void main(String[] args) { ApplicationTest ex = new
	 * ApplicationTest(); ex.callService(Integer::getInteger, "123"); }
	 * 
	 * private Integer callService(Function<String, Integer> sampleMethod, String
	 * input) { Integer output = sampleMethod.apply(input);
	 * System.out.println("Calling method "+ sampleMethod); return output; }
	 */

}
