package com.techelevator.model;

public class Recipe {

    //Instance Variables
    private int recipeId; // Empty unless we put it
    private String title;
    private String instructions;
    private int duration;
    private String category;
    private String dietaryRestriction;
    private String dietaryLabel;
    private String source; // Link to source of recipe for more info
    private String image;

    // Constructor
    public Recipe() {
    }

    //Getters and Setters

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getDietaryRestriction() {
        return dietaryRestriction;
    }

    public void setDietaryRestriction(String dietaryRestriction) {
        this.dietaryRestriction = dietaryRestriction;
    }

    public String getDietaryLabel() {
        return dietaryLabel;
    }

    public void setDietaryLabel(String dietaryLabel) {
        this.dietaryLabel = dietaryLabel;
    }
}
