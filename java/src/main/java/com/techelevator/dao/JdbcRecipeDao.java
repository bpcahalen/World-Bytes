package com.techelevator.dao;


import com.techelevator.model.Recipe;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcRecipeDao implements RecipeDao {


    // Initialize the jdbcTemplate
    private final JdbcTemplate jdbcTemplate;

    public JdbcRecipeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    //Methods go here

    @Override
    public int findIdByUsername(String username) {
        if (username == null) throw new IllegalArgumentException("Username cannot be null");

        int userId;
        try {
            userId = jdbcTemplate.queryForObject("SELECT user_id FROM users WHERE username = ?", int.class, username);
        } catch (NullPointerException | EmptyResultDataAccessException e) {
            throw new UsernameNotFoundException("User " + username + " was not found.");
        }

        return userId;
    }


    // GET
    @Override
    public List<Recipe> getAllMyRecipes(Principal principal) {
        List <Recipe> recipeList = new ArrayList<>();
        int userId = findIdByUsername(principal.getName());

        String sql = "SELECT *\n" +
                "FROM recipes\n" +
                "WHERE user_id = ?;";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId);

        while (rowSet.next()) {
            recipeList.add(mapRecipeFromRowSet(rowSet));
        }

        return recipeList;
    }

    // POST
    public void addRecipeToLibrary() {

    }

    // PUT
    public void updateRecipeInLibrary() {

    }

    private Recipe mapRecipeFromRowSet(SqlRowSet rowSet) {
        Recipe recipe = new Recipe();

        //enter column data to be mapped here
        int recipeId = rowSet.getInt("recipe_id");
        String title = rowSet.getString("title");
        String instructions = rowSet.getString("instructions");
        int duration = rowSet.getInt("duration");
        String category = rowSet.getString("category");
        String source = rowSet.getString("link");
        String image = rowSet.getString("image");
        String dietaryRestriction = rowSet.getString("dietary_restriction");
        String dietaryLabel = rowSet.getString("dietary_label");

        recipe.setRecipeId(recipeId);
        recipe.setTitle(title);
        recipe.setInstructions(instructions);
        recipe.setDuration(duration);
        recipe.setCategory(category);
        recipe.setSource(source);
        recipe.setImage(image);
        recipe.setDietaryRestriction(dietaryRestriction);
        recipe.setDietaryLabel(dietaryLabel);

        return recipe;
    }
}
