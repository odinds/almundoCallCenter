package com.almundo.test.model.observer;

import com.almundo.test.model.OperadorQueue;

public class OperadorQueueChangeEvent {
	private OperadorQueue operadorQueue;
	
	public OperadorQueueChangeEvent(OperadorQueue operadorQueue) {
		this.operadorQueue = operadorQueue;
	}

	public OperadorQueue getOperadorQueue() {
		return operadorQueue;
	}
	
}
