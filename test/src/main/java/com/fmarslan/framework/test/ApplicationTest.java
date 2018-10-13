package com.fmarslan.framework.test;

import com.fmarslan.framework.log.Logger;
import com.fmarslan.framework.management.FMApplication;
import com.fmarslan.framework.management.ProxyService;
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

		boolean compare = false;

		if (compare == false) {
			p.run(x -> x.writeMessage("proxy"));
		} else {
			for (int in = 0; in < 10; in++) {

				long endTime = System.nanoTime();

				for (int i = 0; i < 100; i++) {
					ts.writeMessageForPlain("original");
					// p.run(x -> x.writeMessage("proxy"));
				}

				Logger.Info("original Time : %s ", System.nanoTime() - endTime);

				endTime = System.nanoTime();

				for (int i = 0; i < 100; i++) {
					// ts.writeMessage("original");
					p.run(x -> x.writeMessage("proxy"));
				}

				Logger.Info("proxy Time : %s ", System.nanoTime() - endTime);

			}
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
