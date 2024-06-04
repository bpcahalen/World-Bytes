package com.techelevator.controller;

import com.techelevator.dao.RecipeDao;
import com.techelevator.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/recipes")
@CrossOrigin
public class RecipeController {

    @Autowired RecipeDao recipeDao;

    private final RecipeDao dao;

    public RecipeController(RecipeDao dao) {
        this.dao = dao;
    }

    @RequestMapping(path = "/id/username", method = RequestMethod.GET)
    public int findIdByUsername(@RequestParam String username) {
        return recipeDao.findIdByUsername(username);
    }


    //API calls go here
    @GetMapping("/library")
    public List<Recipe> getAllMyRecipes(Principal principal) {
        return getAllMyRecipes(principal);
    }
}
