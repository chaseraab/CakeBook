package edu.depaul.cdm.se452.grouppp.cakebook.Cookbook;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CookbookRepository extends JpaRepository<Cookbook, Long> {

    @Query(value = "select c.id as \"id\", c.id, c.name, c.favorite, c.created_at, c.updated_at from cookbooks c, users_cookbooks uc where uc.cookbook_id = c.id and uc.user_id = ?1", nativeQuery = true)
    Optional<List<Cookbook>> findAllById(Long id);
    
}
