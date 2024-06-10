package com.techelevator.controller;

import com.techelevator.dao.RecipeDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Recipe;
import com.techelevator.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping(path = "{recipeId}/information")
    public Recipe viewRecipeDetails(@RequestBody Recipe recipe) {
        return recipeService.viewRecipeDetails(recipe);
    }

    @GetMapping(path = "/library")
    public List<Recipe> getAllMyRecipes(Principal principal) {
        int userId = userDao.findIdByUsername(principal.getName());
        return recipeDao.getAllMyRecipes(userId);
    }


    // utilizing viewRecipeDetails to map the recipe model
    // then using recipeDao.addRecipe, post the info into our database as a new recipe
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/library")
    public void addRecipe(@RequestBody Recipe recipe) {
        Recipe recipeToAdd = recipeService.viewRecipeDetails(recipe);
        recipeDao.addRecipe(recipeToAdd);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
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
