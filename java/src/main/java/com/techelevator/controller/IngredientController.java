package com.techelevator.controller;


import com.techelevator.dao.IngredientDao;
import com.techelevator.dao.PantryDao;
import com.techelevator.model.Ingredient;
import com.techelevator.model.Pantry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/ingredients")
@CrossOrigin
public class IngredientController {

    @Autowired IngredientDao ingredientDao;
    @Autowired PantryDao pantryDao;

    public IngredientController(IngredientDao ingredientDao, PantryDao pantryDao) {
        this.ingredientDao = ingredientDao;
        this.pantryDao = pantryDao;
    }

    // API calls go here

    // need a GET to retrieve all ingredients
    @GetMapping
    public List<Ingredient> getAllIngredients() {
        return ingredientDao.getAllIngredients();
    }

    // POST for creating a new ingredient
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Ingredient createIngredient(@RequestBody Ingredient ingredient) {
        return ingredientDao.createIngredient(ingredient);
    }

    @GetMapping("/ingredients")
    public List<Pantry> getAllMyIngredients(Principal principal) {
        return pantryDao.getAllMyIngredients(principal);
    }
}
