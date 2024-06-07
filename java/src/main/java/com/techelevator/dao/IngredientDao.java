package com.techelevator.dao;

import com.techelevator.model.Ingredient;
import org.springframework.stereotype.Component;

import java.util.List;

public interface IngredientDao {
    Ingredient createIngredient(Ingredient ingredient);

    List<Ingredient> getAllIngredients();
}
