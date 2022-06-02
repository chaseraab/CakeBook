package edu.depaul.cdm.se452.grouppp.cakebook.Recipe;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.depaul.cdm.se452.grouppp.cakebook.Cookbook.Cookbook;
import edu.depaul.cdm.se452.grouppp.cakebook.Ingredient.Ingredient;
import edu.depaul.cdm.se452.grouppp.cakebook.Instruction.Instruction;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;

import org.json.simple.JSONObject;

import com.google.gson.Gson;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @GetMapping("/all/{searchString}")
    public ResponseEntity<List<Recipe>> searchForRecipes(@PathVariable("searchString") String searchString) {
        return recipeService.searchForRecipes(searchString, null);
    }


    @GetMapping("/{id}/{searchString}")
    public ResponseEntity<List<Recipe>> searchForRecipes(@PathVariable("searchString") String searchString, @PathVariable("id") Long id) {
        return recipeService.searchForRecipes(searchString, id);
    }

    @GetMapping(path="/name/{name}")
    public ResponseEntity<Recipe> getRecipeByName(@PathVariable("name") String name) {
        return recipeService.getRecipeByName(name);
    }

    @GetMapping(path="/id/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable("id") long id) {
        return recipeService.getRecipeById(id);
    }

    @PostMapping("/new/{cookbook}")
    public ResponseEntity<String> addRecipe(@PathVariable Cookbook cookbook, @RequestBody JSONObject values) {
        Recipe recipe = new Gson().fromJson(values.toJSONString(), Recipe.class);
        return recipeService.addRecipe(cookbook, recipe);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteRecipeById(@PathVariable("id") long id){
        return recipeService.deleteRecipeById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateRecipe(@PathVariable Long id, @RequestBody JSONObject values) {
        Recipe recipe = new Gson().fromJson(values.toJSONString(), Recipe.class);
        return recipeService.updateRecipe(id, recipe);
    }
    @GetMapping("/instructions/{id}")
    public ResponseEntity<List<Instruction>> getInstructions(@PathVariable("id") long id) {
        return recipeService.getRecipeInstructions(id);
    }

    @GetMapping("/ingredients/{id}")
    public ResponseEntity<List<Ingredient>> getIngredients(@PathVariable("id") long id) {
        return recipeService.getRecipeIngredients(id);
    }
}