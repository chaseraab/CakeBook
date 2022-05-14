package edu.depaul.cdm.se452.grouppp.cakebook.Ingredient;

import lombok.Data;
import javax.persistence.*;

import edu.depaul.cdm.se452.grouppp.cakebook.Recipe.Recipe;

@Data
@Entity
@Table(name = "Ingredients")
public class Ingredient {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Recipe recipe;

    String name;
    Integer quantity;

    public Ingredient(){

    }

}
