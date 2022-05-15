package edu.depaul.cdm.se452.grouppp.cakebook.Recipe;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long>{

    Optional<Recipe> findByName(String name);

}