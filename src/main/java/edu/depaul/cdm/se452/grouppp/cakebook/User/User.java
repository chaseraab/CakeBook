package edu.depaul.cdm.se452.grouppp.cakebook.User;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import edu.depaul.cdm.se452.grouppp.cakebook.Cookbook;
import lombok.Data;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String password;

    @OneToMany
    private List<Cookbook> cookbook;

    public User() {

    }

}
