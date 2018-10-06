package com.fmarslan.framework.exception;

public class ProxyException extends RuntimeException {
	public ProxyException() {
		super();
	}

	public ProxyException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public ProxyException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ProxyException(String arg0) {
		super(arg0);
	}

	public ProxyException(Throwable arg0) {
		super(arg0);
	}

	private static final long serialVersionUID = 1144958853973074740L;

	
	
}
