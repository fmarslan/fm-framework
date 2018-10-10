package com.fmarslan.framework.management;

import java.io.Serializable;
import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.function.Supplier;

import com.fmarslan.framework.exception.ProxyException;
import com.fmarslan.framework.model.InvokeContext;
import com.fmarslan.framework.shared.Function;

import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;

public class ProxyService<SERVICE> implements InvocationHandler {

	SERVICE instance;
	SERVICE proxyInstance;
	Class<SERVICE> clazz;
	Class<SERVICE> proxyClazz;
	Proxy proxy;

	public Object invoke(Object proxy, Method method, Object[] args)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		InvokeContext<?, ?> context = InvokeContext.createContext(method.getReturnType(), instance, method, args);
		FMApplication.current.invoke(context);
		Object result = method.invoke(instance, args);
		return result;
	}

	@SuppressWarnings("unchecked")
	public ProxyService(Class<SERVICE> clazz) {
		try {
			this.clazz = clazz;
			ProxyFactory proxyFactory = new ProxyFactory();
			proxyFactory.setSuperclass(this.clazz);
			this.proxyClazz = (Class<SERVICE>) proxyFactory.createClass();
			Constructor<?> ctor = this.proxyClazz.getConstructor();
			this.instance = (SERVICE) ctor.newInstance();
			((javassist.util.proxy.Proxy)this.instance).setHandler(new MethodHandler() {
				
				@Override
				public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable {
					// TODO Auto-generated method stub
					return null;
				}
			});
			
		} catch (IllegalAccessException | InstantiationException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException ex) {
			throw new ProxyException(ex);
		}
	}
	
	@SuppressWarnings("unchecked")
	public ProxyService(SERVICE instance) {
		try {
			this.clazz = (Class<SERVICE>) instance.getClass();
			this.instance = instance;
		} catch (SecurityException ex) {
			throw new ProxyException(ex);
		}
	}

	
	
	
	
	
	
	public String run(Function<String,SERVICE> function) {
		try {
			
			System.out.println(function.getClass());
			System.out.println(function.getMethod());			
//			System.out.println(((Serializable)function).getClass().getSuperclass());
			return function.invoke(this.instance);

		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	// public <RESULT> RESULT runPlain(Function<SERVICE, RESULT> function) throws
	// Exception {
	// DebugLog.Info("method is invoking");
	// InvokeContext<RESULT, SERVICE> context = new InvokeContext<RESULT,
	// SERVICE>(function, instance);
	// FMApplication.current.invoke(context);
	// return context.getResponse().getData();
	// }
}
