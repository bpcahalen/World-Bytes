<template>
  <div class="home">
    <header>
      <nav id="navigation">
        <router-link to="/">Home</router-link>
        <router-link to="/account">Account</router-link>
        <router-link to="/recipes">Recipes</router-link>
        <router-link to="/meal-plans">Meal Plans</router-link>
      </nav>
    </header>
    <img src="../photos/world_byte.png" alt="Logo" class="logo" />
    <main>
      <h1 class="centered-title">Welcome to Our Meal Planning Application</h1>
      <p class="centered-text">Start your journey by exploring our recipes or creating a meal plan tailored to your needs.</p>
      
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
          <recipeDesign :recipes="filteredList"/>
        </div>
        <div class="see-more">
          <button @mouseover="expandButton" @mouseleave="shrinkButton" @click="viewAllRecipes">
            See More
          </button>
        </div>
      </section>

      <section class="about-meals centered-text">
        <h2>About Our Meals</h2>
        <p>We offer a wide variety of meals to cater to different tastes and dietary needs. Whether you are looking for healthy options, quick meals, or gourmet dishes, we have something for everyone.</p>
      </section>
      
      <section class="meal-plans centered-text">
        <h2>How to Make a Meal Plan</h2>
        <p>Creating a meal plan with our application is easy and fun. Start by browsing our recipes, add your favorites to your meal plan, and generate a grocery list with a single click.</p>
      </section>

      <section class="more-info centered-text">
        <h2>Get Started Now</h2>
        <p>Sign up today and take control of your meals. Whether you are a busy professional or a parent looking to streamline family dinners, our application is here to help.</p>
      </section>
    </main>
    <footer>
            <p>&copy; 2024 Meal Planning App. All rights reserved. <img id="waltFooter"
                    src="../photos/walter_smiling.png" /></p>
            <div class="socials">
                <a href="https://facebook.com" target="_blank">Facebook
                    <fa :icon="['fab', 'facebook']" />
                </a>
                <a href="https://twitter.com" target="_blank">Twitter
                    <fa :icon="['fab', 'twitter']" />
                </a>
                <a href="https://instagram.com" target="_blank">Instagram
                    <fa :icon="['fab', 'instagram']" />
                </a>
            </div>
        </footer>
  </div>
</template>

<script>
import recipeDesign from '../components/recipe.vue'
import recipeService from '../services/AuthService';

export default {
  components: {
    recipeDesign
  },
  data() {
    return {
      recipes: [
      // {
      //               recipeId: 1,
      //               image: "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/chorizo-mozarella-gnocchi-bake-cropped-9ab73a3.jpg?quality=90&resize=556,505",
      //               title: "Chicken alfredo with some parmesan encrustated cauliflower",
      //               duration: 30,
      //               occasions: ['lunch', 'snack'],
      //               dietCategories: "none",
      //               servings: 2
      //           },
      //           {
      //               recipeId: 2,
      //               image: "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/chorizo-mozarella-gnocchi-bake-cropped-9ab73a3.jpg?quality=90&resize=556,505",
      //               title: "Gnocchi",
      //               duration: 60,
      //               occasions: ["Dinner", 'snack'],
      //               dietCategories: ["none"],
      //               servings: 4
      //           },
      //           {
      //               recipeId: 3,
      //               image: "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/chorizo-mozarella-gnocchi-bake-cropped-9ab73a3.jpg?quality=90&resize=556,505",
      //               title: "Pie",
      //               duration: 30,
      //               occasions: ["Dessert", 'snack'],
      //               dietCategories: ["Vegan"],
      //               servings: 2
      //           },
      //           {
      //               recipeId: 4,
      //               image: "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/chorizo-mozarella-gnocchi-bake-cropped-9ab73a3.jpg?quality=90&resize=556,505",
      //               title: "Chicken alfredo",
      //               duration: 30,
      //               occasions: ["Lunch", 'snack'],
      //               dietCategories: ["none"],
      //               servings: 2
      //           },
      //           {
      //               recipeId: 5,
      //               image: "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/chorizo-mozarella-gnocchi-bake-cropped-9ab73a3.jpg?quality=90&resize=556,505",
      //               title: "iced tea",
      //               duration: 60,
      //               occasions: ["Dinner", 'snack'],
      //               dietCategories: ["vegetarian"],
      //               servings: 4
      //           },
      //           {
      //               recipeId: 6,
      //               image: "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/chorizo-mozarella-gnocchi-bake-cropped-9ab73a3.jpg?quality=90&resize=556,505",
      //               title: "Pie",
      //               duration: 30,
      //               occasions: ["Dessert", 'snack'],
      //               dietCategories: ["pescatarian"],
      //               servings: 2
      //           }
      ],
      filter: {
        name: "",
      },
      hoverSeeMore: false
    };
  },
  created() {
    this.getRecipes();
  },
  methods: {
    getRecipes() {
      recipeService.getRecipes("bacon").then(response => {
        this.recipes = response.data.slice(0, 8);
      }).catch(error => {
        console.error('Error fetching recipes:', error);
      });
    },
    searchRecipes() {
      console.log(this.filter.name);
    },
    expandButton(event) {
      event.target.style.transform = 'scale(1.1)';
    },
    shrinkButton(event) {
      event.target.style.transform = 'scale(1)';
    },
    viewAllRecipes() {
      this.$router.push('/recipes');
    }
  },
  computed: {
    filteredList() {
      let filtered = this.recipes;
      if (this.filter.name) {
        filtered = filtered.filter(recipe =>
          recipe.title.toLowerCase().includes(this.filter.name.toLowerCase())
        );
      }
      return filtered.slice(0, 8);
    }
  }
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Merienda:wght@600&family=Varela+Round&display=swap');

.home {
  background-color: #369cdb;
}

nav a {
    margin: 0 20px;
    font-size: 30px;
    color: white;
    text-decoration: none;
}

nav a:hover {
text-decoration: underline;
}

.logo {
  height: 50px;
}

main {
  margin-top: 20px;
}



.centered-title {
  font-family: 'Merienda', cursive;
  color: #00b35c;
  text-align: center;
}

.centered-text {
  text-align: center;
  font-size: 1.2em;
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
