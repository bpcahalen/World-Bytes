package com.techelevator.controller;

import com.techelevator.dao.RecipeDao;
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

    private final RecipeDao dao;

    public RecipeController(RecipeDao dao) {
        this.dao = dao;
    }

    @RequestMapping(method = RequestMethod.GET)
    public int findIdByUsername(@RequestParam String username) {
        return recipeDao.findIdByUsername(username);
    }

    //API calls go here
    @GetMapping(path = "/")
    public List<Recipe> searchRecipesByKeyword(Principal principal, String searchQuery) {
        return recipeService.getRecipesByKeyword(searchQuery);
    }

    @GetMapping(path = "/")
    public List<Recipe> searchRecipesByIngredients(Principal principal, String[] ingredients) {
        return recipeService.getRecipesByIngredients(ingredients);
    }

    @GetMapping(path = "/library")
    public List<Recipe> getAllMyRecipes(Principal principal) {
        return getAllMyRecipes(principal);
    }

    @PostMapping(path = "/library")
    public void addRecipe(Principal principal, Recipe recipe) { // return recipe ?
        recipeService.addRecipeToLibrary(recipe);
    }
}
