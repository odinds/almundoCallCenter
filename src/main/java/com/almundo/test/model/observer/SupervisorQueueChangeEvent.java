package com.almundo.test.model.observer;

import com.almundo.test.model.SupervisorQueue;

/**
 * 
 * @author Daniel Sarmiento
 *
 */
public class SupervisorQueueChangeEvent {
	
	private SupervisorQueue supervisorQueue;
	
	public SupervisorQueueChangeEvent(SupervisorQueue supervisorQueue) {
		this.supervisorQueue = supervisorQueue;
	}

	public SupervisorQueue getSupervisorQueue() {
		return supervisorQueue;
	}
	
}
