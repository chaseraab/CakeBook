package edu.depaul.cdm.se452.grouppp.cakebook.Mealplan;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.depaul.cdm.se452.grouppp.cakebook.User.User;

@Service
public class MealplanService {
    @Autowired
    private final MealplanRepository mealplanRepository;

    public MealplanService(MealplanRepository mealplanRepository) {
        this.mealplanRepository = mealplanRepository;
    }

    public ResponseEntity<List<Mealplan>> getAllMealplans() {
        return new ResponseEntity<>(mealplanRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<String> addMealplan(User user, Mealplan mealplan) {
        try {
            user.addMealplan(mealplan);
            mealplan.setUser(user);
            mealplanRepository.save(mealplan);

            return new ResponseEntity<>("Mealplan created.", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<Optional<List<Mealplan>>> getMealplans(User user) {
        return new ResponseEntity<>(mealplanRepository.findByUserId(user.getId()), HttpStatus.OK);
    }

}
