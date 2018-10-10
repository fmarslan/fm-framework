package com.fmarslan.framework.shared;

import java.io.Serializable;

@FunctionalInterface
public interface Function<R,T> extends Serializable  {
    
	R invoke(T obj);
		
	default String getMethod() {
		System.out.println(this);
		return "";
	}
}
