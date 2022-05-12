package edu.depaul.cdm.se452.grouppp.cakebook;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @GetMapping("/")
    public String health() {
        return "Cakebook is running!";
    }

}
