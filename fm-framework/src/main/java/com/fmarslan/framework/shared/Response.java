package com.fmarslan.framework.shared;

import java.util.Map;

import com.fmarslan.framework.enums.ResponseStatus;

public interface Response<T extends Object> {
	
	public void setData(T data);
	
	public void setResponseStatus(ResponseStatus responseStatus);
	
	public void setParameters(Map<String,T> parameters);
	
	public void setThrowable(Throwable throwable);
	
}
