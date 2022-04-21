package edu.depaul.cdm.se452.grouppp.cakebook;


import java.util.ArrayList;

import lombok.Data;

@Data
public class User {

    String name;
    String password; 
    ArrayList<Cookbook> cookbooks; 
    
    

    public User(){

    }
    
}
