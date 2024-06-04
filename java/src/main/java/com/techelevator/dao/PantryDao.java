package com.techelevator.dao;

import com.techelevator.model.Pantry;

import java.security.Principal;
import java.util.List;

public interface PantryDao {


    int findIdByUsername(String username);
    List<Pantry> getAllMyIngredients(Principal principal);
}
