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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany
    @JoinTable(name = "Cookbooks_Recipes", joinColumns = {@JoinColumn(name = "cookbook_id", referencedColumnName = "id")})
    private List<Recipe> recipes = new ArrayList<Recipe> ();

    private String name;
    private Boolean favorite;
    private LocalDateTime createdAt;
    // private Timestamp updatedAt;
    // private List<Recipe> recipes;

    public Cookbook() {
    }

    public Cookbook(String name) {
        this.name = name;
        favorite = false;
        createdAt = LocalDateTime.now();

    }
}