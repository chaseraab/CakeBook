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

    @Modifying
    @Transactional
    @Query(value = "delete from instructions where recipe_id = :id", nativeQuery = true)
    void deleteInstructionsFromRecipe(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "update instructions set recipe_id = :recipe_id where recipe_id = :id", nativeQuery = true)
    void updateInstructionOwner(@Param("recipe_id") Long recipe_id, @Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "delete from ingredients where recipe_id = :id", nativeQuery = true)
    void deleteIngredientsFromRecipe(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "update ingredients set recipe_id = :recipe_id where recipe_id = :id", nativeQuery = true)
    void updateIngredientOwner(@Param("recipe_id") Long recipe_id, @Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "update recipes set id = :recipe_id where id = :id", nativeQuery = true)
    void updateId(@Param("recipe_id") Long recipe_id, @Param("id") Long id);

    @Query(value = "SELECT r.id as \"id\", r.name as \"name\", r.cook_time as \"cook_time\", r.prep_time as \"prep_time\", r.favorite as \"favorite\", r.is_public as \"is_public\", r.author as \"author\", r.mealplan_id as \"mealplan_id\" FROM RECIPES r, COOKBOOKS_RECIPES cr, USERS_COOKBOOKS uc where r.id = cr.recipe_id and cr.cookbook_id = uc.cookbook_id and uc.user_id = :id", nativeQuery = true)
    List<Recipe> getAllUserRecipes(@Param("id") Long id);
}