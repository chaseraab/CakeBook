package edu.depaul.cdm.se452.grouppp.cakebook.Ingredient;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "Ingredients")
public class Ingredient {
    @Id
    @GeneratedValue
    private long id;
    String name;
    Integer quantity;

    public Ingredient(){

    }

}
