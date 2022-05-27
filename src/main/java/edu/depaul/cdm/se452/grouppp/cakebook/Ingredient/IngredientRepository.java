package edu.depaul.cdm.se452.grouppp.cakebook.Ingredient;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import java.util.Optional;

public interface IngredientRepository extends JpaRepository<Ingredient, Long>{

    Optional<List<Ingredient>> findByName(String name);

}
