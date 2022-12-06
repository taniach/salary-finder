package com.example.salaryfinder;

import com.example.salaryfinder.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SalaryFinderApplication {
	@Autowired
	private UserServiceImpl userService;

	public static void main(String[] args) {
		SpringApplication.run(SalaryFinderApplication.class, args);
	}
}
