package com.fmarslan.framework.event;

import com.fmarslan.framework.model.InvokeContext;

public interface Handle<RESULT> {

	RESULT invoke(InvokeContext<?, ?> arg);

}
