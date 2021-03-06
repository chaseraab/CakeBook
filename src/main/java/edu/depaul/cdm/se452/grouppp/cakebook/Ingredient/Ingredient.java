package edu.depaul.cdm.se452.grouppp.cakebook.Ingredient;

import lombok.Data;
import javax.persistence.*;

import edu.depaul.cdm.se452.grouppp.cakebook.Recipe.Recipe;

@Data
@Entity
@Table(name = "Ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // Recipe
    String name;
    Float quantity;
    String measurement;
    Boolean is_checked = false;

    public Ingredient() {
    }

    public Ingredient(String name, Float quantity, String measurement, Boolean is_checked) {
        this.name = name;
        this.quantity = quantity;
        this.measurement = measurement;
        this.is_checked = is_checked;
    }

    public Ingredient(String name, Float quantity, String measurement) {
        this.name = name;
        this.quantity = quantity;
        this.measurement = measurement;
    }

    

}
