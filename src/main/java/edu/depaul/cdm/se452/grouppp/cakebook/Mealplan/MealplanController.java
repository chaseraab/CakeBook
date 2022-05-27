package edu.depaul.cdm.se452.grouppp.cakebook.Mealplan;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}