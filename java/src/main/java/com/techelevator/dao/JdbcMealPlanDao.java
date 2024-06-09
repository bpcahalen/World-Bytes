package com.techelevator.dao;


import com.techelevator.exception.DaoException;
import com.techelevator.model.MealPlan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcMealPlanDao implements MealPlanDao{


    // Initialize the jdbcTemplate
    private final JdbcTemplate jdbcTemplate;

    public JdbcMealPlanDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Methods go here

    @Override
    public List<MealPlan> getAllMyMealPlans(int userId) {
        List <MealPlan> mealPlanList = new ArrayList<>();

        String sql = "SELECT *\n" +
                "FROM meal_plan\n" +
                "WHERE user_id = ?;";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId);

        while (rowSet.next()) {
            mealPlanList.add(mapMealPlanFromRowSet(rowSet));
        }

        return mealPlanList;
    }

    // Need MealPlan tables active before this will function
    @Override
    public boolean createMealPlan(MealPlan mealPlan) {
        MealPlan newMealPlan = new MealPlan();

        String sql = "INSERT INTO meal_plans(title, recipe_list, description, duration, diet_categories, dietary_restrictions)\n" +
                "VALUES(?,?,?,?,?,?)\n" +
                "RETURNING meal_plan_id;";
        try{
            int mealPlanId = jdbcTemplate.queryForObject(sql, int.class, mealPlan.getTitle(),
                    mealPlan.getRecipeList(), mealPlan.getDescription(), mealPlan.getDuration(),
                    mealPlan.getDietCategories(), mealPlan.getDietaryRestrictions());

            mealPlan.setMealPlanId(mealPlanId);

        } catch (DaoException ex) {
            System.out.println("Something went wrong, error: " + ex.getMessage());
        }

        return true;
    }


    private MealPlan mapMealPlanFromRowSet(SqlRowSet rowSet) {

        List<Integer> recipeList = new ArrayList<>();
        List<String> dietCategories = new ArrayList<>();
        MealPlan mealPlan = new MealPlan();

        int mealPlanId = rowSet.getInt("meal_plan_id");
        String title = rowSet.getString("title");
        String recipeListString = rowSet.getString("recipe_list");

        if(recipeListString != null && !recipeListString.isEmpty()) {
            String[] recipeStringArray = recipeListString.split(",");
            for(String id : recipeStringArray) {
                try{
                    recipeList.add(Integer.parseInt(id.trim()));
                } catch(NumberFormatException ex) {
                    System.out.println("Invalid number format" + ex.getMessage());
                }
            }
        }

        String description = rowSet.getString("description");
        int duration = rowSet.getInt("duration");
        String dietCategoriesString = rowSet.getString("diet_categories");

        if(dietCategoriesString != null && !dietCategoriesString.isEmpty()) {
            String[] dietCategoriesStringArray = dietCategoriesString.split(",");
            for (String category : dietCategoriesStringArray) {
                dietCategories.add(category.trim());
            }
        }
        String dietaryRestrictions = rowSet.getString("dietary_restrictions");


        mealPlan.setMealPlanId(mealPlanId);
        mealPlan.setTitle(title);
        mealPlan.setRecipeList(recipeList);
        mealPlan.setDescription(description);
        mealPlan.setDuration(duration);
        mealPlan.setDietCategories(dietCategories);
        mealPlan.setDietaryRestrictions(dietaryRestrictions);

        return mealPlan;
    }
}
