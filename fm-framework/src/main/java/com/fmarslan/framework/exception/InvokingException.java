package com.fmarslan.framework.exception;

public class InvokingException extends RuntimeException {
	public InvokingException() {
		super();
	}

	public InvokingException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public InvokingException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public InvokingException(String arg0) {
		super(arg0);
	}

	public InvokingException(Throwable arg0) {
		super(arg0);
	}

	private static final long serialVersionUID = 1144958853973074740L;

	
	
}
