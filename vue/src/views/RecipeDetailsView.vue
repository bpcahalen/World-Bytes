<template>
    <div class="recipe-details">
    <header>
      <img src="../photos/world_byte.png" alt="Logo" class="logo" />
      <nav>
        <router-link to="/">Home</router-link>
        <router-link to="/account">Account</router-link>
        <router-link to="/recipes">Recipes</router-link>
        <router-link to="/meal-plans">Meal Plans</router-link>
        <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      </nav>
    </header>
      <h1>{{ recipe.title }}</h1>
      <img :src="recipe.image" :alt="recipe.title" class="recipe-image"/>
      <p><strong>Duration:</strong> {{ recipe.duration }} minutes</p>
      <p><strong>Category:</strong> {{ recipe.category }}</p>
      <p><strong>Dietary:</strong> {{ recipe.dietary }}</p>
      <p><strong>Servings:</strong> {{ recipe.servings }}</p>
    </div>
  </template>
  
  <script>
  import recipeService from '../services/AuthService';
  
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
  
  .recipe-details p {
    margin: 10px 0;
  }
  </style>
  