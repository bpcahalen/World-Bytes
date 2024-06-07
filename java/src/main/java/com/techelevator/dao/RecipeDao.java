package com.techelevator.dao;

import com.techelevator.model.Recipe;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.List;


@Component
public interface RecipeDao {

    List<Recipe> getAllMyRecipes(Principal Principal);

    int findIdByUsername(String username);

    void addRecipeToLibrary();

    void updateRecipeInLibrary(Recipe recipe);
}
