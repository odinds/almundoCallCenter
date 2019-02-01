package com.almundo.test.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.almundo.test.model.Director;
import com.almundo.test.model.Employee;
import com.almundo.test.model.EmployeeLevel;
import com.almundo.test.model.Operador;
import com.almundo.test.model.Status;
import com.almundo.test.model.Supervisor;

/**
 * 
 * @author Daniel Sarmiento
 *
 */
@Configuration
public class EmployeesConfigration {
	private static final Logger logger = LoggerFactory.getLogger(EmployeesConfigration.class);
	
	
	@Bean
	public Map<EmployeeLevel,Map<Integer,Employee>> empleadosExitentes() {
		Map<Integer,Employee> employeesOP= new HashMap<>();
		Map<Integer,Employee> employeesSU= new HashMap<>();
		Map<Integer,Employee> employeesDR= new HashMap<>();
		Map<EmployeeLevel,Map<Integer,Employee>> employees= new HashMap<>();
		
		//Minimun 4 Operators
		Integer operadores = new Random().nextInt(4)+4;

		//Minimun 2 supervisors
		Integer supervisores = new Random().nextInt(2)+2;

		//Minimun 1 directors
		Integer directores = new Random().nextInt(1)+1;
		for (int i = 0; i < operadores; i++) {
			Employee e1 = new Operador();
			e1.setName("Op_"+i);
			e1.setStatus(Status.FREE);
			employeesOP.put(i, e1);
		}
		logger.info("operadores:{}",operadores);
		
		for (int i = 0; i < supervisores; i++) {
			Employee e1 = new Supervisor();
			e1.setName("Su_"+i);
			e1.setStatus(Status.FREE);
			employeesSU.put(i, e1);
		}
		for (int i = 0; i < directores; i++) {
			Employee e1 = new Director();
			e1.setName("Dr_"+i);
			e1.setStatus(Status.FREE);
			employeesDR.put(i, e1);
		}
		
		employees.put(EmployeeLevel.OPERATOR, employeesOP);
		employees.put(EmployeeLevel.SUPERVISOR, employeesSU);
		employees.put(EmployeeLevel.DIRECTOR, employeesDR);
		
		return employees;
	} 
}
