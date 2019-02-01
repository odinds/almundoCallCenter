package com.almundo.test.model.observer;

import java.util.Observable;
import java.util.Observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Daniel Sarmiento
 *
 */
public class EmployeeObserver implements Observer{
	private static final Logger logger = LoggerFactory.getLogger(EmployeeObserver.class);
	
	@Override
	public void update(Observable observable, Object args) {
        if (args instanceof EmployeeChangeStatusEvent) {
        	EmployeeChangeStatusEvent evento = (EmployeeChangeStatusEvent) args;
            logger.info("El empleado {}:{} ha cambiado de estado de {} a {}", evento.getEmployee().getName(), evento.getStatusOld(), evento.getStatusNew());
        }
		
	}

}
