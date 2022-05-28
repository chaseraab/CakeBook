package edu.depaul.cdm.se452.grouppp.cakebook.Recipe;

import java.util.*;
import java.util.List;

import edu.depaul.cdm.se452.grouppp.cakebook.Ingredient.Ingredient;
import edu.depaul.cdm.se452.grouppp.cakebook.Instruction.Instruction;
import edu.depaul.cdm.se452.grouppp.cakebook.Mealplan.Mealplan;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "Recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "recipe_id")
    private List<Ingredient> ingredients = new ArrayList<Ingredient>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "recipe_id")
    private List<Instruction> instructions = new ArrayList<Instruction>();
    @Column(name = "cookTime")
    String cookTime;
    String prepTime;
    Boolean favorite;
    Boolean isPublic;
    String author;

    public Recipe() {
    }

    public Recipe(String name) {
        this.name = name;
    }

    public void addInstruction(Instruction instruction) {
        this.instructions.add(instruction);
    }

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }

}
