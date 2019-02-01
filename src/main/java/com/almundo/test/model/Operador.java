package com.almundo.test.model;

/**
 * 
 * @author Daniel Sarmiento
 *
 */
public class Operador extends Employee{
	private static EmployeeLevel employeeLevel;

	static {
		employeeLevel = EmployeeLevel.OPERATOR;
	}

	public EmployeeLevel getEmployeeLevel() {
		return employeeLevel;
	}

	@Override
	public String toString() {
		return "Operador [getName()=" + getName() + ", getNit()=" + getNit() + ", getStatus()=" + getStatus()
				 + "]";
	}

}
