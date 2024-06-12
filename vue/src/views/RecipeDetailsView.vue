<template>
    <div class="recipe-details">
      <header id="navigation">
            <nav>
                <router-link to="/">Home</router-link>
                <router-link to="/account">Account</router-link>
                <router-link to="/recipes">Recipes</router-link>
                <router-link to="/meal-plans">Meal Plans</router-link>
                <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
            </nav>
        </header>
      <h1>{{ recipe.title }}</h1>
      <img :src="recipe.image" :alt="recipe.title" />
      <p>{{ recipe.description }}</p>
      <p><strong>Time to make:</strong> {{ recipe.duration }} minutes</p>
      <footer>
      <p>&copy; 2024 Meal Planning App. All rights reserved. <img id="waltFooter" src="../photos/walter_smiling.png"/></p>
      <div class="socials">
                <a href="https://facebook.com" target="_blank">Facebook <fa :icon="['fab', 'facebook']" /></a>
                <a href="https://twitter.com" target="_blank">Twitter <fa :icon="['fab', 'twitter']" /></a>
                <a href="https://instagram.com" target="_blank">Instagram <fa :icon="['fab', 'instagram']" /></a>
            </div>
    </footer>
    </div>
  </template>
  
  <script>
  import authService from '../services/AuthService';
  
  export default {
    data() {
      return {
        recipe: {}
      };
    },
    created() {
      const recipeId = this.$route.params.id;
      authService.getRecipeById(recipeId).then(response => {
        this.recipe = response.data;
      }).catch(error => {
        console.error("Error fetching recipe details:", error);
      });
    }
  };
  </script>
  
  <style scoped>
  .recipe-details {
    /* max-width: 600px; */
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
  