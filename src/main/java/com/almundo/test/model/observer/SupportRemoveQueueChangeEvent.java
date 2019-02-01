package com.almundo.test.model.observer;

import com.almundo.test.model.SupportCall;

/**
 * 
 * @author Daniel Sarmiento
 *
 */
public class SupportRemoveQueueChangeEvent {
	private SupportCall supportCall;
	
	public SupportRemoveQueueChangeEvent(SupportCall supportCall) {
		this.supportCall = supportCall;
	}

	public SupportCall getSupportCall() {
		return supportCall;
	}

}
