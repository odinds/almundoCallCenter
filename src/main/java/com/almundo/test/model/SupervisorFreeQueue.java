package com.almundo.test.model;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Queue;

import com.almundo.test.model.observer.SupervisorQueueChangeEvent;

/**
 * 
 * @author Daniel Sarmiento
 *
 */
public class SupervisorFreeQueue implements SupervisorQueue{
	private Queue<Employee> employees = new LinkedList<Employee>();
	
	private static SupervisorFreeQueue instance;
	
	public static SupervisorFreeQueue getInstance() {
		if(instance == null) {
			instance = new SupervisorFreeQueue();
		}
		return instance;
	}

	public Queue<Employee> getEmployees() {
		return employees;
	}

	private static class SupervisorQueueObservable extends Observable {
        @Override
        public synchronized void setChanged() {
            super.setChanged();
        }
    }	
	
	private static final SupervisorQueueObservable OBSERVABLE;
	
	static {
		OBSERVABLE = new SupervisorQueueObservable();
	}
	
	public static Observable getObservable() {
		return OBSERVABLE;
	}
	
	public synchronized  void addEmployee(Employee employee) {
		SupervisorQueueChangeEvent event = new SupervisorQueueChangeEvent(this);

		employees.add(employee);

        synchronized (OBSERVABLE) {
        	OBSERVABLE.setChanged();	
        	OBSERVABLE.notifyObservers(event);            
        }		
	}
	
	public Employee getEmployee() {
		SupervisorQueueChangeEvent event = new SupervisorQueueChangeEvent(this);

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
		+"] Supervisores [Libres=" + employees + "]";
	}
	
	
	
}
