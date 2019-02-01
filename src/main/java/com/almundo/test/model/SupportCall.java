package com.almundo.test.model;

/**
 * @see Entidad que representa la llamada en curso con su respectivo usuario de atención 
 * @author Daniel Sarmiento
 *
 */
public class SupportCall {
	private Call call;
	private Employee employee;
	
	public Call getCall() {
		return call;
	}
	public void setCall(Call call) {
		this.call = call;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	@Override
	public String toString() {
		return "SupportCall [call=" + call + ", employee=" + employee + "]";
	}

	
}
