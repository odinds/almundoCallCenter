package com.almundo.test.model;

/**
 * 
 * @author Daniel Sarmiento
 *
 */
public class Director extends Employee{
	private static EmployeeLevel employeeLevel;

	static {
		employeeLevel = EmployeeLevel.DIRECTOR;
	}

	public EmployeeLevel getEmployeeLevel() {
		return employeeLevel;
	}

	@Override
	public String toString() {
		return "Director [getName()=" + getName() + ", getNit()=" + getNit() + ", getStatus()=" + getStatus() + "]";
	}
	
}
