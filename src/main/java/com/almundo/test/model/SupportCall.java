package com.almundo.test.model;

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
