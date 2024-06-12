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
        <div class="search-bar">
          <input
            type="text"
            v-model="filter.name"
            placeholder="Search for recipes..."
            @keyup.enter="searchRecipes"
          />
          <button @click="searchRecipes">Search</button>
        </div>
        <div class="recipe-list">
          <div v-for="recipe in filteredList" :key="recipe.id" class="recipe-card">
            <img :src="recipe.image" :alt="recipe.title" class="recipe-image" />
            <h3>{{ recipe.title }}</h3>
          </div>
        </div>
        <div class="see-more">
          <router-link to="/recipes" class="see-more-button" @mouseover="expandButton" @mouseleave="shrinkButton">
            See More
          </router-link>
        </div>
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
      <p>&copy; 2024 Meal Planning App. All rights reserved.</p>
      <div class="socials">
        <a href="https://facebook.com" target="_blank">Facebook</a>
        <a href="https://twitter.com" target="_blank">Twitter</a>
        <a href="https://instagram.com" target="_blank">Instagram</a>
      </div>
    </footer>
  </div>
</template>

<script>
import recipeService from '../services/RecipeService';

export default {
  data() {
    return {
      recipes: [],
      filter: {
        name: "",
        time: "",
        category: "",
        dietary: ""
      }
    };
  },
  created() {
    this.getRecipes();
  },
  methods: {
    getRecipes() {
      recipeService.getRecipes().then(response => {
        this.recipes = response.data;
      }).catch(error => {
        console.error('Error fetching recipes:', error);
      });
    },
    searchRecipes() {
      if (this.filter.name.trim()) {
        recipeService.getRecipes().then(response => {
          this.recipes = response.data.filter(recipe =>
            recipe.title.toLowerCase().includes(this.filter.name.toLowerCase())
          );
        }).catch(error => {
          console.error('Error searching recipes:', error);
        });
      } else {
        this.getRecipes();
      }
    },
    expandButton(event) {
      event.target.style.transform = 'scale(1.1)';
    },
    shrinkButton(event) {
      event.target.style.transform = 'scale(1)';
    }
  },
  computed: {
    filteredList() {
      let filteredRecipes = this.recipes;
      if (this.filter.name) {
        filteredRecipes = filteredRecipes.filter(recipe =>
          recipe.title.toLowerCase().includes(this.filter.name.toLowerCase())
        );
      }
      if (this.filter.category) {
        filteredRecipes = filteredRecipes.filter(recipe =>
          recipe.category.toLowerCase().includes(this.filter.category.toLowerCase())
        );
      }
      if (this.filter.time) {
        filteredRecipes = filteredRecipes.filter(recipe =>
          recipe.time <= this.filter.time
        );
      }
      if (this.filter.dietary) {
        filteredRecipes = filteredRecipes.filter(recipe =>
          recipe.dietary.toLowerCase().includes(this.filter.dietary.toLowerCase())
        );
      }
      return filteredRecipes;
    }
  }
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

.recipes .search-bar {
  display: flex;
  align-items: center;
  gap: 10px;
  margin-bottom: 20px;
}

.recipes .search-bar input {
  flex-grow: 1;
  padding: 10px;
}

.recipes .search-bar button {
  padding: 10px 20px;
  background-color: #369cdb;
  color: white;
  border: none;
  cursor: pointer;
}

.recipes .search-bar button:hover {
  background-color: #287bb5;
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
  text-align: right;
  margin-top: 10px;
}

.see-more-button {
  color: #369cdb;
  text-decoration: none;
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
