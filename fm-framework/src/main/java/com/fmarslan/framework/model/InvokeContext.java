package com.fmarslan.framework.model;

import java.lang.reflect.Method;

public class InvokeContext<RESULT, SERVICE> {

	ResponseContext<RESULT> responseContext;
	RequestContext<RESULT, SERVICE> requestContext;

	public static <R,S> InvokeContext<R,S> createContext(Class<R> resultType,S service, Method method,Object[] args){
		return new InvokeContext<R, S>(resultType, service,method,args);
	}
	
	public InvokeContext(Class<RESULT> resultType, SERVICE service,Method method,Object[] args) {
		requestContext = new RequestContext<RESULT, SERVICE>(method, service,args);
		responseContext = new ResponseContext<RESULT>();
	}

	public ResponseContext<RESULT> getResponse() {
		return responseContext;
	}

	public RequestContext<RESULT, SERVICE> getRequest() {
		return requestContext;
	}
	
	

}
