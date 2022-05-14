package edu.depaul.cdm.se452.grouppp.cakebook.Instruction;

import javax.persistence.*;
import lombok.Data;

import edu.depaul.cdm.se452.grouppp.cakebook.Recipe.Recipe;

@Data
@Entity
@Table(name = "Instructions")
public class Instruction {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "recipeId")
    Recipe recipe;
    String instruction;
    
}
