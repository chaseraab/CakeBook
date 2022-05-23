package edu.depaul.cdm.se452.grouppp.cakebook.Cookbook;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CookbookRepository extends JpaRepository<Cookbook, Long> {

    @Query(value = "SELECT * FROM Users_Cookbooks JOIN Users ON Users.id = Users_Cookbooks.user_id JOIN Cookbooks ON Cookbooks.id = Users_Cookbooks.cookbook_id WHERE Users.id = ?1", nativeQuery = true)
    Optional<List<Cookbook>> findAllById(Long id);
    
}
