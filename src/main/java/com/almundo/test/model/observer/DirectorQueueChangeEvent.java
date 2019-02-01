package com.almundo.test.model.observer;

import com.almundo.test.model.DirectorQueue;
import com.almundo.test.model.SupervisorQueue;

/**
 * 
 * @author Daniel Sarmiento
 *
 */
public class DirectorQueueChangeEvent {
	
	private DirectorQueue directorQueue;
	
	public DirectorQueueChangeEvent(DirectorQueue directorQueue) {
		this.directorQueue = directorQueue;
	}

	public DirectorQueue getDirectorQueue() {
		return directorQueue;
	}

}
