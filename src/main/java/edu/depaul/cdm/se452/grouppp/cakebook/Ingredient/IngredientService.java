package edu.depaul.cdm.se452.grouppp.cakebook.Ingredient;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {
    @Autowired
    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public ResponseEntity<List<Ingredient>> getAllIngredients() {
        return new ResponseEntity<>(ingredientRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Ingredient> getIngredientByName(String name) {
        try {
            Optional<Ingredient> ingredientData = ingredientRepository.findByName(name);
            if (ingredientData.isPresent()) {
                return new ResponseEntity<>(ingredientData.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Ingredient> getIngredientById(long id) {
        try {
            Optional<Ingredient> ingredientData = ingredientRepository.findById(id);
            if (ingredientData.isPresent()) {
                return new ResponseEntity<>(ingredientData.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> addIngredient(Ingredient ingredient) {
        try {
            Optional<Ingredient> searchIngredient = ingredientRepository.findByName(ingredient.getName());
            if (searchIngredient.isPresent()) {
                return new ResponseEntity<>("An ingredient with this name already exists.", HttpStatus.CONFLICT);
            }
            ingredientRepository.save(ingredient);
            return new ResponseEntity<>("Ingredient created.", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<HttpStatus> deleteIngredientByName(String name) {
        try {
            Ingredient searchIngredient = ingredientRepository.findByName(name).get();
            ingredientRepository.deleteById(searchIngredient.getId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    public ResponseEntity<HttpStatus> deleteIngredientById(long id) {
        try {
            ingredientRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Ingredient> updateIngredient(long id, Ingredient newIngredient) {
        Optional<Ingredient> searchIngredient = ingredientRepository.findById(id);
        if (searchIngredient.isPresent()) {
            Ingredient _ingredient = searchIngredient.get();
            _ingredient.setName(newIngredient.getName());
            _ingredient.setQuantity(newIngredient.getQuantity());
            _ingredient.setRecipe(newIngredient.getRecipe());
            return new ResponseEntity<>(ingredientRepository.save(_ingredient), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
