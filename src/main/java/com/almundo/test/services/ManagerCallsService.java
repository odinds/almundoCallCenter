package com.almundo.test.services;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;

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
import com.almundo.test.model.SupportCall;
import com.almundo.test.model.SupportQueue;

/**
 * 
 * @author Daniel Sarmiento
 *
 */
public class ManagerCallsService implements Runnable{
	private static final Logger logger = LoggerFactory.getLogger(ManagerCallsService.class);
	
	private SupportQueue supportQueue = SupportQueue.getInstance();
	
	private OperadorBusyQueue operadorBusyQueue = OperadorBusyQueue.getInstance(); 
	private OperadorFreeQueue operadorFreeQueue = OperadorFreeQueue.getInstance(); 
	
	private SupervisorBusyQueue supervisorBusyQueue = SupervisorBusyQueue.getInstance(); 
	private SupervisorFreeQueue supervisorFreeQueue = SupervisorFreeQueue.getInstance();	
	
	private DirectorBusyQueue directorBusyQueue = DirectorBusyQueue.getInstance(); 
	private DirectorFreeQueue directorFreeQueue = DirectorFreeQueue.getInstance();
	
	Long timeInterval = new Long(0L);
	Integer callsToProcces = new Integer(0);
	
	public ManagerCallsService(Integer incomingCall) {
		callsToProcces = incomingCall;
	}
	
	public void run() {
		
		logger.info("-------------Monitorea las llamadas TOTAL# {}",callsToProcces);
		while(callsToProcces > 0) {
			try {
				ArrayList<SupportCall> clonedList = (ArrayList<SupportCall>) supportQueue.getSupports().clone();
				
				LocalTime nowHour = LocalTime.now();
				for(SupportCall support:clonedList) {
					timeInterval = Duration.between(support.getCall().getBeginHour(), nowHour).getSeconds();
					if(timeInterval > 5 && timeInterval < 10) {
						Employee employee = null;
						support.getCall().setEndHour(nowHour);
						
						logger.info("LLamada Terminada:{}", support);
						supportQueue.removeCall(support);
						employee = support.getEmployee();
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
						callsToProcces--;
					}
				}
				Thread.sleep(500L);
			} catch (InterruptedException e) {
				logger.info("Interrupted");
			}
		}
	}
}
