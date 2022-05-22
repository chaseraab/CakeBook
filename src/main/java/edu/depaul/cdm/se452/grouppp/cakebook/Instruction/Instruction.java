package edu.depaul.cdm.se452.grouppp.cakebook.Instruction;

import javax.persistence.*;
import lombok.Data;

import edu.depaul.cdm.se452.grouppp.cakebook.Recipe.Recipe;

@Data
@Entity
@Table(name = "Instructions")
public class Instruction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    Recipe recipe;
    String instruction;

    public Instruction(){}
    public Instruction(String instruction) {
        this.instruction = instruction;
    }   

}
