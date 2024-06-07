package com.techelevator.dao;

import com.techelevator.model.Recipe;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.List;


@Component
public interface RecipeDao {

    List<Recipe> getAllMyRecipes(int userId);

    void addRecipeToLibrary();

    void updateRecipeInLibrary(Recipe recipe);
}
