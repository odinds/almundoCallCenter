package com.almundo.test.services;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.almundo.test.model.CallStatus;
import com.almundo.test.model.SupportCall;
import com.almundo.test.model.SupportQueue;
import com.almundo.test.model.observer.SupportRemoveQueueObserver;

/**
 * @see Proceso alterno que se encargará de terminar y gestionar las llamadas actuales
 * @author Daniel Sarmiento
 *
 */
public class ManagerCallsService implements Runnable{
	private static final Logger logger = LoggerFactory.getLogger(ManagerCallsService.class);
	
	private SupportQueue supportQueue = SupportQueue.getInstance();
	
	Long timeInterval = new Long(0L);
	Integer callsToProcces = new Integer(0);
	
	public ManagerCallsService(Integer incomingCall) {
		callsToProcces = incomingCall;
	}
	
	public void run() {
		
		Observer observerRemoveSupportQueue = new SupportRemoveQueueObserver();
		SupportQueue.getObservable().addObserver(observerRemoveSupportQueue);
		
		logger.info("-------------Monitorea las llamadas TOTAL# {}",callsToProcces);
		while(callsToProcces > 0) {
			try {
				ArrayList<SupportCall> clonedList = (ArrayList<SupportCall>) supportQueue.getSupports().clone();
				
				LocalTime nowHour = LocalTime.now();
				for(SupportCall support:clonedList) {
					timeInterval = Duration.between(support.getCall().getBeginHour(), nowHour).getSeconds();
					if(timeInterval > 5 && timeInterval < 10) {
						support.getCall().setEndHour(nowHour);
						support.getCall().setCallStatus(CallStatus.END);
						supportQueue.removeSupport(support);
						
						callsToProcces--;
					}
				}
				Thread.sleep(500L);
			} catch (InterruptedException e) {
				logger.info("Interrupted");
			}
		}
	}
}
