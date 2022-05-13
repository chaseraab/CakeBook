package edu.depaul.cdm.se452.grouppp.cakebook;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "Cookbooks")
public class Cookbook {
    @Id
    @GeneratedValue
    private long id;

    private String name;
    private Boolean favorite;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    // private List<Recipe> recipes;

    public Cookbook() {

    }
}
