package edu.depaul.cdm.se452.grouppp.cakebook.Ingredient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import java.util.Optional;

public interface IngredientRepository extends JpaRepository<Ingredient, Long>{

    Optional<List<Ingredient>> findByName(String name);
    

    @Modifying
    @Query("update Ingredient i set i.is_checked = :is_checked where i.id = :id")
    void updateIsCheckedIngredient(@Param(value = "is_checked") boolean is_checked, @Param(value = "id") long id);


}
