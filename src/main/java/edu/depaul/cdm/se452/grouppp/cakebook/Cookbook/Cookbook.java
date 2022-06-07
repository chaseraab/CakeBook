package edu.depaul.cdm.se452.grouppp.cakebook.Cookbook;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

import javax.persistence.*;

import lombok.Data;

import edu.depaul.cdm.se452.grouppp.cakebook.Recipe.Recipe;

@Data
@Entity
@Table(name = "Cookbooks")
public class Cookbook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private Boolean favorite = false;
    private Date created_at = new Date();
    private Date updated_at;
    @OneToMany
    @JoinTable(name = "Cookbooks_Recipes", joinColumns = @JoinColumn(name = "cookbook_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "recipe_id", referencedColumnName = "id"))
    private List<Recipe> recipes = new ArrayList<Recipe>();

    public Cookbook() {
    }

    public Cookbook(String name) {
        this.name = name;
    }

    public void addRecipe(Recipe recipe) {
        System.out.println("Adding recipe");
        System.out.println(recipe.toString());
        this.recipes.add(recipe);
    }

    public List<Recipe> getRecipes() {
        return this.recipes;
    }
}
