package edu.depaul.cdm.se452.grouppp.cakebook.Recipe;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

    public ResponseEntity<String> addRecipe(Recipe recipe) {
        try {
            Optional<Recipe> searchRecipe = recipeRepository.findByName(recipe.getName());
            if (searchRecipe.isPresent()) {
                return new ResponseEntity<>("An recipe with this name already exists.", HttpStatus.CONFLICT);
            }
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
            recipeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Recipe> updateRecipe(long id, Recipe newRecipe) {
        Optional<Recipe> searchRecipe = recipeRepository.findById(id);
        if (searchRecipe.isPresent()) {
            Recipe _recipe = searchRecipe.get();
            _recipe.setName(newRecipe.getName());
            _recipe.setCookbook(newRecipe.getCookbook());
            _recipe.setIngredients(newRecipe.getIngredients());
            _recipe.setIntstructions(newRecipe.getIntstructions());
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
