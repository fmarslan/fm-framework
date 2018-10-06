package com.fmarslan.framework.shared;

import com.fmarslan.framework.model.InvokeContext;

public abstract class BaseMiddleware {

	protected BaseMiddleware next;

	public BaseMiddleware build(BaseMiddleware next) {
		if (this.next == null) {
			this.next = next;
			return this.next;
		} else {
			return this.build(next);
		}
	}

	public void invoke(InvokeContext<?, ?> context) {
		if (next != null)
			next.invoke(context);
	}
}
