package com.techelevator.controller;


import com.techelevator.dao.IngredientDao;
import com.techelevator.dao.PantryDao;
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
    private final PantryDao dao;
    public PantryController(PantryDao dao) {
        this.dao = dao;
    }

    //API calls go here


    // GET current userId by using username, [example] principal.getName()
    @RequestMapping(path = "/id/username", method = RequestMethod.GET)
    public int findIdByUsername(@RequestParam String username) {
        return pantryDao.findIdByUsername(username);
    }

    // GET all ingredients for the current user
    @GetMapping("/ingredients")
    public List<Pantry> getAllMyIngredients(Principal principal) {
        return getAllMyIngredients(principal);
    }

}
