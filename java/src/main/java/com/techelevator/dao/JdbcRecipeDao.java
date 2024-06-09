package com.techelevator.dao;


import com.techelevator.exception.DaoException;
import com.techelevator.model.Ingredient;
import com.techelevator.model.Recipe;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

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

    // GET
    @Override
    public List<Recipe> getAllMyRecipes(int userId) {
        List<Recipe> recipeList = new ArrayList<>();

        String sql = "SELECT *\n" +
                "FROM recipes\n" +
                "WHERE user_id = ?;";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId);

        while (rowSet.next()) {
//            recipeList.add(mapRecipeFromRowSet(rowSet));
        }

        return recipeList;
    }

    /* Handles adding recipe to database */

    // POST for creating new recipe
    public Recipe addRecipeToLibrary(Recipe recipe) {

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
        return recipe;
    }

    // PUT
    public void updateRecipeInLibrary(Recipe recipe) {

        String sql = "UPDATE recipes_library\n" +
                "SET user_id = ?, title = ?, ingredient_list = ?,\n" +
                "\tinstructions = ?, summary = ?, duration = ?,\n" +
                "\tdiet_category = ?, dietary_restictions = ?,\n" +
                "\trecipe_source_url = ?, image_path = ?\n" +
                "WHERE recipe_id = ?;";

        try {
            jdbcTemplate.update(sql, recipe.getUserId(), recipe.getTitle(), recipe.getIngredientList(),
                    recipe.getInstructions(), recipe.getSummary(), recipe.getDuration(),
                    recipe.getDietCategories(), recipe.getDietaryRestrictions(), recipe.getSource(),
                    recipe.getImage(), recipe.getRecipeId());

        } catch (DaoException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Recipe not found, error: " + ex.getMessage());
        }
    }


    // NOTE: Need to get the ingredientList as an array to make into a list, same with dietaryRestrictions
    private Recipe mapRecipeFromRowSet(SqlRowSet rowSet) {
        Recipe recipe = new Recipe();

        int recipeId = rowSet.getInt("recipe_id");
        int userId = rowSet.getInt("user_id");
        String title = rowSet.getString("title");
//        String ingredientList = rowSet.getString("ingredient_list");
//        String instructions = rowSet.getString("instructions");
        String summary = rowSet.getString("summary");
        int duration = rowSet.getInt("duration");
//        String dietCategory = rowSet.getString("diet_category");
//        String dietaryRestrictions = rowSet.getString("dietary_restrictions");
        String source = rowSet.getString("recipe_source_url");
        String image = rowSet.getString("image_path");

        recipe.setRecipeId(recipeId);
        recipe.setUserId(userId);
        recipe.setTitle(title);
//        recipe.setIngredientList(ingredientList);
//        recipe.setInstructions(instructions);
        recipe.setSummary(summary);
        recipe.setDuration(duration);
//        recipe.setDietCategory(dietCategory);
//        recipe.setDietaryRestrictions(dietaryRestrictions);
        recipe.setSource(source);
        recipe.setImage(image);

        return recipe;
    }
}
