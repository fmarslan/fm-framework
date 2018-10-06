package com.fmarslan.framework.test.service;

public class TestService {

	private static TestService testService;
	
	public static TestService getInstance() {
		if(testService== null)
			testService = new TestService();
		return testService;
	}
	
	public String writeMessage(String a) {		
		System.out.println(a);
		return "method succeded";		
	}
	
}
