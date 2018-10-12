package com.fmarslan.framework.base;

import java.io.Serializable;

import com.fmarslan.framework.model.InvokeContext;

public abstract class BaseMiddleware implements Serializable {

	private static final long serialVersionUID = -4849125816278762024L;
	private BaseMiddleware next;

	public final BaseMiddleware build(BaseMiddleware next) {
		if (next == null)
			this.next = null;
		else if (this.next == null) {
			this.next = next;
		} else {
			next.build(this.next);
			this.next = next;
		}
		return this.next;
	}
	
	public final void beforeInvoke(InvokeContext<?, ?> context) {
		before(context);
		if(next!=null) {
			next.before(context);			
		}else {
			//context.getRequest().getMethod().i
		}
		after(context);
	}
	
	public final void afterInvoke(InvokeContext<?, ?> context) {
		after(context);
		if(next!=null) {
			next.after(context);
		}
	}
	
	protected abstract void before(InvokeContext<?, ?> context);
	protected abstract void after(InvokeContext<?, ?> context);
}
