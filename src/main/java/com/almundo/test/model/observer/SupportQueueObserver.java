package com.almundo.test.model.observer;

import java.util.Observable;
import java.util.Observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SupportQueueObserver implements Observer{
	private static final Logger logger = LoggerFactory.getLogger(SupportQueueObserver.class);
	
	
	@Override
	public void update(Observable arg0, Object args) {
		if(args instanceof SupportQueueChangeEvent) {
			SupportQueueChangeEvent event = (SupportQueueChangeEvent)args;
			logger.info(">>>>>>> LLamadas Actual atendidias: {}", event.getSupportQueue().getSupports().size());
		}
		
	}

}
