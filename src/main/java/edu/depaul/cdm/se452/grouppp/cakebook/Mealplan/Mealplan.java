package edu.depaul.cdm.se452.grouppp.cakebook.Mealplan;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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

    @OneToMany
    private List<Recipe> recipes;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ElementCollection(targetClass = Ingredient.class)
    private List<Ingredient> need;
    @ElementCollection(targetClass = Ingredient.class)
    private List<Ingredient> have;

    public Mealplan() {

    }

    public Mealplan(String name, User user) {
        this.name = name;
        this.user = user;
        recipes = new ArrayList<Recipe>();
        need = new ArrayList<Ingredient>();
        have = new ArrayList<Ingredient>();
    }

}
