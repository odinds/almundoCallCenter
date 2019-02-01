package com.almundo.test.model.observer;

import java.util.Observable;
import java.util.Observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.almundo.test.model.Director;
import com.almundo.test.model.DirectorBusyQueue;
import com.almundo.test.model.DirectorFreeQueue;
import com.almundo.test.model.Employee;
import com.almundo.test.model.Operador;
import com.almundo.test.model.OperadorBusyQueue;
import com.almundo.test.model.OperadorFreeQueue;
import com.almundo.test.model.Status;
import com.almundo.test.model.Supervisor;
import com.almundo.test.model.SupervisorBusyQueue;
import com.almundo.test.model.SupervisorFreeQueue;

public class SupportRemoveQueueObserver implements Observer{
	private static final Logger logger = LoggerFactory.getLogger(SupportRemoveQueueObserver.class);

	private OperadorBusyQueue operadorBusyQueue = OperadorBusyQueue.getInstance(); 
	private OperadorFreeQueue operadorFreeQueue = OperadorFreeQueue.getInstance(); 
	
	private SupervisorBusyQueue supervisorBusyQueue = SupervisorBusyQueue.getInstance(); 
	private SupervisorFreeQueue supervisorFreeQueue = SupervisorFreeQueue.getInstance();	
	
	private DirectorBusyQueue directorBusyQueue = DirectorBusyQueue.getInstance(); 
	private DirectorFreeQueue directorFreeQueue = DirectorFreeQueue.getInstance();	
	
	@Override
	public void update(Observable arg0, Object args) {
		if(args instanceof SupportRemoveQueueChangeEvent) {
			SupportRemoveQueueChangeEvent event = (SupportRemoveQueueChangeEvent)args;
			logger.info(">>>>>>> LLamadas terminada: {}", event.getSupportCall());
			
			Employee employee = (event.getSupportCall()).getEmployee();
			if(employee instanceof Operador) {
				operadorBusyQueue.removeEmployee(employee);
				operadorFreeQueue.addEmployee(employee);
			}else if(employee instanceof Supervisor){
				supervisorBusyQueue.removeEmployee(employee);
				employee.setStatus(Status.FREE);
				supervisorFreeQueue.addEmployee(employee);
			}else if(employee instanceof Director){
				directorBusyQueue.removeEmployee(employee);
				employee.setStatus(Status.FREE);
				directorFreeQueue.addEmployee(employee);
			}			
			logger.info(">>>>>>> Empleado libre: [{}]", employee);
			
		}
		
	}

}
