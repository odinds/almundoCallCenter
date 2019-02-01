package com.almundo.test.model;

import java.util.ArrayList;
import java.util.Observable;

import org.springframework.stereotype.Component;

import com.almundo.test.model.observer.SupportQueueChangeEvent;

/**
 * @see Clase que contiene y controla la lista de llamadas en proceso
 * @author Daniel Sarmiento
 *
 */
@Component
public class SupportQueue {
	private ArrayList<SupportCall> supports = new ArrayList<>();

	private static SupportQueue instance;
	
	public static SupportQueue getInstance() {
		if(instance == null) {
			instance = new SupportQueue();
		}
		return instance;
	}
	
	public ArrayList<SupportCall> getSupports() {
		return supports;
	}
	
	private static class SupportQueueObservable extends Observable {
        @Override
        public synchronized void setChanged() {
            super.setChanged();
        }
    }
	
	private static final SupportQueueObservable OBSERVABLE;
	
	static {
		OBSERVABLE = new SupportQueueObservable();
	}
	
	public static Observable getObservable() {
		return OBSERVABLE;
	}
	
	public synchronized void addSupport(SupportCall supportCall) {
		SupportQueueChangeEvent event = new SupportQueueChangeEvent(this);

		supports.add(supportCall);

        synchronized (OBSERVABLE) {
        	OBSERVABLE.setChanged();	
        	OBSERVABLE.notifyObservers(event);            
        }		
	}
	
	public synchronized void removeCall(SupportCall supportCall) {
		SupportQueueChangeEvent event = new SupportQueueChangeEvent(this);
		
		supports.remove(supportCall);
		
		synchronized (OBSERVABLE) {
			OBSERVABLE.setChanged();	
			OBSERVABLE.notifyObservers(event);            
		}		
	}
}
