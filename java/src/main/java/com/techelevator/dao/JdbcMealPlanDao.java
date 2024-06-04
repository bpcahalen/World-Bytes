package com.techelevator.dao;


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
    public List<MealPlan> getAllMyMealPlans(Principal principal) {
        List <MealPlan> mealPlanList = new ArrayList<>();
        int userId = findIdByUsername(principal.getName());

        String sql = "SELECT *\n" +
                "FROM meal_plan\n" +
                "WHERE user_id = ?;";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId);

        while (rowSet.next()) {
            mealPlanList.add(mapMealPlanFromRowSet(rowSet));
        }

        return mealPlanList;
    }

    private MealPlan mapMealPlanFromRowSet(SqlRowSet rowSet) {

        MealPlan mealPlan = new MealPlan();

        int mealPlanId = rowSet.getInt("meal_plan_id");
        int recipeId = rowSet.getInt("recipe_id");
        String title = rowSet.getString("title");
        int duration = rowSet.getInt("duration");
        String type = rowSet.getString("type");
        String dietaryRestrictions = rowSet.getString("dietary_restrictions");


        mealPlan.setMealPlanId(mealPlanId);
        mealPlan.setRecipeId(recipeId);
        mealPlan.setTitle(title);
        mealPlan.setDuration(duration);
        mealPlan.setType(type);
        mealPlan.setDietaryRestrictions(dietaryRestrictions);

        return mealPlan;
    }
}
