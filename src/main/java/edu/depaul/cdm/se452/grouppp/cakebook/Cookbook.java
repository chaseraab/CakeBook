break this file
package edu.depaul.cdm.se452.grouppp.cakebook;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Cookbook {

    String name; 
    Boolean favorite;
    Timestamp createdAt; 
    Timestamp updatedAt; 
    //List<Recipe> recipes;
    
    public Cookbook() {

    }
}
