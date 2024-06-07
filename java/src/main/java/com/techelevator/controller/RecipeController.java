package com.techelevator.controller;

import com.techelevator.dao.RecipeDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Recipe;
import com.techelevator.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/recipes")
@CrossOrigin
public class RecipeController {

    @Autowired RecipeDao recipeDao;
    @Autowired RecipeService recipeService;
    @Autowired UserDao userDao;


    public RecipeController() {
    }

    //API calls go here
    @GetMapping(path = "")
    public List<Recipe> searchRecipesByKeyword(Principal principal, String searchQuery) {
        return recipeService.getRecipesByKeyword(searchQuery);
    }

    @GetMapping(path = "/")
    public List<Recipe> searchRecipesByIngredients(Principal principal, String[] ingredients) {
        return recipeService.getRecipesByIngredients(ingredients);
    }

    @GetMapping(path = "/library")
    public List<Recipe> getAllMyRecipes(Principal principal) {
        int userId = userDao.findIdByUsername(principal.getName());
        return recipeDao.getAllMyRecipes(userId);
    }

    @PostMapping(path = "/library")
    public void addRecipe() { // return recipe ?
        recipeService.addRecipeToLibrary();
    }

    @PutMapping("/library")
    public void updateRecipe(@RequestBody Recipe recipe) {
        recipeDao.updateRecipeInLibrary(recipe);
    }
}
