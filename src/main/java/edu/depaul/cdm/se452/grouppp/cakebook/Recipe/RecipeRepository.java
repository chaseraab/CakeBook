package edu.depaul.cdm.se452.grouppp.cakebook.Recipe;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface RecipeRepository extends JpaRepository<Recipe, Long>{

    //@Query(value = "SELECT * FROM Cookbooks_Recipes JOIN Cookbooks ON Cookbooks.id = Cookbooks_Recipes.cookbook_id JOIN Recipes ON Recipes.id = Cookbooks_Recipes.recipe_id WHERE Cookbooks.id = ?1", nativeQuery = true)
    Optional<Recipe> findByName(String name);
    Optional<List<Recipe>> findAllById(Long id);

}