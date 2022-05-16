package edu.depaul.cdm.se452.grouppp.cakebook.Cookbook;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.depaul.cdm.se452.grouppp.cakebook.User.User;

@Service
public class CookbookService {
    @Autowired
    private final CookbookRepository cookbookRepository;

    public CookbookService(CookbookRepository cookbookRepository) {
        this.cookbookRepository = cookbookRepository;
    }

    public ResponseEntity<List<Cookbook>> getAllCookbooks() {
        return new ResponseEntity<>(cookbookRepository.findAll(), HttpStatus.OK);

    }

    public ResponseEntity<String> addCookbook(User user, Cookbook cookbook) {
        try {
            user.addCookbook(cookbook);
            cookbookRepository.save(cookbook);

            return new ResponseEntity<>("Cookbook created.", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }

    }

}
