package com.techelevator.dao;

import com.techelevator.model.Recipe;

import java.security.Principal;
import java.util.List;

public interface RecipeDao {

    List<Recipe> getAllMyRecipes(Principal Principal);

    int findIdByUsername(String username);
}
