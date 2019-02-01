package com.almundo.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AlmundoCallCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlmundoCallCenterApplication.class, args).close();
	}

}

