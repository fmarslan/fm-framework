package com.fmarslan.framework.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.fmarslan.framework.enums.ResponseStatus;

public class ResponseContext<RESULT> implements Serializable {

	private static final long serialVersionUID = -7721803390581576505L;

	private ResponseStatus responseStatus;
	private Map<String, Object> parameters;

	private RESULT data;
	private String message;

	public ResponseContext() {
		parameters = new HashMap<String, Object>();
	}

	@SuppressWarnings("unchecked")
	public void setData(Object data) {		
		this.data = (RESULT)data;
	}

	public RESULT getData() {
		return data;
	}

	public ResponseStatus getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(ResponseStatus responseStatus) {
		this.responseStatus = responseStatus;
	}

	public Map<String, Object> getParameters() {
		return Collections.unmodifiableMap(parameters);
	}

	public ResponseContext<RESULT> setParameter(String k, Object v) {
		parameters.put(k, v);
		return this;
	}
	
	public Object getParemeter(String key) {
		return parameters.get(key);
	}
	
	public boolean containsKey(String key) {
		return parameters.containsKey(key);
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
