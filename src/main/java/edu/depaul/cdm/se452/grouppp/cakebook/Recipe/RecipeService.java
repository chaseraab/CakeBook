package edu.depaul.cdm.se452.grouppp.cakebook.Recipe;

import org.springframework.beans.factory.annotation.Autowired;

import edu.depaul.cdm.se452.grouppp.cakebook.Cookbook.*;
import edu.depaul.cdm.se452.grouppp.cakebook.Instruction.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {
    @Autowired
    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public ResponseEntity<List<Recipe>> getAllRecipes() {
        return new ResponseEntity<>(recipeRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Recipe> getRecipeByName(String name) {
        try {
            Optional<Recipe> recipeData = recipeRepository.findByName(name);
            if (recipeData.isPresent()) {
                return new ResponseEntity<>(recipeData.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Recipe> getRecipeById(long id) {
        try {
            Optional<Recipe> recipeData = recipeRepository.findById(id);
            if (recipeData.isPresent()) {
                return new ResponseEntity<>(recipeData.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> addRecipe(Cookbook cookbook, Recipe recipe) {
        try {
            Optional<Recipe> searchRecipe = recipeRepository.findByName(recipe.getName());
            if (searchRecipe.isPresent()) {
                return new ResponseEntity<>("A recipe with this name already exists.", HttpStatus.CONFLICT);
            }
            cookbook.addRecipe(recipe);
            recipeRepository.save(recipe);
            return new ResponseEntity<>("Recipe created.", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<HttpStatus> deleteRecipeByName(String name) {
        try {
            Recipe searchRecipe = recipeRepository.findByName(name).get();
            recipeRepository.deleteById(searchRecipe.getId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    public ResponseEntity<HttpStatus> deleteRecipeById(long id) {
        try {
            System.out.println("Deleteing id: " + id);
            recipeRepository.deleteFromCookbooksRecipes(id);
            recipeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.valueOf(e.toString()));
        }
    }

    public ResponseEntity<List<Instruction>> getRecipeInstructions(Long id) {
        if (recipeRepository.findById(id).isPresent()) {
            List<Instruction> instructions = new ArrayList<Instruction>();
            instructions = recipeRepository.findById(id).get().getInstructions();
            return new ResponseEntity<>(instructions, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Recipe> updateRecipe(long id, Recipe newRecipe) {
        Optional<Recipe> searchRecipe = recipeRepository.findById(id);
        if (searchRecipe.isPresent()) {
            Recipe _recipe = searchRecipe.get();
            _recipe.setName(newRecipe.getName());
            _recipe.setIngredients(newRecipe.getIngredients());
            _recipe.setInstructions(newRecipe.getInstructions());
            _recipe.setName(newRecipe.getName());
            _recipe.setCookTime(newRecipe.getCookTime());
            _recipe.setPrepTime(newRecipe.getPrepTime());
            _recipe.setFavorite(newRecipe.getFavorite());
            _recipe.setIsPublic(newRecipe.getIsPublic());
            _recipe.setAuthor(newRecipe.getAuthor());
            return new ResponseEntity<>(recipeRepository.save(_recipe), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
