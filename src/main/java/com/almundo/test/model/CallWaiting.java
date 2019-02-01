package com.almundo.test.model;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Queue;

import org.springframework.stereotype.Component;

import com.almundo.test.model.observer.CallsQueueChangeEvent;

/**
 * 
 * @author Daniel Sarmiento
 *
 */
@Component
public class CallWaiting {
	private Queue<Call> calls = new LinkedList<Call>();
	
	private static CallWaiting instance;
	
	public static CallWaiting getInstance() {
		if(instance == null) {
			instance = new CallWaiting();
		}
		return instance;
	}
	
	public Queue<Call> getCalls() {
		return calls;
	}

	private static class CallsObservable extends Observable {
        @Override
        public synchronized void setChanged() {
            super.setChanged();
        }
    }
	
	private static final CallsObservable OBSERVABLE;
	
	static {
		OBSERVABLE = new CallsObservable();
	}
	
	public static Observable getObservable() {
		return OBSERVABLE;
	}
	
	public void addCall(Call call) {
		CallsQueueChangeEvent event = new CallsQueueChangeEvent(this);

		calls.add(call);
		synchronized (OBSERVABLE) {
			OBSERVABLE.setChanged();	
			OBSERVABLE.notifyObservers(event);            
		}		

	}
	
	public Call getCall() {
		CallsQueueChangeEvent event = new CallsQueueChangeEvent(this);

		Call take = calls.poll();

        synchronized (OBSERVABLE) {
        	OBSERVABLE.setChanged();	
        	OBSERVABLE.notifyObservers(event);            
        }	
        
        return take;
	}

	@Override
	public String toString() {
		return "[Número llamadas en espera=" + calls.size() + "]";
	}
    
	
}
