package com.fmarslan.framework.management;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.fmarslan.framework.event.Action;
import com.fmarslan.framework.exception.ProxyException;
import com.fmarslan.framework.log.DebugLog;
import com.fmarslan.framework.model.InvokeContext;
import com.fmarslan.framework.system.RequestPool;

import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;

public class ProxyService<SERVICE> {

	ProxyFactory proxyFactory;
	SERVICE orgInstance;
	SERVICE proxyInstance;
	Class<SERVICE> clazz;
	Class<SERVICE> proxyClazz;
	String clazzName;

	@SuppressWarnings("unchecked")
	public ProxyService(Class<SERVICE> clazz) {
		long executeTimer = System.currentTimeMillis();
		this.clazz = clazz;
		this.clazzName = clazz.getCanonicalName();
		proxyFactory = new ProxyFactory();
		proxyFactory.setSuperclass(this.clazz);
		proxyFactory.setUseCache(true);
		Class<SERVICE> proxyClazz = (Class<SERVICE>) proxyFactory.createClass();
		try {
			orgInstance = clazz.getConstructor().newInstance();
			proxyInstance = proxyClazz.getConstructor().newInstance();
			((ProxyObject) proxyInstance).setHandler(new MethodHandler() {

				@Override
				public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable {
					InvokeContext<?> context = RequestPool.getContext();
					context.setConfigContext(thisMethod, args);
					FMApplication.current.invoke(context);
					return context.getResponse().getData();
				}
			});
			DebugLog.Info("%s created %s ms", clazzName, System.currentTimeMillis() - executeTimer);

		} catch (NoSuchMethodException | InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | SecurityException e) {
			throw new ProxyException(e);
		}
	}

	public Object run(Action<Object, SERVICE> function) {
		long executeTimer = System.currentTimeMillis();
		InvokeContext<SERVICE> context = InvokeContext.createContext(clazzName, orgInstance, function);
		try {
			function.invoke(proxyInstance);
			return FMApplication.resultPackage(context);
		} catch (Throwable e) {
			context.setException(e);
			FMApplication.handleException(context);
			return FMApplication.resultPackage(context);
		} finally {
			RequestPool.disposeRequest();
			DebugLog.Info("%s request execute %s ms", clazzName, System.currentTimeMillis() - executeTimer);
		}
	}

	@SuppressWarnings("unchecked")
	public <RESULT> RESULT runPlain(Action<Object, SERVICE> function) {
		long executeTimer = System.currentTimeMillis();
		InvokeContext<SERVICE> context = InvokeContext.createContext(clazzName, orgInstance, function);
		try {
			function.invoke(proxyInstance);
			return (RESULT) context.getResponse().getData();
		} catch (Throwable e) {
			context.setException(e);
			FMApplication.handleException(context);
			return (RESULT) context.getResponse().getData();
		} finally {
			RequestPool.disposeRequest();
			DebugLog.Info("%s request execute plain %s ms", clazz.getCanonicalName(),
					System.currentTimeMillis() - executeTimer);
		}
	}
}
