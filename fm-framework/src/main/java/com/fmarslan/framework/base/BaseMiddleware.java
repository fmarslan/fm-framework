package com.fmarslan.framework.base;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import com.fmarslan.framework.exception.InvokingException;
import com.fmarslan.framework.management.FMApplication;
import com.fmarslan.framework.model.InvokeContext;

public abstract class BaseMiddleware implements Serializable {

	private static final long serialVersionUID = -4849125816278762024L;
	private BaseMiddleware next;
	private String name;
	
	private int BEFORE_PROCESS;
	private int AFTER_PROCESS;
	private int INVOKE_PROCESS;
	
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
		name = getClass().getCanonicalName();
		BEFORE_PROCESS = FMApplication.addProcess(name + "-before");
		AFTER_PROCESS = FMApplication.addProcess(name + "-after");
		INVOKE_PROCESS = FMApplication.addProcess("Invoke Original Method");
	}
	
	public final void invoke(InvokeContext<?> context) {
		context.startProcess(BEFORE_PROCESS);
		before(context);
		context.endProcess(BEFORE_PROCESS);
		if(next!=null) {
			next.before(context);			
		}else {
			try {
				context.startProcess(INVOKE_PROCESS);
//				context.getRequest().invoke();
				Object data = context.getRequest().getMethod().invoke(context.getRequest().getService(), context.getRequest().getArgs());
				context.getResponse().setData(data);
				context.endProcess(INVOKE_PROCESS);
			} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
				throw new InvokingException(e);
			}
		}
		context.startProcess(AFTER_PROCESS);
		after(context);
		context.endProcess(AFTER_PROCESS);
	}
	
	protected abstract void before(InvokeContext<?> context);
	protected abstract void after(InvokeContext<?> context);
}
