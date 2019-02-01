package com.almundo.test.model;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Queue;

import com.almundo.test.model.observer.OperadorQueueChangeEvent;
import com.almundo.test.model.observer.SupervisorQueueChangeEvent;

/**
 * @see Clase que contiene y controla la cola de los supervisores ocupados
 * @author Daniel Sarmiento
 *
 */
public class SupervisorBusyQueue implements SupervisorQueue{
	private Queue<Employee> employees = new LinkedList<Employee>();
	
	private static SupervisorBusyQueue instance;
	
	public static SupervisorBusyQueue getInstance() {
		if(instance == null) {
			instance = new SupervisorBusyQueue();
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
	
	public synchronized void  addEmployee(Employee employee) {
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
	
	
	public Employee removeEmployee(Employee employee) {
		SupervisorQueueChangeEvent event = new SupervisorQueueChangeEvent(this);
		
		employees.remove(employee);
		
		synchronized (OBSERVABLE) {
			OBSERVABLE.setChanged();	
			OBSERVABLE.notifyObservers(event);            
		}	
		
		return employee;
	}		

	@Override
	public String toString() {
		return  "***** Num: [" + employees.size()
		+"]Supervisores [Ocupados=" + employees + "]";
	}	
	
	
}
