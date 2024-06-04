package com.techelevator.dao;


import com.techelevator.model.Pantry;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPantryDao implements PantryDao{

    //Initialize the jdbcTemplate
    private final JdbcTemplate jdbcTemplate;

    public JdbcPantryDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    //Methods go here

    @Override
    public int findIdByUsername(String username) {
        if (username == null) throw new IllegalArgumentException("Username cannot be null");

        int userId;
        try {
            userId = jdbcTemplate.queryForObject("SELECT user_id FROM world_byte_user WHERE username = ?", int.class, username);
        } catch (NullPointerException | EmptyResultDataAccessException e) {
            throw new UsernameNotFoundException("User " + username + " was not found.");
        }

        return userId;
    }

    @Override
    public List<Pantry> getAllMyIngredients(Principal principal) {
        List<Pantry> pantryList = new ArrayList<>();
        int userId = findIdByUsername(principal.getName());

        String sql = "SELECT ingredient_id, user_id\n" +
                "FROM pantry\n" +
                "WHERE user_id = ?;";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId);

        while(rowSet.next()) {
            pantryList.add(mapPantryFromRow(rowSet));

        }
        return pantryList;
    }

    public Pantry mapPantryFromRow(SqlRowSet rowSet) {
        Pantry pantry = new Pantry();

        int ingredientId = rowSet.getInt("ingredient_id");
        int userId = rowSet.getInt("user_id");

        pantry.setIngredientId(ingredientId);
        pantry.setUserId(userId);

        return pantry;
    }
}
