package com.example.logindb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoginDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginDbApplication.class, args);
	}

//	public CommandLineRunner commandLineRunner( AppDAO appDAO ) {
//
//		return runner-> {
//			createUser(appDAO);
//		};
//	}
//
//	private void createUser(AppDAO appDAO) {
//
//	}
}
