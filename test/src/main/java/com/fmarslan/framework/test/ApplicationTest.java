package com.fmarslan.framework.test;

import com.fmarslan.framework.log.Logger;
import com.fmarslan.framework.management.FMApplication;
import com.fmarslan.framework.management.ProxyService;
import com.fmarslan.framework.model.ResponseModel;
import com.fmarslan.framework.test.service.TestService;

public class ApplicationTest {

	public static void main(String[] args) {
		FMApplication.Default();

		/*
		 * final long startTime = System.currentTimeMillis(); for (int i = 0; i <
		 * 2000000; i++) {
		 * 
		 * //service.run((x) -> x.writeMessage("exp")); //17000 ins.writeMessage("a");
		 * // 17000-17500 }
		 * 
		 * final long endTime = System.currentTimeMillis();
		 * System.out.println("Total execution time: " + (endTime - startTime));
		 */

		ProxyService<TestService> p = new ProxyService<TestService>(TestService.class);
		TestService ts = new TestService();

		// p.run(x -> x.writeMessage("proxy"));

		for (int in = 0; in < 10; in++) {

			long endTime = System.currentTimeMillis();

			for (int i = 0; i < 1000000; i++) {
				ts.writeMessage("original");
				// p.run(x -> x.writeMessage("proxy"));
			}

			Logger.Info("original Time : %s ", System.currentTimeMillis() - endTime);

			endTime = System.currentTimeMillis();

			for (int i = 0; i < 1000000; i++) {
				// ts.writeMessage("original");
				p.run(x -> x.writeMessage("proxy"));
			}

			Logger.Info("proxy Time : %s ", System.currentTimeMillis() - endTime);

		}

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
