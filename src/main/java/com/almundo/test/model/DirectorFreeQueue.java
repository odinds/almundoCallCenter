package com.almundo.test.model;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Queue;

import com.almundo.test.model.observer.DirectorQueueChangeEvent;

/**
 * 
 * @author Daniel Sarmiento
 *
 */
public class DirectorFreeQueue implements DirectorQueue{
	private Queue<Employee> employees = new LinkedList<Employee>();
	
	private static DirectorFreeQueue instance;
	
	public static DirectorFreeQueue getInstance() {
		if(instance == null) {
			instance = new DirectorFreeQueue();
		}
		return instance;
	}

	public Queue<Employee> getEmployees() {
		return employees;
	}

	private static class DirectorQueueObservable extends Observable {
        @Override
        public synchronized void setChanged() {
            super.setChanged();
        }
    }	
	
	private static final DirectorQueueObservable OBSERVABLE;
	
	static {
		OBSERVABLE = new DirectorQueueObservable();
	}
	
	public static Observable getObservable() {
		return OBSERVABLE;
	}
	
	public synchronized  void addEmployee(Employee employee) {
		DirectorQueueChangeEvent event = new DirectorQueueChangeEvent(this);

		employees.add(employee);

        synchronized (OBSERVABLE) {
        	OBSERVABLE.setChanged();	
        	OBSERVABLE.notifyObservers(event);            
        }		
	}
	
	public synchronized Employee getEmployee() {
		DirectorQueueChangeEvent event = new DirectorQueueChangeEvent(this);

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
		+"]-Directores [Libres=" + employees + "]";
	}
	
	
	
}
