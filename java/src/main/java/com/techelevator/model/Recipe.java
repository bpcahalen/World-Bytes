package com.techelevator.model;

import java.util.List;

public class Recipe {

    // Instance Variables
    private int recipeId;
    private int userId;
    private String title;
    private List<Ingredient> ingredientList;
    private List<String> instructions;
    private String summary;
    private int duration;
    private List<String> dietCategories;
    private List<String> dietaryRestrictions;
    private String source;
    private String image;

    // Constructor
    public Recipe() {
    }

    // Getters and Setters

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<String> getDietCategories() {
        return dietCategories;
    }

    public void setDietCategories(List<String> dietCategories) {
        this.dietCategories = dietCategories;
    }

    public List<String> getDietaryRestrictions() {
        return dietaryRestrictions;
    }

    public void setDietaryRestrictions(List<String> dietaryRestrictions) {
        this.dietaryRestrictions = dietaryRestrictions;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
