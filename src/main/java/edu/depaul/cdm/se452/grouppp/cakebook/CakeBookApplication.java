package edu.depaul.cdm.se452.grouppp.cakebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import edu.depaul.cdm.se452.grouppp.cakebook.User.*;
import edu.depaul.cdm.se452.grouppp.cakebook.Cookbook.*;
import edu.depaul.cdm.se452.grouppp.cakebook.Recipe.*;
import edu.depaul.cdm.se452.grouppp.cakebook.Instruction.*;

@SpringBootApplication
public class CakeBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(CakeBookApplication.class, args);
		System.out.println("Welcome to CakeBook!");
	}

}
