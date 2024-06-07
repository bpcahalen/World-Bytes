package com.techelevator.dao;


import com.techelevator.exception.DaoException;
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
    public void updateRecipeInLibrary(Recipe recipe) {
    }

    /* Handles adding recipe to database


        String sql = "INSERT INTO recipes_library(user_id, title, ingredient_list,\n" +
            "\t\t\t\t\t\t\tinstructions, summary, duration,\n" +
            "\t\t\t\t\t\t\tdiet_category, dietary_restrictions,\n" +
            "\t\t\t\t\t\t   recipe_source_url, image_path)\n" +
            "VALUES(?,?,?,?,?,?,?,?,?,?)\n" +
            "RETURNING recipe_id;";

        try {
            int recipeId = jdbcTemplate.queryForObject(sql, int.class,
                recipe.getUserId(), recipe.getTitle(), recipe.getIngredientList(),
                recipe.getInstructions(), recipe.getSummary(), recipe.getDuration(),
                recipe.getCategory(), recipe.getDietaryRestriction(), recipe.getSource(),
                recipe.getImage());

            recipe.setRecipeId(recipeId);

        } catch(DaoException ex) {
            System.out.println("Something went wrong: " + ex.getMessage());
        }
        return recipe;  */

    private Recipe mapRecipeFromRowSet(SqlRowSet rowSet) {
        Recipe recipe = new Recipe();

        //userId, ingredientList, summary -- add these
        //enter column data to be mapped here
        int recipeId = rowSet.getInt("recipe_id");
        int userId = rowSet.getInt("user_id");
        String title = rowSet.getString("title");
        String ingredientList = rowSet.getString("ingredient_list");
        String instructions = rowSet.getString("instructions");
        String summary = rowSet.getString("summary");
        int duration = rowSet.getInt("duration");
        String category = rowSet.getString("diet_category");
        String source = rowSet.getString("recipe_source_url");
        String image = rowSet.getString("image_path");
        String dietaryRestriction = rowSet.getString("dietary_restriction");

        recipe.setRecipeId(recipeId);
        recipe.setUserId(userId);
        recipe.setTitle(title);
        recipe.setIngredientList(ingredientList);
        recipe.setInstructions(instructions);
        recipe.setSummary(summary);
        recipe.setDuration(duration);
        recipe.setCategory(category);
        recipe.setSource(source);
        recipe.setImage(image);
        recipe.setDietaryRestriction(dietaryRestriction);

        return recipe;
    }
}
