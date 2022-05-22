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

		User temp = new User("Craab", "supersecret");
		Cookbook tempbc = new Cookbook("TestCookbook");
		temp.addCookbook(tempbc);
		Recipe tempr = new Recipe("Test Recipe");
		tempbc.addRecipe(tempr);
		Instruction tempi1 = new Instruction("Beat the egg");
		Instruction tempi2 = new Instruction("Beat it again!");
		tempr.addInstruction(tempi1);
		tempr.addInstruction(tempi2);
		System.out.println(tempbc.getRecipes());
		System.out.println("Welcome to CakeBook!");
	}

}
