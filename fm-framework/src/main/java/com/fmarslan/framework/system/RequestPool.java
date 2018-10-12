package com.fmarslan.framework.system;

import java.io.Serializable;
import java.util.HashMap;

import com.fmarslan.framework.management.FMApplication;
import com.fmarslan.framework.model.InvokeContext;

public class RequestPool implements Serializable {

	private static final long serialVersionUID = 6604424109248276730L;

	static HashMap<Long, InvokeContext<?, ?>> pool;

	static {
		pool = new HashMap<>();
	}

	public static void createRequest(InvokeContext<?, ?> context) {
		context.startProcess("createRequest");
		try {
			long requestId = Thread.currentThread().getId();
			pool.put(requestId, context);
		} finally {
			context.endProcess("createRequest");
		}
	}

	public static void disposeRequest(InvokeContext<?, ?> context) {
		context.startProcess("disposeRequest");
		try {
			long requestId = Thread.currentThread().getId();
			if(context.getRequest().)
			pool.remove(requestId);
		} finally {
			context.endProcess("disposeRequest");
		}
	}

}
