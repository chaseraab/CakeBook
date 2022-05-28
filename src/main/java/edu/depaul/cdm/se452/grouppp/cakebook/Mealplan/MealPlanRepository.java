package edu.depaul.cdm.se452.grouppp.cakebook.Mealplan;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MealplanRepository extends JpaRepository<Mealplan, Long> {
    Optional<List<Mealplan>> findByUserId(long user_id);

}
