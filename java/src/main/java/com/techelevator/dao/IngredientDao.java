package com.techelevator.dao;

import com.techelevator.model.Ingredient;

import java.util.List;

public interface IngredientDao {
    Ingredient createIngredient(Ingredient ingredient);

    List<Ingredient> getAllIngredients();
}
