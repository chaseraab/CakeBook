package edu.depaul.cdm.se452.grouppp.cakebook.Recipe;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface RecipeRepository extends JpaRepository<Recipe, Long>{

    Optional<Recipe> findByName(String name);

    @Modifying
    @Transactional
    @Query(value = "delete from cookbooks_recipes where recipe_id = :id", nativeQuery = true)
    void deleteFromCookbooksRecipes(@Param("id") Long id);


}