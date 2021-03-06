package com.almundo.test.model.observer;

import java.util.Observable;
import java.util.Observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Daniel Sarmiento
 *
 */
public class OperadorQueueObserver implements Observer{
	private static final Logger logger = LoggerFactory.getLogger(OperadorQueueObserver.class);
	
	
	@Override
	public void update(Observable arg0, Object args) {
		if(args instanceof OperadorQueueChangeEvent) {
			OperadorQueueChangeEvent event = (OperadorQueueChangeEvent)args;
			logger.info(">>>>OPER: {}", event.getOperadorQueue());
		}
		
	}

}
