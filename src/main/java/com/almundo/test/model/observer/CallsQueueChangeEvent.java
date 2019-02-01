package com.almundo.test.model.observer;

import com.almundo.test.model.CallWaiting;

/**
 * 
 * @author Daniel Sarmiento
 *
 */
public class CallsQueueChangeEvent {
	private CallWaiting callWaiting;
	
	public CallsQueueChangeEvent(CallWaiting callWaiting) {
		this.callWaiting = callWaiting;
	}

	public CallWaiting getCallWaiting() {
		return callWaiting;
	}

	
}
