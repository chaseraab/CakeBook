package edu.depaul.cdm.se452.grouppp.cakebook.Recipe;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.depaul.cdm.se452.grouppp.cakebook.Cookbook.Cookbook;
import edu.depaul.cdm.se452.grouppp.cakebook.Instruction.Instruction;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;

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

    @GetMapping(path="/name/{name}")
    public ResponseEntity<Recipe> getRecipeByName(@PathVariable("name") String name) {
        return recipeService.getRecipeByName(name);
    }

    @GetMapping(path="/id/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable("id") long id) {
        return recipeService.getRecipeById(id);
    }

    @PostMapping("/new/{cookbook}")
    public ResponseEntity<String> addRecipe(@PathVariable Cookbook cookbook, @RequestBody Recipe recipe) {
        return recipeService.addRecipe(cookbook, recipe);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteRecipeById(@PathVariable("id") long id){
        return recipeService.deleteRecipeById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable("id") long id, @RequestBody Recipe newRecipe) {
        return recipeService.updateRecipe(id, newRecipe);
    }

    @GetMapping("/instructions/{id}")
    public ResponseEntity<List<Instruction>> getInstructions(@PathVariable("id") long id) {
        return recipeService.getRecipeInstructions(id);
    }
}