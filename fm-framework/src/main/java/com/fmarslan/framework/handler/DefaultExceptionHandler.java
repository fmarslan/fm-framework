package com.fmarslan.framework.handler;

import com.fmarslan.framework.enums.ResponseStatus;
import com.fmarslan.framework.event.Handle;
import com.fmarslan.framework.exception.BusinessException;
import com.fmarslan.framework.log.Logger;
import com.fmarslan.framework.model.InvokeContext;

public class DefaultExceptionHandler implements Handle<Void> {

	@Override
	public Void invoke(InvokeContext<?> arg) {
		if(arg.getException() instanceof BusinessException) {
			arg.getResponse().setResponseStatus(ResponseStatus.BAD_REQUEST);
		}else {
			arg.getResponse().setResponseStatus(ResponseStatus.INTERNAL_SERVER_ERROR);
			Logger.Error(arg.getException());
		}
		return null;
	}

}
