package edu.depaul.cdm.se452.grouppp.cakebook;

import javax.persistence.*;
import lombok.Data;

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
