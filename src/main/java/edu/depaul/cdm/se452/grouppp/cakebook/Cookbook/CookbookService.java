package edu.depaul.cdm.se452.grouppp.cakebook.Cookbook;

import java.util.*;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.depaul.cdm.se452.grouppp.cakebook.User.User;
import edu.depaul.cdm.se452.grouppp.cakebook.Recipe.*;;

@Service
public class CookbookService {
    @Autowired
    private final CookbookRepository cookbookRepository;

    public CookbookService(CookbookRepository cookbookRepository) {
        this.cookbookRepository = cookbookRepository;
    }

    public ResponseEntity<List<Cookbook>> getAllCookbooks() {
        return new ResponseEntity<>(cookbookRepository.findAll(), HttpStatus.OK);

    }

    public ResponseEntity<Optional<List<Cookbook>>> getCookbooks(User user) {
        return new ResponseEntity<>(cookbookRepository.findAllById(user.getId()), HttpStatus.OK);

    }

    public ResponseEntity<String> addCookbook(User user, Cookbook cookbook) {
        try {
            user.addCookbook(cookbook);
            cookbookRepository.save(cookbook);

            return new ResponseEntity<>("Cookbook created.", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<List<Recipe>> getCookbookRecipes(Long id) {
        if (cookbookRepository.findById(id).isPresent()) {
            List<Recipe> recipes = new ArrayList<Recipe>();
            recipes = cookbookRepository.findById(id).get().getRecipes();
            return new ResponseEntity<>(recipes, HttpStatus.OK);
        } else {
            return new ResponseEntity(null, HttpStatus.BAD_GATEWAY);
        }
    }

    public ResponseEntity<String> deleteCookbook(Cookbook cookbook, User user) {
        try {
            cookbookRepository.deleteFromUsersCookbooks(cookbook.getId());
            cookbookRepository.delete(cookbook);
            user.deleteCookbook(cookbook);
            return new ResponseEntity<>("Cookbook deleted.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
