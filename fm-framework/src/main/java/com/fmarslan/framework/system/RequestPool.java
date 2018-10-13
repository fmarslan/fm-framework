package com.fmarslan.framework.system;

import java.io.Serializable;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.fmarslan.framework.log.DebugLog;
import com.fmarslan.framework.log.LogType;
import com.fmarslan.framework.management.FMApplication;
import com.fmarslan.framework.model.InvokeContext;

public class RequestPool implements Serializable {

	private static final long serialVersionUID = 6604424109248276730L;

	static Map<Long, InvokeContext<?>> pool;
	static InvokeContext<?> obj;
	static int CREATE_REQUEST;
	static int DISPOSE_REQUEST;

	static {
		pool = new IdentityHashMap<>();
		CREATE_REQUEST =FMApplication.addProcess("Create Request");
		DISPOSE_REQUEST =FMApplication.addProcess("Dispose Request");
	}

	public static void createRequest(InvokeContext<?> context) {
		
		 context.startProcess(CREATE_REQUEST);
		try {
			long requestId = Thread.currentThread().getId();
			 pool.put(requestId, context);
			obj = context;
		} finally {
			 context.endProcess(CREATE_REQUEST);
		}
	}

	@SuppressWarnings("unchecked")
	public static <SERVICE> InvokeContext<SERVICE> getContext(long id) {
		 return (InvokeContext<SERVICE>) pool.get(id);
//		return (InvokeContext<SERVICE>) obj;
	}

	public static <SERVICE> InvokeContext<SERVICE> getCurrentContext() {
		return getContext(Thread.currentThread().getId());
	}

	public static void disposeRequest(InvokeContext<?> context) {
		context.startProcess(DISPOSE_REQUEST);
		try {
			long requestId = context.getRequest().getRequestId();
			pool.remove(requestId);
			if (FMApplication.isDebug()) {
				for (Entry<String, Long> item : context.getTimeSheets().entrySet()) {
					DebugLog.Log(LogType.INFO, "%s : %s", item.getValue(), item.getKey());
				}
			}
		} finally {
			context.endProcess(DISPOSE_REQUEST);
		}
	}

	public static void disposeCurrentRequest() {
		disposeRequest(getCurrentContext());
	}
}
