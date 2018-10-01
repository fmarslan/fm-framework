package com.fmarslan.framework.middlewares;

import com.fmarslan.framework.enums.ResponseStatus;
import com.fmarslan.framework.log.DebugLog;
import com.fmarslan.framework.management.FMApplication;
import com.fmarslan.framework.model.FWConfiguration;
import com.fmarslan.framework.model.InvokeContext;
import com.fmarslan.framework.shared.BaseMiddleware;

public class ExceptionMiddleware extends BaseMiddleware {

	@Override
	public void invoke(InvokeContext<?,?> context) {

		DebugLog.Info("exception handle");
		try {
			super.invoke(context);
		} catch (Throwable t) {
			boolean notThrow = FMApplication.exceptionHandle(t, context);
			if (notThrow == false && FWConfiguration.isResponseObject()) {
				context.getResponse().setResponseStatus(ResponseStatus.INTERNAL_SERVER_ERROR);
				context.getResponse().setThrowable(t);
				DebugLog.Error(t);
			}
		}
	}

}
