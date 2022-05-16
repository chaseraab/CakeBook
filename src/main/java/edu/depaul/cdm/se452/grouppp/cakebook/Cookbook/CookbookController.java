package edu.depaul.cdm.se452.grouppp.cakebook.Cookbook;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.depaul.cdm.se452.grouppp.cakebook.User.User;

@RestController
@RequestMapping("/api/cookbook")
public class CookbookController {

    @Autowired
    private final CookbookService cookbookService;

    public CookbookController(CookbookService cookbookService) {
        this.cookbookService = cookbookService;
    }

    @GetMapping("/get/{user}")
    public ResponseEntity<Optional<List<Cookbook>>> getCookbooks(@PathVariable User user) {
        return cookbookService.getCookbooks(user);

    }

    @PostMapping("/new/{user}")
    public ResponseEntity<String> addCookbook(@PathVariable User user, @RequestBody Cookbook cookbook) {
        return cookbookService.addCookbook(user, cookbook);
    }

}
