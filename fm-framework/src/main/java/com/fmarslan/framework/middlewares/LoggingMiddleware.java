package com.fmarslan.framework.middlewares;

import com.fmarslan.framework.log.Logger;
import com.fmarslan.framework.model.InvokeContext;
import com.fmarslan.framework.shared.BaseMiddleware;

public class LoggingMiddleware extends BaseMiddleware{
	
	@Override
	public void invoke(InvokeContext<?,?> context) {
		
		Logger.Info("Request is processing for %s", context.getRequest().getMethod().getClass().getCanonicalName());		
		super.invoke(context);
		Logger.Info("Request processed for %s", "blabla");
	}

}
