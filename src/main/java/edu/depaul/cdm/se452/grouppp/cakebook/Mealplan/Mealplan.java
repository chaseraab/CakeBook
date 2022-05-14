package edu.depaul.cdm.se452.grouppp.cakebook.Mealplan;

import java.sql.Date;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import edu.depaul.cdm.se452.grouppp.cakebook.Ingredient.Ingredient;
import edu.depaul.cdm.se452.grouppp.cakebook.Recipe.Recipe;
import lombok.Data;

@Data
@Entity
public class Mealplan {
    @Id
    @GeneratedValue
    private long id;
    Date weekof;
    ArrayList<Recipe> recipes;
    ArrayList<Ingredient> need;
    ArrayList<Ingredient> have;

    public Mealplan() {

    }

}
