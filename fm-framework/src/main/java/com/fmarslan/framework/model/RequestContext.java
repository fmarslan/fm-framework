package com.fmarslan.framework.model;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.fmarslan.framework.event.Action;

public class RequestContext<SERVICE> extends HashMap<String, Object> {
	private static final long serialVersionUID = -7721823390581576505L;

	private Method method;
	private Map<String, Object> parameters;
	private SERVICE service;
	private Object[] args;
	private Long requestId;
	private String clazzName;
	private String methodName;
	private Action<?, SERVICE> action;

	public RequestContext(long requestId, String clazzName, SERVICE service, Action<?, SERVICE> action) {
		parameters = new HashMap<String, Object>();
		this.service = service;
		this.requestId = requestId;
		this.clazzName = clazzName;
		this.action = action;
	}

	public void setConfigContext(Method method, Object[] args) {
		this.method = method;
		this.args = args;
		this.methodName = method.getName();
	}

	public Action<?, SERVICE> getAction() {
		return action;
	}
	
	public Object invoke() {
		return action.invoke(service);
	}

	public String getMethodName() {
		return methodName;
	}

	public String getClazzName() {
		return clazzName;
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

	public RequestContext<SERVICE> setParameter(String k, Object v) {
		parameters.put(k, v);
		return this;
	}

	public Object getParemeter(String key) {
		return parameters.get(key);
	}

	public boolean containsKey(String key) {
		return parameters.containsKey(key);
	}

	public Long getRequestId() {
		return requestId;
	}

}
