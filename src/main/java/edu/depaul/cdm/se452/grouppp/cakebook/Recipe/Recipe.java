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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinTable(name = "Cookbooks_Recipes", joinColumns = {@JoinColumn(name = "recipe_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "cookbook_id")})
    private Cookbook cookbook;

    //Ingredients
    @OneToMany(mappedBy="recipe")
    private List<Ingredient> ingredients;

    String name;
    @OneToMany(mappedBy = "recipe")
    private List<Instruction> intstructions;
    @Column(name="cookTime")
    Time cookTime;
    Time prepTime;
    Boolean favorite;
    Boolean isPublic;

    String author;

    public Recipe(){

    }

}
