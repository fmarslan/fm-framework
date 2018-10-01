package com.fmarslan.framework.management;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.function.Function;

import com.fmarslan.framework.model.InvokeContext;
import com.fmarslan.framework.model.ResponseContext;

public class ProxyService<SERVICE> implements InvocationHandler {

	SERVICE instance;
	SERVICE proxyInstance;
	Class<SERVICE> clazz;
	Proxy proxy;

	public Object invoke(Object proxy, Method method, Object[] args)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {		
		InvokeContext<?, ?> context = InvokeContext.createContext(method.getReturnType(), instance, method,args);
		FMApplication.current.invoke(context);
		Object result = method.invoke(instance, args);
		return result;
	}

	@SuppressWarnings("unchecked")
	public ProxyService(SERVICE instance) {
		this.clazz = (Class<SERVICE>) instance.getClass();
		this.instance = instance;
//		this.proxyInstance = (SERVICE) Proxy.newProxyInstance(this.clazz.getClassLoader(), this.clazz.getGenericInterfaces(), this);
		
		
	}

	@SuppressWarnings("unchecked")
	public <RESULT> RESULT run(Function<SERVICE, RESULT> function) {
		try {
			return function.apply(instance);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

//	public <RESULT> RESULT runPlain(Function<SERVICE, RESULT> function) throws Exception {
//		DebugLog.Info("method is invoking");
//		InvokeContext<RESULT, SERVICE> context = new InvokeContext<RESULT, SERVICE>(function, instance);
//		FMApplication.current.invoke(context);
//		return context.getResponse().getData();
//	}
}
