package com.almundo.test.model.observer;

import java.util.Observable;
import java.util.Observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CallsQueueObserver implements Observer{
	private static final Logger logger = LoggerFactory.getLogger(CallsQueueObserver.class);
	
	@Override
	public void update(Observable observable, Object args) {
        if (args instanceof CallsQueueChangeEvent) {
        	CallsQueueChangeEvent evento = (CallsQueueChangeEvent) args;
            logger.info("Llamadas en espera ahora {} ", evento.getCallWaiting());
        }
		
		
	}
	
	

}
