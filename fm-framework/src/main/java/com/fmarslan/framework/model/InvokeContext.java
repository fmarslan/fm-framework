package com.fmarslan.framework.model;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.fmarslan.framework.system.FMConfiguration;

public class InvokeContext<RESULT, SERVICE> implements Serializable {

	private static final long serialVersionUID = -7600531320224326670L;

	private HashMap<String, Long> timeSheets = new HashMap<>();

	ResponseContext<RESULT> responseContext;
	RequestContext<SERVICE> requestContext;
	Throwable exception;
	long createTime;

	public static <R, S> InvokeContext<R, S> createContext(Class<R> resultType, S service, Method method,
			Object[] args) {
		return new InvokeContext<R, S>(resultType, service, method, args);
	}

	public InvokeContext(Class<RESULT> resultType, SERVICE service, Method method, Object[] args) {
		requestContext = new RequestContext<SERVICE>(method, service, args);
		responseContext = new ResponseContext<RESULT>();
		createTime = System.currentTimeMillis();
	}

	public ResponseContext<RESULT> getResponse() {
		return responseContext;
	}

	public RequestContext<SERVICE> getRequest() {
		return requestContext;
	}

	public long getLastMillis() {
		return System.currentTimeMillis() - createTime;
	}

	public void setException(Throwable exception) {
		this.exception = exception;
	}

	public Throwable getException() {
		return exception;
	}

	public void startProcess(String key) {
		if (FMConfiguration.isActiveProcessTimer())
			timeSheets.put(key, System.currentTimeMillis());
	}
	
	public void endProcess(String key) {
		if (FMConfiguration.isActiveProcessTimer()) {
			long time = timeSheets.get(key);
			timeSheets.put(key, System.currentTimeMillis()-time);
		}
	}
	
	public Map<String,Long> getTimeSheets(){
		 return Collections.unmodifiableMap(timeSheets);
	}
}
