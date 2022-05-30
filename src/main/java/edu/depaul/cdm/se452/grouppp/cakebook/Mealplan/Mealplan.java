package edu.depaul.cdm.se452.grouppp.cakebook.Mealplan;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import edu.depaul.cdm.se452.grouppp.cakebook.Ingredient.Ingredient;
import edu.depaul.cdm.se452.grouppp.cakebook.Recipe.Recipe;
import edu.depaul.cdm.se452.grouppp.cakebook.User.User;
import lombok.Data;

@Data
@Entity
@Table(name = "Mealplans")
public class Mealplan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    private Date weekof = new Date();

    // @OneToMany
    // @JoinTable(name = "Mealplans_Recipes", joinColumns = @JoinColumn(name =
    // "mealplan_id", referencedColumnName = "id"), inverseJoinColumns =
    // @JoinColumn(name = "recipe_id", referencedColumnName = "id"))
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "mealplan_id")
    private List<Recipe> recipes = new ArrayList<Recipe>();;

    // @ElementCollection(targetClass = Ingredient.class)
    // private List<Ingredient> need;
    // @ElementCollection(targetClass = Ingredient.class)
    // private List<Ingredient> have;

    public Mealplan() {

    }

    public Mealplan(String name) {
        this.name = name;
        // this.user = user;
        // recipes = new ArrayList<Recipe>();
        // need = new ArrayList<Ingredient>();
        // have = new ArrayList<Ingredient>();
    }

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    public void deleteRecipe(Recipe recipe) {
        recipes.remove(recipe);
    }

}
