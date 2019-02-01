package com.almundo.test.model.observer;

import java.util.Observable;
import java.util.Observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CallsObserver implements Observer{
	private static final Logger logger = LoggerFactory.getLogger(CallsObserver.class);
	
	@Override
	public void update(Observable observable, Object args) {
        if (args instanceof CallsChangeStatusEvent) {
        	CallsChangeStatusEvent evento = (CallsChangeStatusEvent) args;
            logger.info("Llamadas en espera ahora {} ", evento.getCalls());
        }
		
		
	}
	
	

}
