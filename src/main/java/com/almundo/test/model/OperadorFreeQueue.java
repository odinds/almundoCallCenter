package com.almundo.test.model;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Queue;

import com.almundo.test.model.observer.OperadorQueueChangeEvent;

/**
 * @see Clase que contiene y controla la cola de los operadores libres
 * @author Daniel Sarmiento
 *
 */
public class OperadorFreeQueue implements OperadorQueue{
	private Queue<Employee> employees = new LinkedList<Employee>();
	
	private static OperadorFreeQueue instance;
	
	public static OperadorFreeQueue getInstance() {
		if(instance == null) {
			instance = new OperadorFreeQueue();
		}
		return instance;
	}

	public Queue<Employee> getEmployees() {
		return employees;
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
	
	public synchronized void addEmployee(Employee employee) {
		OperadorQueueChangeEvent event = new OperadorQueueChangeEvent(this);

		employees.add(employee);

        synchronized (OBSERVABLE) {	
        	OBSERVABLE.setChanged();	
        	OBSERVABLE.notifyObservers(event);            
        }		
	}	
	
	public synchronized Employee getEmployee() {
		OperadorQueueChangeEvent event = new OperadorQueueChangeEvent(this);

		Employee employee = employees.poll();

        synchronized (OBSERVABLE) {
        	OBSERVABLE.setChanged();	
        	OBSERVABLE.notifyObservers(event);            
        }	
        
        return employee;
	}	
	

	@Override
	public String toString() {
		return  "***** Num: [" + employees.size()
		+"]Operadores [Libres=" + employees + "]";
	}	
	
}
