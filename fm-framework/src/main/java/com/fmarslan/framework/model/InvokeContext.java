package com.fmarslan.framework.model;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.fmarslan.framework.enums.ResponseStatus;
import com.fmarslan.framework.event.Action;
import com.fmarslan.framework.management.FMApplication;
import com.fmarslan.framework.system.FMConfiguration;
import com.fmarslan.framework.system.RequestPool;

public class InvokeContext<SERVICE> implements Serializable {

	private static final long serialVersionUID = -7600531320224326670L;

	private Map<Integer, Long> timeSheets = new IdentityHashMap<>();

	ResponseContext<Object> responseContext;
	RequestContext<SERVICE> requestContext;
	Throwable exception;
	long createTime;

	public void setConfigContext(Method method, Object[] args) {
		requestContext.setConfigContext(method, args);
	}

	public static <S> InvokeContext<S> createContext(String clazzName, S service, Action<Object, S> action) {
		InvokeContext<S> context =  new InvokeContext<S>(clazzName, service, action);
		RequestPool.createRequest(context);
		return context;
	}

	public InvokeContext(String clazzName, SERVICE service, Action<Object, SERVICE> action) {
		requestContext = new RequestContext<SERVICE>(Thread.currentThread().getId(), clazzName, service, action);
		responseContext = new ResponseContext<Object>();
		createTime = System.currentTimeMillis();
	}

	public ResponseContext<Object> getResponse() {
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
		this.getResponse().setResponseStatus(ResponseStatus.INTERNAL_SERVER_ERROR);
	}

	public Throwable getException() {
		return exception;
	}

	public void startProcess(int key) {
		if (FMConfiguration.isActiveProcessTimer())
			timeSheets.put(key, System.nanoTime());
	}

	public void endProcess(int key) {
		if (FMConfiguration.isActiveProcessTimer()) {
			long time = timeSheets.get(key);
			timeSheets.put(key, System.nanoTime() - time);
		}
	}
	
	public void addTimeSheets(int key,Long time) {
		if (FMConfiguration.isActiveProcessTimer())
			timeSheets.put(key, time);
	}

	public Map<String, Long> getTimeSheets() {
		HashMap<String, Long> resultMap = new HashMap<>();
		for(Entry<Integer, Long> x : timeSheets.entrySet()) {
			resultMap.put(FMApplication.getProcessName(x.getKey()), x.getValue());
		}
		return resultMap;
	}
}
