package com.almundo.test.model;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Queue;

import com.almundo.test.model.observer.OperadorQueueChangeEvent;

/**
 * 
 * @author Daniel Sarmiento
 *
 */
public class OperadorBusyQueue  implements OperadorQueue{
	private Queue<Employee> operadores = new LinkedList<Employee>();
	
	private static OperadorBusyQueue instance;
	
	public static OperadorBusyQueue getInstance() {
		if(instance == null) {
			instance = new OperadorBusyQueue();
		}
		return instance;
	}

	public Queue<Employee> getOperadores() {
		return operadores;
	}
	
	private static class OperadorQueueObservable extends Observable {
        @Override
        public synchronized void setChanged() {
            super.setChanged();
        }
    }	
	
	private static final OperadorQueueObservable OBSERVABLE;
	
	static {
		OBSERVABLE = new OperadorQueueObservable();
	}
	
	public static Observable getObservable() {
		return OBSERVABLE;
	}
	
	public synchronized  void addEmployee(Employee employee) {
		OperadorQueueChangeEvent event = new OperadorQueueChangeEvent(this);

		operadores.add(employee);

        synchronized (OBSERVABLE) {
        	OBSERVABLE.setChanged();	
        	OBSERVABLE.notifyObservers(event);            
        }		
	}
	
	public synchronized  Employee getEmployee() {
		OperadorQueueChangeEvent event = new OperadorQueueChangeEvent(this);

		Employee employee = operadores.poll();

        synchronized (OBSERVABLE) {
        	OBSERVABLE.setChanged();	
        	OBSERVABLE.notifyObservers(event);            
        }	
        
        return employee;
	}	
	
	public synchronized Employee removeEmployee(Employee employee) {
		OperadorQueueChangeEvent event = new OperadorQueueChangeEvent(this);
		
		operadores.remove(employee);
		
		synchronized (OBSERVABLE) {
			OBSERVABLE.setChanged();	
			OBSERVABLE.notifyObservers(event);            
		}	
		
		return employee;
	}	
	

	@Override
	public String toString() {
		return  "***** Num: [" + operadores.size()
				+"] Operadores [Ocupados=" + operadores + "]";
	}
	
}
