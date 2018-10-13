package com.fmarslan.framework.test.service;

import java.util.HashMap;

public class TestService {

	private static TestService testService;
	
	public static TestService getInstance() {
		if(testService== null)
			testService = new TestService();
		return testService;
	}
	
	public String writeMessage(String b) {
		return processBody(b);
	}
	
	public String writeMessageForPlain(String b) {

		(new HashMap<>()).put("", "");
		(new HashMap<>()).put("", "");
		(new HashMap<>()).put("", "");
		(new HashMap<>()).put("", "");
		(new HashMap<>()).put("", "");
		return processBody(b);
	}
	
	private String processBody(String b) {
		String a= "ddffsd";
		a+="sdgfsdfsdf";
		a+="dsfsdfsdf";
		return a + "dsfsdfsdf";
	}
	
}
