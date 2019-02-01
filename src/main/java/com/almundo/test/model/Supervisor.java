package com.almundo.test.model;

/**
 * 
 * @author Daniel Sarmiento
 *
 */
public class Supervisor extends Employee{
	private static EmployeeLevel employeeLevel;

	static {
		employeeLevel = EmployeeLevel.SUPERVISOR;
	}

	public EmployeeLevel getEmployeeLevel() {
		return employeeLevel;
	}

	@Override
	public String toString() {
		return "Supervisor [getName()=" + getName() + ", getNit()=" + getNit() + ", getStatus()=" + getStatus() + "]";
	}
	
}
