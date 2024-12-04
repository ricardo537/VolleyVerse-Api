package com.tfg.volleyverse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.tfg.volleyverse")
public class VolleyverseApplication {

	public static void main(String[] args) {
		SpringApplication.run(VolleyverseApplication.class, args);
	}

}
