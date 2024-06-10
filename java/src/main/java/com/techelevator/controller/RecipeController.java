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
    @Autowired UserDao userDao;
    @Autowired RecipeService recipeService;


    public RecipeController(RecipeDao recipeDao, UserDao userDao, RecipeService recipeService) {
        this.recipeDao = recipeDao;
        this.userDao = userDao;
        this.recipeService = recipeService;
    }

    //API calls go here
    @GetMapping
    public List<Recipe> searchRecipesByKeyword(String searchQuery) {
        return recipeService.getRecipesByKeyword(searchQuery);
    }

    @GetMapping(path = "/information")
    public Recipe viewRecipeDetails() {
        return recipeService.viewRecipeDetails();
    }

    @GetMapping(path = "/library")
    public List<Recipe> getAllMyRecipes(Principal principal) {
        int userId = userDao.findIdByUsername(principal.getName());
        return recipeDao.getAllMyRecipes(userId);
    }

    @PostMapping(path = "/library")
    public void addRecipe(Recipe recipe) { // return recipe ?
        recipeService.addRecipeToLibrary(recipe);
    }

    @PutMapping("/library/{recipeId}")
    public void updateRecipe(@RequestBody Recipe recipe, @PathVariable int recipeId) {
        recipe.setRecipeId(recipeId);
        recipeDao.updateRecipeInLibrary(recipe);
    }

    // Extra cases; not implemented
    //    @GetMapping(path = "/")
//    public List<Recipe> searchRecipesByIngredients(Principal principal, String[] ingredients) {
//        return recipeService.getRecipesByIngredients(ingredients);
//    }
}
