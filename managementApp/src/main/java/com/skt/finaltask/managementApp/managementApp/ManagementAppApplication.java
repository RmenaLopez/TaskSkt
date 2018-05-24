package com.skt.finaltask.managementApp.managementApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ManagementAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagementAppApplication.class, args);
	}
}
