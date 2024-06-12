package com.techelevator.dao;


import com.techelevator.exception.DaoException;
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
            recipeList.add(mapRecipeFromRowSet(rowSet));
        }

        return recipeList;
    }

    /* Handles adding recipe to database */

    // POST for creating new recipe
    @Override
    public Recipe addRecipe(Recipe recipe) {

        String sql = "INSERT INTO recipes_library(user_id, title, ingredient_list,\n" +
            "\t\t\t\t\t\t\tinstructions, summary, duration,\n" +
            "\t\t\t\t\t\t\tdiet_categories, occasions,\n" +
            "\t\t\t\t\t\t   recipe_source_url, image_path)\n" +
            "VALUES(?,?,?,?,?,?,?,?,?,?)\n" +
            "RETURNING recipe_id;";

        String ingredientListString = "";
        for (int i = 0; i < recipe.getIngredientList().size(); i++) {
            if (i == recipe.getIngredientList().size() - 1) { // check to see if it's the last entry
                ingredientListString += recipe.getIngredientList().get(i).getIngredientId() + ":" + recipe.getIngredientList().get(i).getIngredientName();
            } else {
                ingredientListString += recipe.getIngredientList().get(i).getIngredientId() + ":" + recipe.getIngredientList().get(i).getIngredientName() + ",";
            }
        }

        String instructionsString = "";
        for (int i = 0; i < recipe.getInstructions().size(); i++) {
            if (i == recipe.getInstructions().size() - 1) { // check to see if it's the last entry
                instructionsString += recipe.getInstructions().get(i);
            } else {
                instructionsString += recipe.getInstructions().get(i) + "||";
            }
        }

        String dietCategoriesString = "";
        for (int i = 0; i < recipe.getDietCategories().size(); i++) {
            if (i == recipe.getDietCategories().size() - 1) { // check to see if it's the last entry
                dietCategoriesString += recipe.getDietCategories().get(i);
            } else {
                dietCategoriesString += recipe.getDietCategories().get(i) + "||";
            }
        }

        String occasionsString = "";
        for (int i = 0; i < recipe.getOccasions().size(); i++) {
            if (i == recipe.getOccasions().size() - 1) { // check to see if it's the last entry
                occasionsString += recipe.getOccasions().get(i);
            } else {
                occasionsString += recipe.getOccasions().get(i) + "||";
            }
        }

        try {
            int recipeId = jdbcTemplate.queryForObject(sql, int.class, recipe.getUserId(), recipe.getTitle(),
                    ingredientListString, instructionsString, recipe.getSummary(), recipe.getDuration(),
                    dietCategoriesString, occasionsString, recipe.getSource(), recipe.getImage());

            recipe.setRecipeId(recipeId);

        } catch(DaoException ex) {
            System.out.println("Something went wrong: " + ex.getMessage());
        } catch(NullPointerException ex) {

        }

        return recipe;
    }

    // PUT
    @Override
    public void updateRecipeInLibrary(Recipe recipe) {

        String sql = "UPDATE recipes_library\n" +
                "SET user_id = ?, title = ?, ingredient_list = ?,\n" +
                "\tinstructions = ?, summary = ?, duration = ?, servings = ?,\n" +
                "\tdiet_category = ?, occasions = ?,\n" +
                "\trecipe_source_url = ?, image_path = ?\n" +
                "WHERE recipe_id = ?;";

        try {
            int rowsAffected = jdbcTemplate.update(sql, int.class, recipe.getUserId(), recipe.getTitle(), recipe.getIngredientList(),
                    recipe.getInstructions(), recipe.getSummary(), recipe.getDuration(), recipe.getServings(),
                    recipe.getDietCategories(), recipe.getOccasions(), recipe.getSource(),
                    recipe.getImage(), recipe.getRecipeId());

            if(rowsAffected <= 0) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Recipe not found");
            }

        } catch (DaoException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Recipe not found, error: " + ex.getMessage());
        }
    }

    @Override
    public void deleteRecipeFromLibrary(int recipeId) {
        String sql = "DELETE FROM recipes_library\n" +
                "WHERE recipe_id = ?;";

        jdbcTemplate.update(sql, recipeId);
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
        int servings = rowSet.getInt("servings");
//        String dietCategories = rowSet.getString("diet_categories");
//        String occasions = rowSet.getString("occasions");
        String source = rowSet.getString("recipe_source_url");
        String image = rowSet.getString("image_path");

        recipe.setRecipeId(recipeId);
        recipe.setUserId(userId);
        recipe.setTitle(title);
//        recipe.setIngredientList(ingredientList);
//        recipe.setInstructions(instructions);
        recipe.setSummary(summary);
        recipe.setDuration(duration);
        recipe.setServings(servings);
//        recipe.setDietCategory(dietCategory);
//        recipe.setOccasions(occasions);
        recipe.setSource(source);
        recipe.setImage(image);

        return recipe;
    }
}
