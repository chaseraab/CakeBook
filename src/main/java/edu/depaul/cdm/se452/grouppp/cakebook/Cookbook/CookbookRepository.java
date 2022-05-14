package edu.depaul.cdm.se452.grouppp.cakebook.Cookbook;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CookbookRepository extends JpaRepository<Cookbook, Long>{
    
}
