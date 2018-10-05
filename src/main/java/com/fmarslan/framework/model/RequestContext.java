package com.fmarslan.framework.model;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class RequestContext<RESULT, SERVICE> extends HashMap<String, Object> {
	private static final long serialVersionUID = -7721823390581576505L;

	private Method method;
	private Map<String, Object> parameters;
	private SERVICE service;
	private Object[] args;

	public RequestContext(Method method, SERVICE service,Object[] args) {
		parameters = new HashMap<String, Object>();
		this.service = service;
		this.method = method;
		this.args = args;
	}

	public Method getMethod() {
		return method;
	}

	public Object[] getArgs() {
		return args;
	}
	
	public SERVICE getService() {
		return service;
	}

	public Map<String, Object> getParameters() {
		return Collections.unmodifiableMap(parameters);
	}

	public RequestContext<RESULT, SERVICE> addParameter(String k, Object v) {
		parameters.put(k, v);
		return this;
	}

}
