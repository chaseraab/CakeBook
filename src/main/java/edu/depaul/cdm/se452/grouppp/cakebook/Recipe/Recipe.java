package edu.depaul.cdm.se452.grouppp.cakebook.Recipe;

import java.sql.Time;
import java.util.*;
import java.util.List;

import edu.depaul.cdm.se452.grouppp.cakebook.Ingredient.Ingredient;
import edu.depaul.cdm.se452.grouppp.cakebook.Instruction.Instruction;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "Recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //Ingredients
    @OneToMany(mappedBy="recipe")
    private List<Ingredient> ingredients = new ArrayList<Ingredient>();

    String name;
    //@OneToMany(mappedBy = "recipe")
    //@OneToMany(targetEntity = Instruction.class)
    //@JoinColumn(name = "recipe_id")
    @OneToMany(
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @JoinColumn(name = "recipe_id")
    private List<Instruction> instructions = new ArrayList<Instruction>();
    @Column(name="cookTime")
    String cookTime;
    String prepTime;
    Boolean favorite;
    Boolean isPublic;

    String author;

    public Recipe(){
    }
    public Recipe(String name){
        this.name = name;
    }

    public void addInstruction(Instruction instruction){
        this.instructions.add(instruction);
    }

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }

}
