package com.almundo.test.model;

import java.util.Observable;

import com.almundo.test.model.observer.EmployeeChangeStatusEvent;

/**
 * 
 * @author Daniel Sarmiento
 *
 */
public class Employee {

	private String name;
	private String nit;
	private Status status;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNit() {
		return nit;
	}
	public void setNit(String nit) {
		this.nit = nit;
	}
	
    private static class EmployeeObservable extends Observable {
        @Override
        public synchronized void setChanged() {
            super.setChanged();
        }
    }
    
    private static final EmployeeObservable OBSERVABLE;
    
    static {
    	OBSERVABLE = new EmployeeObservable();
    }
    
    public static Observable getObservable() {
        return OBSERVABLE;
    } 
    
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
       EmployeeChangeStatusEvent event = new EmployeeChangeStatusEvent(this, this.status, status);

        this.status = status;

        synchronized (OBSERVABLE) {
        	OBSERVABLE.setChanged();	
        	OBSERVABLE.notifyObservers(event);            
        }
	}
	@Override
	public String toString() {
		return "Employee [name=" + name + ", nit=" + nit + ", status=" + status + "]";
	}

	
	
}
