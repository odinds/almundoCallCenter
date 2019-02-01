package com.almundo.test.model.observer;

import com.almundo.test.model.SupportQueue;

/**
 * 
 * @author Daniel Sarmiento
 *
 */
public class SupportQueueChangeEvent {
	private SupportQueue supportQueue;
	
	public SupportQueueChangeEvent(SupportQueue supportQueue) {
		this.supportQueue = supportQueue;
	}

	public SupportQueue getSupportQueue() {
		return supportQueue;
	}
	
}
