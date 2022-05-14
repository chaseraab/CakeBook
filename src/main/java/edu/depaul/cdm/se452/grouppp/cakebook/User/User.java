package edu.depaul.cdm.se452.grouppp.cakebook.User;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import edu.depaul.cdm.se452.grouppp.cakebook.Cookbook.Cookbook;
import lombok.Data;

@Data
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String username;
    private String pword;

    // @OneToMany
    // @ElementCollection(targetClass = Cookbook.class)
    // private List<Cookbook> cookbook;

    public User() {
    }

    public User(String username, String pword) {
        this.username = username;
        this.pword = pword;
        // this.cookbook = new ArrayList<Cookbook>();
    }
    /*
     * public void addCookbook(Cookbook cookbook) {
     * this.cookbook.add(cookbook);
     * }
     */
}
