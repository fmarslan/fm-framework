package com.fmarslan.framework.handler;

import com.fmarslan.framework.event.Handle;
import com.fmarslan.framework.model.InvokeContext;
import com.fmarslan.framework.model.ResponseModel;

public class DefaultPackager implements Handle<ResponseModel<Object>> {

	@Override
	public ResponseModel<Object> invoke(InvokeContext<?> arg) {
		ResponseModel<Object> resultObject = new ResponseModel<>();
		resultObject.setException(arg.getException());
		resultObject.setData(arg.getResponse().getData());
		resultObject.setMessage(arg.getResponse().getMessage());
		resultObject.setParams(arg.getResponse().getParameters());
		return resultObject;
	}

}
