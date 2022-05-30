package edu.depaul.cdm.se452.grouppp.cakebook.Mealplan;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.depaul.cdm.se452.grouppp.cakebook.Recipe.Recipe;
import edu.depaul.cdm.se452.grouppp.cakebook.User.User;

@RestController
@RequestMapping("/api/mealplan")
public class MealplanController {

    @Autowired
    private final MealplanService mealplanService;

    public MealplanController(MealplanService mealplanService) {
        this.mealplanService = mealplanService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Mealplan>> getAllMealplans() {
        return mealplanService.getAllMealplans();
    }

    // add mealplan to user
    @PostMapping("/new/{user}")
    public ResponseEntity<String> addMealplan(@PathVariable User user, @RequestBody Mealplan mealplan) {
        return mealplanService.addMealplan(user, mealplan);
    }

    // return user's mealplans
    @GetMapping("/get/{user}")
    public ResponseEntity<Optional<List<Mealplan>>> getMealplans(@PathVariable User user) {
        return mealplanService.getMealplans(user);

    }

    // add recipe to mealplan
    @PostMapping("/recipe/{recipe}")
    public ResponseEntity<String> addRecipeToMealplan(@PathVariable Recipe recipe, @RequestBody Mealplan mealplan) {
        return mealplanService.addRecipeToMealplan(recipe, mealplan);
    }

    @DeleteMapping("/delete/mealplan/{mealplan}/user/{user}")
    public ResponseEntity<String> deleteMealplan(@PathVariable Mealplan mealplan, @PathVariable User user) {
        return mealplanService.deleteMealplan(mealplan, user);
    }

    // remove recipe from mealplan
    @DeleteMapping("/delete/mealplan/{mealplan}/recipe/{recipe}")
    public ResponseEntity<String> deleteRecipeFromMealplan(@PathVariable Mealplan mealplan,
            @PathVariable Recipe recipe) {
        return mealplanService.deleteRecipeFromMealplan(mealplan, recipe);
    }
}
