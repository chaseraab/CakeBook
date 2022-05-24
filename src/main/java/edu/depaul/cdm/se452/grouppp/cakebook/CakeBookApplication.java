package edu.depaul.cdm.se452.grouppp.cakebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CakeBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(CakeBookApplication.class, args);
		System.out.println("Welcome to CakeBook!");
	}

}
