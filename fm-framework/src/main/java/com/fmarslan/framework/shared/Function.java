package com.fmarslan.framework.shared;

import java.io.Serializable;

@FunctionalInterface
public interface Function<R,S> extends Serializable  {
    
	R invoke(S s);
		
}
