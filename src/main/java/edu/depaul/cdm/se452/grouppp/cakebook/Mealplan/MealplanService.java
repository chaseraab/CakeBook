package edu.depaul.cdm.se452.grouppp.cakebook.Mealplan;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.depaul.cdm.se452.grouppp.cakebook.Recipe.Recipe;
import edu.depaul.cdm.se452.grouppp.cakebook.User.User;
import edu.depaul.cdm.se452.grouppp.cakebook.User.UserRepository;

@Service
public class MealplanService {
    @Autowired
    private final MealPlanRepository mealplanRepository;

    public MealplanService(MealPlanRepository mealplanRepository) {
        this.mealplanRepository = mealplanRepository;
    }

    public ResponseEntity<List<Mealplan>> getAllMealplans() {
        return new ResponseEntity<>(mealplanRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<String> addMealplan(User user, Mealplan mealplan) {
        try {
            user.addMealplan(mealplan);
            // mealplan.setUser(user);
            mealplanRepository.save(mealplan);

            return new ResponseEntity<>("Mealplan created.", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<Optional<List<Mealplan>>> getMealplans(User user) {
        return new ResponseEntity(mealplanRepository.findById(user.getId()), HttpStatus.OK);
    }

    public ResponseEntity<String> addRecipeToMealplan(Recipe recipe, Mealplan mealplan) {
        Optional<Mealplan> optionalMealplan = mealplanRepository.findById(mealplan.getId());
        if (optionalMealplan.isPresent()) {
            Mealplan temp = optionalMealplan.get();
            temp.addRecipe(recipe);
            mealplanRepository.save(temp);
            return new ResponseEntity<>("Recipe added to mealplan.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Mealplan does not exist.", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> deleteMealplan(Mealplan mealplan, User user) {
        try {
            List<Recipe> recipes = new ArrayList<Recipe>();
            mealplan.setRecipes(recipes);
            user.deleteMealplan(mealplan);
            mealplanRepository.delete(mealplan);
            return new ResponseEntity<>("Mealplan deleted.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Mealplan could not be deleted.", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> deleteRecipeFromMealplan(Mealplan mealplan, Recipe recipe) {
        try {
            mealplan.deleteRecipe(recipe);
            mealplanRepository.save(mealplan);
            return new ResponseEntity<>("Recipe removed from mealplan.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Recipe could not be deleted.", HttpStatus.OK);
        }

    }

}
