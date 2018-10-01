package com.fmarslan.framework.middlewares;

import com.fmarslan.framework.log.Logger;
import com.fmarslan.framework.model.InvokeContext;
import com.fmarslan.framework.shared.BaseMiddleware;

public class InvokingMiddleware extends BaseMiddleware {

	@Override
	public void invoke(InvokeContext<?, ?> context) {

		Logger.Info("%s is invoking...", "blabla");
		try {
			context.getResponse().setData(context.getRequest().getMethod().invoke(context.getRequest().getArgs()));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		super.invoke(context);
		Logger.Info("%s is executed", "blabla");
	}

}
