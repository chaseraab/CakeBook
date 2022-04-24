package edu.depaul.cdm.se452.grouppp.cakebook;

import java.sql.Time;
import java.util.ArrayList;
import lombok.Data;

@Data
public class Recipe {
    Cookbook cookbook;
    ArrayList<Ingredient> ingredients;
    String name;
    ArrayList<String> intstructions;
    Time cookTime;
    Time prepTime;
    Boolean favorite;
    Boolean isPublic;
    String author;

    public Recipe(){

    }

    

    
}
