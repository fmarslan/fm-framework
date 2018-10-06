package com.fmarslan.framework.management;

@FunctionalInterface
public interface FMFunction<T,R> {
	R apply(T service);
}
