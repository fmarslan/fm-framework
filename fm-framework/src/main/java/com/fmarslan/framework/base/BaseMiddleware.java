package com.fmarslan.framework.base;

import java.io.Serializable;

import com.fmarslan.framework.exception.InvokingException;
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
	
	public BaseMiddleware() {
	}
	
	public final void invoke(InvokeContext<?> context){
		before(context);
		if(next!=null) {
			next.invoke(context);			
		}else {
			try {
				Object data = context.getRequest().invoke();
				context.getResponse().setData(data);
			} catch (IllegalArgumentException e) {
				throw new InvokingException(e);
			}
		}
		after(context);
	}
	
	protected abstract void before(InvokeContext<?> context);
	protected abstract void after(InvokeContext<?> context);
}
