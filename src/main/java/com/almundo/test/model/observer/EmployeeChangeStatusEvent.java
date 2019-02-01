package com.almundo.test.model.observer;

import com.almundo.test.model.Employee;
import com.almundo.test.model.Status;

/**
 * 
 * @author Daniel Sarmiento
 *
 */
public class EmployeeChangeStatusEvent {

	private Employee employee;
	private Status statusOld;
	private Status statusNew;
	
	public EmployeeChangeStatusEvent(Employee employee,Status statusOld,Status statusNew) {
		this.employee = employee;
		this.statusOld = statusOld;
		this.statusNew = statusNew;
	}

	public Employee getEmployee() {
		return employee;
	}

	public Status getStatusOld() {
		return statusOld;
	}

	public Status getStatusNew() {
		return statusNew;
	}
	
}
