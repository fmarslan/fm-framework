package com.fmarslan.framework.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.fmarslan.framework.enums.ResponseStatus;

public class ResponseContext<T extends Object> extends HashMap<String, Object> {

	private static final long serialVersionUID = -7721803390581576505L;

	private ResponseStatus responseStatus;
	private Throwable throwable;
	private Map<String, Object> parameters;

	private T data;

	public ResponseContext() {
		parameters = new HashMap<String, Object>();
	}

	@SuppressWarnings("unchecked")
	public void setData(Object data) {		
		this.data = (T)data;
	}

	public T getData() {
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

	public ResponseContext<T> addParameter(String k, Object v) {
		parameters.put(k, v);
		return this;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}
}
