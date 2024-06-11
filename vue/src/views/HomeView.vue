<template>
  <div class="home">
    <header>
      <img src="../photos/world_byte.png" alt="Logo" class="logo" />
      <nav>
        <router-link to="/">Home</router-link>
        <router-link to="/account">Account</router-link>
        <router-link to="/recipes">Recipes</router-link>
        <router-link to="/meal-plans">Meal Plans</router-link>
      </nav>
    </header>
    <main>
      <h1>Welcome to Our Meal Planning Application</h1>
      <p>Start your journey by exploring our recipes or creating a meal plan tailored to your needs.</p>
      
      <section class="recipes">
        <h2>Our Recipes</h2>
        <input type="text" v-model="searchQuery" placeholder="Search for recipes..." @input="searchRecipes" />
        <div class="recipe-list">
          <div class="recipe-card" v-for="recipe in recipes" :key="recipe.id">
            <h3>{{ recipe.title }}</h3>
            <img :src="recipe.image" :alt="recipe.title" class="recipe-image"/>
          </div>
        </div>
        <router-link to="/recipes" class="see-more">See more recipes</router-link>
      </section>
      
      <section class="about-meals">
        <h2>About Our Meals</h2>
        <p>We offer a wide variety of meals to cater to different tastes and dietary needs. Whether you are looking for healthy options, quick meals, or gourmet dishes, we have something for everyone.</p>
      </section>
      
      <section class="meal-plans">
        <h2>How to Make a Meal Plan</h2>
        <p>Creating a meal plan with our application is easy and fun. Start by browsing our recipes, add your favorites to your meal plan, and generate a grocery list with a single click.</p>
      </section>

      <section class="more-info">
        <h2>Get Started Now</h2>
        <p>Sign up today and take control of your meals. Whether you are a busy professional or a parent looking to streamline family dinners, our application is here to help.</p>
      </section>
    </main>
    <footer>
      <p>&copy; 2024 Meal Planning App. All rights reserved.<img id="waltFooter" src="../photos/walter_smiling.png"/></p>
      <div class="socials">
        <a href="https://facebook.com" target="_blank">Facebook <fa :icon="['fab', 'facebook']"/></a>
        <a href="https://twitter.com" target="_blank">Twitter <fa :icon="['fab', 'twitter']"/></a>
        <a href="https://instagram.com" target="_blank">Instagram <fa :icon="['fab', 'instagram']"/></a>
      </div>
    </footer>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      recipes: [],
      searchQuery: '',
    };
  },
  created() {
    this.fetchDefaultRecipes();
  },
  methods: {
    fetchDefaultRecipes() {
      const apiKey = 'd9db55cd6bf247eb8dba11df7fceb331'; 
      const apiUrl = 'https://api.spoonacular.com/recipes/random';
      
      axios.get(apiUrl, {
        params: {
          apiKey: apiKey,
          number: 10
        }
      })
      .then(response => {
        this.recipes = response.data.recipes;
      })
      .catch(error => {
        console.error("There was an error fetching the default recipes:", error);
      });
    },
    searchRecipes() {
      if (this.searchQuery.trim()) {
        const apiKey = 'd9db55cd6bf247eb8dba11df7fceb331'; 
        const apiUrl = 'https://api.spoonacular.com/recipes/complexSearch';
        
        axios.get(apiUrl, {
          params: {
            apiKey: apiKey,
            query: this.searchQuery
          }
        })
        .then(response => {
          this.recipes = response.data.results;
        })
        .catch(error => {
          console.error("There was an error searching for recipes:", error);
        });
      } else {
        this.fetchDefaultRecipes();
      }
    },
  },
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Merienda:wght@600&family=Varela+Round&display=swap');

.home {
  font-family: 'Varela Round', sans-serif;
  padding: 20px;
}

header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #369cdb;
  padding: 10px 20px;
}

.logo {
  height: 50px;
}

nav a {
  margin: 0 10px;
  color: white;
  text-decoration: none;
}

nav a:hover {
  text-decoration: underline;
}

main {
  margin-top: 20px;
}

h1 {
  font-family: 'Merienda', cursive;
  color: #00b35c;
}

section {
  margin-bottom: 20px;
}

.recipes input {
  display: block;
  margin-bottom: 10px;
  padding: 10px;
  width: 100%;
  max-width: 400px;
}

.recipe-list {
  display: flex;
  flex-wrap: wrap;
}

.recipe-card {
  background: white;
  border: 1px solid #ccc;
  border-radius: 8px;
  padding: 10px;
  margin: 10px;
  flex: 1 0 30%;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.recipe-image {
  max-width: 100%;
  height: auto;
  border-radius: 8px;
}

.see-more {
  display: block;
  margin-top: 10px;
  text-align: right;
  color: #369cdb;
}

footer {
  margin-top: 40px;
  padding: 20px;
  background-color: #369cdb;
  color: white;
  text-align: center;
}

footer .socials a {
  margin: 0 10px;
  color: white;
  text-decoration: none;
}

footer .socials a:hover {
  text-decoration: underline;
}


</style>
