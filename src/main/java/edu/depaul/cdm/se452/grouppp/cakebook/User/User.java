package edu.depaul.cdm.se452.grouppp.cakebook.User;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import edu.depaul.cdm.se452.grouppp.cakebook.Cookbook.Cookbook;
import edu.depaul.cdm.se452.grouppp.cakebook.Mealplan.Mealplan;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String pword;

    @OneToMany
    @JoinTable(name = "Users_Cookbooks", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "cookbook_id", referencedColumnName = "id"))
    private List<Cookbook> cookbooks;

    // @OneToMany(mappedBy = "user)
    @OneToMany
    // @JoinColumn(name = "user_id")
    @JoinTable(name = "Users_Mealplans", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "mealplan_id", referencedColumnName = "id"))
    // @JoinColumn(name = "mealplan_id", referencedColumnName = "id"))
    private List<Mealplan> mealplans;

    public User() {
    }

    public User(String username, String pword) {
        this.username = username;
        this.pword = pword;
        this.cookbooks = new ArrayList<Cookbook>();
    }

    public void addCookbook(Cookbook cookbook) {
        cookbooks.add(cookbook);
    }

    public void addMealplan(Mealplan mealplan) {
        mealplans.add(mealplan);
    }

}
