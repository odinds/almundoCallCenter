package com.almundo.test.model.observer;

import com.almundo.test.model.CallWaiting;

/**
 * 
 * @author Daniel Sarmiento
 *
 */
public class CallsChangeStatusEvent {
	private CallWaiting calls;
	
	public CallsChangeStatusEvent(CallWaiting calls) {
		this.calls = calls;
				
	}

	public CallWaiting getCalls() {
		return calls;
	}

	
}
