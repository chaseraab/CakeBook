package edu.depaul.cdm.se452.grouppp.cakebook;

import java.sql.Time;
import java.util.ArrayList;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "Recipes")
public class Recipe {
    @Id
    @GeneratedValue
    private long id;
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
