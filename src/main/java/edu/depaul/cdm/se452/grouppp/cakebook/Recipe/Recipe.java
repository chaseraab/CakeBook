package edu.depaul.cdm.se452.grouppp.cakebook.Recipe;

import java.sql.Time;
import java.util.ArrayList;

import javax.persistence.*;

import edu.depaul.cdm.se452.grouppp.cakebook.Cookbook.Cookbook;
import edu.depaul.cdm.se452.grouppp.cakebook.Ingredient.Ingredient;
import edu.depaul.cdm.se452.grouppp.cakebook.Instruction.Instruction;
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
    @JoinTable(name = "Recipes_Ingredients", joinColumns = {
            @JoinColumn(name = "recipeId", referencedColumnName = "id") })
    String name;
    @OneToMany(mappedBy = "Recipe")
    ArrayList<Instruction> intstructions;
    Time cookTime;
    Time prepTime;
    Boolean favorite;
    Boolean isPublic;

    String author;

    public Recipe() {

    }

}
