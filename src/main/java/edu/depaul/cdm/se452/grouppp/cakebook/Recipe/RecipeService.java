package edu.depaul.cdm.se452.grouppp.cakebook.Recipe;

import org.springframework.beans.factory.annotation.Autowired;

import edu.depaul.cdm.se452.grouppp.cakebook.Cookbook.*;
import edu.depaul.cdm.se452.grouppp.cakebook.Ingredient.Ingredient;
import edu.depaul.cdm.se452.grouppp.cakebook.Instruction.*;
import edu.depaul.cdm.se452.grouppp.cakebook.Search.KeyValue;
import edu.depaul.cdm.se452.grouppp.cakebook.Search.Search;

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
            System.out.println("Adding recipe to cookbook");
            cookbook.addRecipe(recipe);
            System.out.println("Saving recipe to repository");
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

    public ResponseEntity<List<Ingredient>> getRecipeIngredients(Long id) {
        if (recipeRepository.findById(id).isPresent()) {
            List<Ingredient> ingredients = new ArrayList<Ingredient>();
            ingredients = recipeRepository.findById(id).get().getIngredients();
            return new ResponseEntity<>(ingredients, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> updateRecipe(Long id, Recipe newRecipe) {
        try {
            Recipe oldRecipe = recipeRepository.findById(id).get();
            oldRecipe.setName(newRecipe.name);
            oldRecipe.setCookTime(newRecipe.getCookTime());
            oldRecipe.setPrepTime(newRecipe.prepTime);
            oldRecipe.setFavorite(newRecipe.favorite);
            oldRecipe.setIsPublic(newRecipe.isPublic);
            oldRecipe.setAuthor(newRecipe.author);
            recipeRepository.deleteInstructionsFromRecipe(oldRecipe.getId());
            for (Instruction i : newRecipe.getInstructions()) {
                oldRecipe.addInstruction(i);
            }
            recipeRepository.deleteIngredientsFromRecipe(oldRecipe.getId());
            for (Ingredient i: newRecipe.getIngredients()) {
                oldRecipe.addIngredient(i);
            }
            recipeRepository.save(oldRecipe);
            return new ResponseEntity<>("Recipe successfully updated", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<Recipe>> searchForRecipes(String search, Long id) {
        System.out.println("Reacevied searchString: " + search);
        ArrayList<KeyValue> kvList = Search.parseSearchString(search);
        ArrayList<Recipe> recipes = new ArrayList<>();
        if (id == null) {
            recipes = (ArrayList<Recipe>) recipeRepository.findAll();
        } else {
            recipes = (ArrayList<Recipe>) recipeRepository.getAllUserRecipes(id);
        }
        System.out.println("Extracted values:");
        for (KeyValue k : kvList) {System.out.println(k.key + " " + k.value);}
        List<Recipe> results = Search.searchByCriteria(kvList, recipes);
        if (results != null) {return new ResponseEntity<>(results, HttpStatus.ACCEPTED);} else {return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);}
    }

}
