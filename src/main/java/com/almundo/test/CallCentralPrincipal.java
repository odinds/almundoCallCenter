package com.almundo.test;

import java.util.Map;
import java.util.Observer;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.almundo.test.model.Call;
import com.almundo.test.model.CallWaiting;
import com.almundo.test.model.DirectorBusyQueue;
import com.almundo.test.model.DirectorFreeQueue;
import com.almundo.test.model.Employee;
import com.almundo.test.model.EmployeeLevel;
import com.almundo.test.model.OperadorBusyQueue;
import com.almundo.test.model.OperadorFreeQueue;
import com.almundo.test.model.Status;
import com.almundo.test.model.SupervisorBusyQueue;
import com.almundo.test.model.SupervisorFreeQueue;
import com.almundo.test.model.SupportQueue;
import com.almundo.test.model.dispatcher.CallDispatcher;
import com.almundo.test.model.observer.CallsQueueObserver;
import com.almundo.test.model.observer.DirectorQueueObserver;
import com.almundo.test.model.observer.OperadorQueueObserver;
import com.almundo.test.model.observer.SupervisorQueueObserver;
import com.almundo.test.model.observer.SupportQueueObserver;
import com.almundo.test.services.ManagerCallsService;

/**
 * 
 * @author Daniel Sarmiento
 *
 */
@Component
public class CallCentralPrincipal implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(CallCentralPrincipal.class);

	@Autowired
	private CallDispatcher callDispatcher;
	
	@Autowired
	private Map<EmployeeLevel,Map<Integer,Employee>> empleadosExitentes;	
	
	private OperadorFreeQueue operadorFreeQueue = OperadorFreeQueue.getInstance();
	private SupervisorFreeQueue supervisorFreeQueue = SupervisorFreeQueue.getInstance();
	private DirectorFreeQueue directorFreeQueue = DirectorFreeQueue.getInstance();
	
	@Override
	public void run(String... args) throws Exception {
		logger.info("..............Inicio Sistema............");
		
		Integer incomingCall = new Random().nextInt(20)+8;
		
		logger.info("Número Simulacion llamadas: {}", incomingCall);
		beginObservers();
		
		logger.info("Carga la lista de empleados en la lista de disponibles");
		logger.info("-------------------INI Carga Empleados---------");
		loadQueueEmployees();
		logger.info("-------------------FIN Carga Empleados---------");
		
//		managerCalls.manageSupport(incomingCall);
		
		Runnable r = new ManagerCallsService(incomingCall);
		new Thread(r).start();		
        for(int threadNumber = 0; threadNumber < incomingCall; threadNumber ++){
        	callDispatcher.dispatchCall(new Call(),threadNumber);
        }
        
        
	}

	/**
	 * @see Carga la lista de empleados
	 */
	private void loadQueueEmployees() {
		Map<Integer, Employee> operadoresMap = empleadosExitentes.get(EmployeeLevel.OPERATOR);
		logger.info("Carga la lista de operadores total:{}",operadoresMap.size());
		operadoresMap.forEach((k,v) ->{
			v.setStatus(Status.FREE);
			operadorFreeQueue.addEmployee(v);
		});
		
		Map<Integer, Employee> supervisoresMap = empleadosExitentes.get(EmployeeLevel.SUPERVISOR);
		logger.info("* Carga la lista de supervisores total:{}",supervisoresMap.size());
		supervisoresMap.forEach((k,v) ->{
			v.setStatus(Status.FREE);
			supervisorFreeQueue.addEmployee(v);
		});
		
		Map<Integer, Employee> directoresMap = empleadosExitentes.get(EmployeeLevel.DIRECTOR);
		logger.info("** Carga la lista de directores total:{}",directoresMap.size());
		directoresMap.forEach((k,v) ->{
			v.setStatus(Status.FREE);
			directorFreeQueue.addEmployee(v);
		});
	}

	/**
	 * @see inicia Los observadore
	 */
	private void beginObservers() {
		Observer observerQueueWait = new CallsQueueObserver();
		Observer observerQueueOperador = new OperadorQueueObserver();
		Observer observerQueueSupervisor = new SupervisorQueueObserver();
		Observer observerQueueDirector = new DirectorQueueObserver();
		Observer observerSupportQueue = new SupportQueueObserver();
		
		CallWaiting.getObservable().addObserver(observerQueueWait);	
		SupportQueue.getObservable().addObserver(observerSupportQueue);
		OperadorBusyQueue.getObservable().addObserver(observerQueueOperador);
		OperadorFreeQueue.getObservable().addObserver(observerQueueOperador);
		SupervisorBusyQueue.getObservable().addObserver(observerQueueSupervisor);
		SupervisorFreeQueue.getObservable().addObserver(observerQueueSupervisor);
		DirectorFreeQueue.getObservable().addObserver(observerQueueDirector);
		DirectorBusyQueue.getObservable().addObserver(observerQueueDirector);
	}
}
