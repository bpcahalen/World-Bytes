package com.techelevator.controller;


import com.techelevator.dao.IngredientDao;
import com.techelevator.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredients")
@CrossOrigin
public class IngredientController {

    private final IngredientDao ingredientDao;

    @Autowired
    public IngredientController(IngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
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
}
