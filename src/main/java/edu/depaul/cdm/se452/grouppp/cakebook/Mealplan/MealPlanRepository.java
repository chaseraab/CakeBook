package edu.depaul.cdm.se452.grouppp.cakebook.Mealplan;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MealPlanRepository extends JpaRepository<Mealplan, Long> {
    @Query(value = "select m.id as \"id\", m.id, m.name, m.weekOf from mealplans m, users_mealplans um where um.mealplan_id = m.id and um.user_id = ?1", nativeQuery = true)
    Optional<List<Mealplan>> findAllById(Long id);

    

}
