package com.fmarslan.framework.system;

import java.io.Serializable;
import java.util.Map.Entry;

import com.fmarslan.framework.log.DebugLog;
import com.fmarslan.framework.log.LogType;
import com.fmarslan.framework.management.FMApplication;
import com.fmarslan.framework.model.InvokeContext;

public class RequestPool implements Serializable {

	private static final long serialVersionUID = 6604424109248276730L;

	static ThreadLocal<InvokeContext<?>> context = new ThreadLocal<>();
//	static int CREATE_REQUEST;
//	static int DISPOSE_REQUEST;

	static {
//		CREATE_REQUEST = FMApplication.addProcess("Create Request");
//		DISPOSE_REQUEST = FMApplication.addProcess("Dispose Request");
	}

	public static void createRequest(InvokeContext<?> c) {

//		c.startProcess(CREATE_REQUEST);
		try {
			context.set(c);
		} finally {
//			c.endProcess(CREATE_REQUEST);
		}
	}

	@SuppressWarnings("unchecked")
	public static <SERVICE> InvokeContext<SERVICE> getContext() {
		return (InvokeContext<SERVICE>) context.get();
	}

	public static void disposeRequest() {
		InvokeContext<?> c = getContext();
//		c.startProcess(DISPOSE_REQUEST);
		try {
			// long requestId = context.getRequest().getRequestId();
			context.remove();
		} finally {
//			c.endProcess(DISPOSE_REQUEST);
			if (FMApplication.isDebug()) {
				for (Entry<String, Long> item : c.getTimeSheets().entrySet()) {
					DebugLog.Log(LogType.INFO, "%s : %s", item.getValue(), item.getKey());
				}
			}
		}
	}
}
