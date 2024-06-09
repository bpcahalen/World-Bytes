package com.techelevator.model;

import java.util.List;

public class MealPlan {

    //Instance Variables
    private int mealPlanId;
    private List<Integer> recipeList;
    private String title;
    private int duration;
    private String type;
    private String dietaryRestrictions;



    //Getters and Setters
    public int getMealPlanId() {
        return mealPlanId;
    }

    public void setMealPlanId(int mealPlanId) {
        this.mealPlanId = mealPlanId;
    }

    public List<Integer> getRecipeId() {
        return recipeList;
    }

    public void setRecipeList(List<Integer> recipeList) {
        this.recipeList = recipeList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDietaryRestrictions() {
        return dietaryRestrictions;
    }

    public void setDietaryRestrictions(String dietaryRestrictions) {
        this.dietaryRestrictions = dietaryRestrictions;
    }
}
