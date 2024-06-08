package com.techelevator.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techelevator.dao.JdbcRecipeDao;
import com.techelevator.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeService { // Recipe Service; in progress switching to Spoonacular

    @Value("${spoonacular.api.url}")
    private String apiURL;
    @Value("${spoonacular.api.key}")
    private String key;
    @Value("${spoonacular.api.general-endpoint}")
    private String generalEndpoint;
    @Value("${spoonacular.api.ingredient-endpoint}")
    private String ingredientEndpoint;
    @Value("${spoonacular.api.necessary-parameters}")
    private String necessaryParameters;
    @Value("${spoonacular.api.random-parameter}")
    private String randomParameter;
    @Autowired private JdbcRecipeDao recipeDao;

    public RecipeService() {
    }

    public void addRecipeToLibrary() {

    }

    public Recipe viewRecipeDetails() {
        return null;
    }

    /**
     * This method accepts a general keyword and returns a list of recipe objects that
     * are associated with that keyword. If there is no searchQuery, then it returns a
     * random list
     *
     * @param searchQuery String containing the keyword that the user would like to search for
     * @return List of RecipeDto objects that align with the provided keyword
     */
    public List<Recipe> getRecipesByKeyword(String searchQuery) {
        String url = this.apiURL + this.generalEndpoint + this.key + this.necessaryParameters;

        // If there is no searchQuery, it will return a random assortment of Recipes
        if (searchQuery.isEmpty()) {
            url += this.randomParameter;
        } else {
            url += "&query=" + searchQuery;
        }

        // Setting up httpEntity and restTemplate
        HttpEntity<String> httpEntity = new HttpEntity<>("");
        RestTemplate restTemplate = new RestTemplate();

        // Setting up objectMapper and jsonNode
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode;

        // Get request:
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                httpEntity,
                String.class
        );

        // Setting up list that will be hold recipes
        List<Recipe> recipeDisplays = new ArrayList<>();

        // Mapping the response data to the recipe model and catching potential exceptions
        try {
            // Setting the jsonNode to the "results" attribute that holds recipes
            jsonNode = objectMapper.readTree(response.getBody());
            JsonNode root = jsonNode.path("results");

            // For loop to add recipes to the list; only accepting the id, title, and image currently
            for (int i=0; i < root.size(); i++) {
                int id = root.path(i).path("id").asInt();
                String title = root.path(i).path("title").asText();
                String image = root.path(i).path("image").asText();

                recipeDisplays.add(mapResponseEntityToRecipeForDisplay(id, title, image));
            }

        } catch (JsonProcessingException e) {
            System.out.println("Something went wrong.");
        }

        return recipeDisplays;
    }

    /**
     * This method accepts a String array of ingredients and applies those as the
     * search parameter(s) to find recipes that include those ingredients.
     *
     * @param ingredients A String array containing food items
     * @return List of recipe objects that include the ingredients provided
     */
    public List<Recipe> getRecipesByIngredients(String[] ingredients) {
//        for (int i=0; i < root.size(); i++) {
//            int id = root.path(i).path("id").asInt();
//            String title = root.path(i).path("title").asText();
//            String instructions = root.path(i).path("analyzedInstructions").asText();
//            int duration = root.path(i).path("readyInMinutes").asInt();
////                String category;
//            String source = root.path(i).path("sourceUrl").asText();
//            String image = root.path(i).path("image").asText();
////                String dietaryLabel;
//
//            recipeDisplays.add(mapResponseEntityToRecipeDisplay(id, title, image));
//        }

        return null;
    }

    /*
    Can add more methods to search by diet, category, occassion, etc.
     */

    /**
     * Maps an API response entity to a Recipe object
     * @return A recipe object including all of the information passed into the method
     */
    public Recipe mapResponseEntityToRecipe(int recipeId, String title, String instructions, int duration,
                                      String source, String image) {
        Recipe recipe = new Recipe();
        recipe.setRecipeId(recipeId);
        recipe.setTitle(title);
        recipe.setInstructions(instructions);
        recipe.setDuration(duration);
        recipe.setSource(source);
        recipe.setImage(image);
//        recipe.setCategory();
//        recipe.setDietaryLabel();
        return recipe;
    }

    public Recipe mapResponseEntityToRecipeForDisplay(int recipeId, String title, String image) {
        Recipe recipeDisplay = new Recipe();
        recipeDisplay.setRecipeId(recipeId);
        recipeDisplay.setTitle(title);
        recipeDisplay.setImage(image);
        return recipeDisplay;
    }
}