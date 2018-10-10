package com.fmarslan.framework.test.service;

public class TestService {

	private static TestService testService;
	
	public static TestService getInstance() {
		if(testService== null)
			testService = new TestService();
		return testService;
	}
	
	public String writeMessage(String b) {
		String a= "ddffsd";
		a+="sdgfsdfsdf";
		a+="dsfsdfsdf";
		return a + "dsfsdfsdf";
	}
	
}
