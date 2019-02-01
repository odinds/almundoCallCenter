package com.almundo.test.model.observer;

import java.util.Observable;
import java.util.Observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DirectorQueueObserver implements Observer{
	private static final Logger logger = LoggerFactory.getLogger(DirectorQueueObserver.class);
	
	
	@Override
	public void update(Observable arg0, Object args) {
		if(args instanceof DirectorQueueChangeEvent) {
			DirectorQueueChangeEvent event = (DirectorQueueChangeEvent)args;
			logger.info(">>>>>**DIRE:  {} , {}", event.getDirectorQueue());
		}
		
	}

}
