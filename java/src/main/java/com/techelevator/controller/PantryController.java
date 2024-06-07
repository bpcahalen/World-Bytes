package com.techelevator.controller;


import com.techelevator.dao.IngredientDao;
import com.techelevator.dao.PantryDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Pantry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/pantry")
@CrossOrigin
public class PantryController {

    // Initialize the dao
    @Autowired PantryDao pantryDao;
    @Autowired UserDao userDao;
    public PantryController() {
    }

    //API calls go here
    // GET current userId by using username, [example] principal.getName(

    // GET all ingredients for the current user
    @GetMapping("/ingredients")
    public List<Pantry> getAllMyIngredients(Principal principal) {
        return getAllMyIngredients(principal);
    }

}
