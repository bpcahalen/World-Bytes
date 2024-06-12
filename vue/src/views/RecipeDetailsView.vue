<template>
    <div class="recipe-details">
      <h1>{{ recipe.title }}</h1>
      <img :src="recipe.image" :alt="recipe.title" />
      <p>{{ recipe.description }}</p>
      <p><strong>Time to make:</strong> {{ recipe.time }} minutes</p>
    </div>
  </template>
  
  <script>
  import recipeService from '../services/RecipeService';
  
  export default {
    data() {
      return {
        recipe: {}
      };
    },
    created() {
      const recipeId = this.$route.params.id;
      recipeService.getRecipeById(recipeId).then(response => {
        this.recipe = response.data;
      }).catch(error => {
        console.error("Error fetching recipe details:", error);
      });
    }
  };
  </script>
  
  <style scoped>
  .recipe-details {
    max-width: 600px;
    margin: auto;
    padding: 20px;
    background: #fff;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    border-radius: 8px;
  }
  
  .recipe-details img {
    max-width: 100%;
    border-radius: 8px;
  }
  
  .recipe-details h1 {
    margin-top: 0;
  }
  </style>
  