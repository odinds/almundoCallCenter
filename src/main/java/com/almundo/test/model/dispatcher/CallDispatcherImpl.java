package com.almundo.test.model.dispatcher;

import java.time.LocalTime;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.almundo.test.model.Call;
import com.almundo.test.model.CallStatus;
import com.almundo.test.model.CallWaiting;
import com.almundo.test.model.DirectorBusyQueue;
import com.almundo.test.model.DirectorFreeQueue;
import com.almundo.test.model.Employee;
import com.almundo.test.model.OperadorBusyQueue;
import com.almundo.test.model.OperadorFreeQueue;
import com.almundo.test.model.Status;
import com.almundo.test.model.SupervisorBusyQueue;
import com.almundo.test.model.SupervisorFreeQueue;
import com.almundo.test.model.SupportCall;
import com.almundo.test.model.SupportQueue;

/**
 * 
 * @author Daniel Sarmiento
 *
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CallDispatcherImpl implements CallDispatcher{
	private static final Logger logger = LoggerFactory.getLogger(CallDispatcherImpl.class);
	
	@Value("${support.max}")
	private String maxSupport;	
	
	private CallWaiting callWaiting =  CallWaiting.getInstance();
	
	private SupportQueue supportQueue = SupportQueue.getInstance();
	
	private OperadorBusyQueue operadorBusyQueue = OperadorBusyQueue.getInstance(); 
	private OperadorFreeQueue operadorFreeQueue = OperadorFreeQueue.getInstance(); 
	
	private SupervisorBusyQueue supervisorBusyQueue = SupervisorBusyQueue.getInstance(); 
	private SupervisorFreeQueue supervisorFreeQueue = SupervisorFreeQueue.getInstance(); 

	private DirectorBusyQueue directorBusyQueue = DirectorBusyQueue.getInstance(); 
	private DirectorFreeQueue directorFreeQueue = DirectorFreeQueue.getInstance(); 
	
	@Async
	public void dispatchCall(Call call, Integer idCall) {
		logger.info("!!-------------------------- llamada Entrante: {}", idCall);
		call.setIdCall(""+idCall);
		call.setCallStatus(CallStatus.RINNING);
		callWaiting.addCall(call);

		//Controla las llamadas
		boolean supportedFind = false;
		while(!supportedFind) {
			if(supportQueue.getSupports().size() < Integer.parseInt(maxSupport)) {
				assignSupport();
				supportedFind = true;
			}
		}
		
//		logger.info("!!--------------------------- llamada Terminada: {}", call);
	}

	/**
	 * @see Asigna la llamada 
	 */
	private synchronized void assignSupport() {
		SupportCall sprCall = new SupportCall();
		
		Employee emp = null;
		while(emp == null) {
			if(operadorFreeQueue.getEmployees().size() > 0) {
				emp = operadorFreeQueue.getEmployee();
				emp.setStatus(Status.BUSY);
				operadorBusyQueue.addEmployee(emp);
			}else if(supervisorFreeQueue.getEmployees().size() > 0) {
				emp = supervisorFreeQueue.getEmployee();
				emp.setStatus(Status.BUSY);
				supervisorBusyQueue.addEmployee(emp);
			}else if(directorFreeQueue.getEmployees().size() > 0) {
				emp = directorFreeQueue.getEmployee();
				emp.setStatus(Status.BUSY);
				directorBusyQueue.addEmployee(emp);
			}
		}
		
		Call call = callWaiting.getCall();
		if(Objects.nonNull(call)) {
			call.setBeginHour(LocalTime.now());
			call.setCallStatus(CallStatus.ANSWER);
			sprCall.setCall(call);
			sprCall.setEmployee(emp);
			supportQueue.addSupport(sprCall);
		}
	}
	
}
