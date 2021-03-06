package edu.depaul.cdm.se452.grouppp.cakebook.Ingredient;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.depaul.cdm.se452.grouppp.cakebook.Recipe.Recipe;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {

    @Autowired
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Ingredient>> getAllIngredients() {
        return ingredientService.getAllIngredients();
    }

    @GetMapping(path = "/name/{name}")
    public ResponseEntity<List<Ingredient>> getIngredientByName(@PathVariable("name") String name) {
        return ingredientService.getIngredientByName(name);
    }

    @GetMapping(path = "/id/{id}")
    public ResponseEntity<Ingredient> getIngredientById(@PathVariable("id") long id) {
        return ingredientService.getIngredientById(id);
    }

    @PostMapping("/new/{recipe}")
    public ResponseEntity<String> addIngredient(@PathVariable("recipe") Recipe recipe,
            @RequestBody Ingredient ingredient) {
        return ingredientService.addIngredient(recipe, ingredient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteIngredientById(@PathVariable("id") long id) {
        return ingredientService.deleteIngredientById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> updateIngredient(@PathVariable("id") long id,
            @RequestBody Ingredient newIngredient) {
        return ingredientService.updateIngredient(id, newIngredient);
    }

    @PatchMapping("/checked/{id}")
    public ResponseEntity<String> setCheckedIngredient(@PathVariable("id") long id, @RequestBody Boolean checked) {
        return ingredientService.setCheckedIngredient(id, checked);
    }
}