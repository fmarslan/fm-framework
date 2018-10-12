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

import com.fmarslan.framework.event.Action;
import com.fmarslan.framework.exception.ProxyException;
import com.fmarslan.framework.model.InvokeContext;

import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;

public class ProxyService<SERVICE> {

	static ProxyFactory proxyFactory;	
	SERVICE orgInstance;
	SERVICE proxyInstance;
	Class<SERVICE> clazz;
	Class<SERVICE> proxyClazz;

	@SuppressWarnings("unchecked")
	public ProxyService(Class<SERVICE> clazz) {
		this.clazz = clazz;
		proxyFactory = new ProxyFactory();
		proxyFactory.setSuperclass(this.clazz);
		proxyFactory.setUseCache(true);
		
		Class<SERVICE> proxyClazz = (Class<SERVICE>) proxyFactory.createClass();
		try {
			ctor = proxyClazz.getConstructor();
			this.instance = clazz.newInstance();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		//this.instance = createProxy();
 catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private SERVICE createProxy() {
			try {
				final SERVICE ins = this.instance;
				SERVICE instance = (SERVICE) ctor.newInstance();
				((javassist.util.proxy.Proxy) instance).setHandler(new MethodHandler() {
					
					private InvokeContext<?, ?> context;
					
					public InvokeContext<?, ?> getContext() {
						return context;
					}
					
					@Override
					public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable {
//						context = InvokeContext.createContext(thisMethod.getReturnType(), instance, thisMethod, args);
//						FMApplication.current.invoke(context);
						Object result = thisMethod.invoke(ins, args);
//						context.getResponse().setData(result);
						return result;
					}
					
				});
				return instance;
			} catch (SecurityException | InstantiationException | InvocationTargetException | IllegalAccessException | IllegalArgumentException e) {
				System.out.println("Denememeee");
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
	}
	
	
//	@SuppressWarnings("unchecked")
//	public ProxyService(SERVICE instance) {
//		try {
//			this.clazz = (Class<SERVICE>) instance.getClass();
//			this.instance = instance;
//		} catch (SecurityException ex) {
//			throw new ProxyException(ex);
//		}
//	}

	public String run(Action<String,SERVICE> function) {
		try {

//			System.out.println(function.getClass());
//			System.out.println(function.getMethod());
//			System.out.println(((Serializable)function).getClass().getSuperclass());
			
			return function.invoke(createProxy());

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
