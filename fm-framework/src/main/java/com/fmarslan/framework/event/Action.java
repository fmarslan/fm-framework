package com.fmarslan.framework.event;

import java.io.Serializable;

@FunctionalInterface
public interface Action<RESULT,SERVICE> extends Serializable  {
    
	RESULT invoke(SERVICE s);
		
}
