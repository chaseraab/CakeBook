package edu.depaul.cdm.se452.grouppp.cakebook.Cookbook;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.depaul.cdm.se452.grouppp.cakebook.Recipe.*;

@Repository
public interface CookbookRepository extends JpaRepository<Cookbook, Long> {

    @Query(value = "SELECT * FROM Users_Cookbooks JOIN Users ON Users.id = Users_Cookbooks.user_id JOIN Cookbooks ON Cookbooks.id = Users_Cookbooks.cookbook_id WHERE Users.id = ?1", nativeQuery = true)
    Optional<List<Cookbook>> findAllById(Long id);
    
    //@Query(value = "select r.id as \"id\", r.name as \"name\", r.cook_time as \"cook_time\", r.prep_time as \"prep_Time\", r.favorite as \"favorite\", r.is_public as \"is_public\", r.author as \"author\" from recipes r, cookbooks c, cookbooks_recipes rc where r.id = rc.recipe_id and c.id = rc.cookbook_id and c.id = ?1", nativeQuery = true)
    //List<Recipe> findRecipesById(Long id);
}
