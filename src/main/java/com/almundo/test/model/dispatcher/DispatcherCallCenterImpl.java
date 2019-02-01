package com.almundo.test.model.dispatcher;

import java.util.Map;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.almundo.test.ResourcesCall;
import com.almundo.test.model.Call;
import com.almundo.test.model.CallStatus;
import com.almundo.test.model.CallWaiting;
import com.almundo.test.model.Employee;
import com.almundo.test.model.EmployeeLevel;
import com.almundo.test.model.SupportQueue;

/**
 * 
 * @author Daniel Sarmiento
 *
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DispatcherCallCenterImpl implements Callable<Call>{
	private static final Logger logger = LoggerFactory.getLogger(DispatcherCallCenterImpl.class);
	
	private Call call;
	
	public DispatcherCallCenterImpl(Call call, Integer idCall) {
		this.call = call;
		this.call.setIdCall(""+idCall);
		this.call.setCallStatus(CallStatus.RINNING);		
	}
	
	private CallWaiting callWaiting =  CallWaiting.getInstance();
	
	private SupportQueue supportQueue = SupportQueue.getInstance();
	
	@Autowired
	private Map<EmployeeLevel,Map<Integer,Employee>> empleadosExitentes;	

	@Value("${support.max}")
	private String maxSupport; 
	
	@Override
	public Call call() throws Exception {
		logger.info("Inicio gestion llamada id: {}", call.getIdCall());
		logger.info("llamada id: {}", call);
		this.callWaiting.addCall(this.call);
		logger.info("--------------Llamadas en espera: {}------", callWaiting.getCalls().size());
		dispatchCall();
		return call;
	}

	private void dispatchCall() {
		if(supportQueue.getSupports().size() < Integer.parseInt(maxSupport)) {
			logger.info("--------------Llamada [{}] será asignada en este momento------", call.getIdCall());
		}else {
			logger.info("--------------Llamada [{}] Siga en espera---------------------", call.getIdCall());
		}
		
		logger.info("--------------Llamadas en espera: {}------", callWaiting.getCalls().size());
	}

//	public void assignCall(Call call, Integer idCall) {
//		
//		logger.info("Asignada: Id llamada: {}",idCall);
//		this.call = call;
//		this.call.setIdCall(""+idCall);
//		this.call.setCallStatus(CallStatus.RINNING);
////		ResourcesCall.getActualCalls().put(idCall, call);
//		this.callWaiting.addCall(this.call);		
//	}
	
	
}
