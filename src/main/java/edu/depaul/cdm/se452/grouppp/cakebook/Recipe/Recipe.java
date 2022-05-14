package edu.depaul.cdm.se452.grouppp.cakebook.Recipe;

import java.lang.annotation.Repeatable;
import java.sql.Time;
import java.util.*;
import java.util.List;

import edu.depaul.cdm.se452.grouppp.cakebook.Cookbook.Cookbook;
import edu.depaul.cdm.se452.grouppp.cakebook.Ingredient.Ingredient;
import edu.depaul.cdm.se452.grouppp.cakebook.Instruction.Instruction;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "Recipes")
public class Recipe {
    @Id
    @GeneratedValue
    private long id;
    //Cookbook cookbook;
    @ManyToOne
    @JoinTable(name = "Cookbooks_Recipes", joinColumns = {@JoinColumn(name = "recipeId", referencedColumnName = "id")})
    private Cookbook cookbook;
    @OneToMany(mappedBy = "recipe")
    private List<Ingredient> ingredients;
    //@JoinTable(name = "Recipes_Ingredients", joinColumns = {@JoinColumn(name = "recipeId", referencedColumnName = "recipeId")})
    String name;
    @OneToMany(mappedBy = "recipe")
    private List<Instruction> intstructions;
    Time cookTime;
    Time prepTime;
    Boolean favorite;
    Boolean isPublic;

    String author;

    public Recipe(){

    }

}
