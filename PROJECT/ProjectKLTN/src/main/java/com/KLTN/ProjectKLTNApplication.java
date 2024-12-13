package com.KLTN;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProjectKLTNApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectKLTNApplication.class, args);
		System.out.println("Run success!!!");
	}

}
