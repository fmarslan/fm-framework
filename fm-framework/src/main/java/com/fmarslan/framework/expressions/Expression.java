package com.fmarslan.framework.expressions;

import java.lang.reflect.Method;
import java.util.function.Function;

public abstract class Expression<INSTANCE,RESULT> implements Function<INSTANCE, RESULT>{



	public Method getMethod() {
		
		return null;
	}
	
}
