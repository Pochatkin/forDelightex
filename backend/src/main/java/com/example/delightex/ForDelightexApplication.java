package com.example.delightex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.example.delightex.controllers"})
@SpringBootApplication
public class ForDelightexApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForDelightexApplication.class, args);
	}
}
