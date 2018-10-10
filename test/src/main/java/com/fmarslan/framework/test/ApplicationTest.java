package com.fmarslan.framework.test;

import com.fmarslan.framework.management.FMApplication;
import com.fmarslan.framework.management.ProxyService;
import com.fmarslan.framework.middlewares.ExceptionMiddleware;
import com.fmarslan.framework.middlewares.InvokingMiddleware;
import com.fmarslan.framework.middlewares.LoggingMiddleware;
import com.fmarslan.framework.test.service.TestService;

public class ApplicationTest {

	public static void main(String[] args) {
		FMApplication.build(new LoggingMiddleware()).build(new ExceptionMiddleware()).build(new InvokingMiddleware());

		ProxyService<TestService> service = new ProxyService<TestService>(TestService.class);
		TestService ins = new TestService();

		// MethodCall call = new MethodCall(0,null, null, null);

		final long startTime = System.currentTimeMillis();
		for (int i = 0; i < 2000000; i++) {

			//service.run((x) -> x.writeMessage("exp")); //17000
			ins.writeMessage("a"); // 17000-17500
		}

		final long endTime = System.currentTimeMillis();
		System.out.println("Total execution time: " + (endTime - startTime));

		// Proxy<TestService> p = new Proxy<TestService>(new TestService());
		//Logger.Info(result);
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
