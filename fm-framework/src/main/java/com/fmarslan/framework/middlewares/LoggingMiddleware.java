package com.fmarslan.framework.middlewares;

import com.fmarslan.framework.base.BaseMiddleware;
import com.fmarslan.framework.log.Logger;
import com.fmarslan.framework.model.InvokeContext;

public class LoggingMiddleware extends BaseMiddleware{

	private static final long serialVersionUID = 4862224962026765025L;

	@Override
	public void before(InvokeContext<?,?> context) {
			Logger.Info("Request is processing for %s", context.getRequest().getMethod().getClass().getCanonicalName());		
	}
	
	@Override
	public void after(InvokeContext<?,?> context) {

		Logger.Info("Request processed for %s", "blabla");
	}

}
