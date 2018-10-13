package com.fmarslan.framework.model;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class ResponseModel<RESULT> implements Serializable {

	private static final long serialVersionUID = 3078620160805824308L;

	private Throwable exception;
	private RESULT data;
	private String message;
	private Map<String,Object> params;

	public RESULT getData() {
		return data;
	}

	public void setData(RESULT data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setException(Throwable exception) {
		this.exception = exception;
	}

	public Map<String, String> getException() {
		if (exception == null)
			return null;
		Map<String, String> map = new HashMap<>();
		map.put("Message", exception.getMessage());
		map.put("Type", exception.getClass().getName());
		StringWriter writer = new StringWriter();
		PrintWriter printWriter= new PrintWriter(writer);
		exception.printStackTrace(printWriter);
		map.put("StackTrace", writer.toString());
		return map;
	}
	
	public Map<String, Object> getParams() {
		return params;
	}
	
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
}
