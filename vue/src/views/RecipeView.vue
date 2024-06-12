<template>
    <div id="recipeList">
        <header id="navigation">
            <nav>
                <router-link to="/">Home</router-link>
                <router-link to="/account">Account</router-link>
                <router-link to="/recipes">Recipes</router-link>
                <router-link to="/meal-plans">Meal Plans</router-link>
                <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
            </nav>
        </header>
        <h1 class="recipeTitle">Recipes</h1>
        <div class="nav">
            <div id="nameSearch">
                <label for="name">Recipe Name: </label>
                <input type:text id="name" v-model="filter.name" placeholder="Search by name...">
            </div>
            <div id="timeSearch">
                <label for="time">Time(in mins): </label>
                <select id="time" v-model="filter.time">
                    <option value="0">0</option>
                    <option value="15">15</option>
                    <option value="30">30</option>
                    <option value="45">45</option>
                    <option value="60">60</option>
                </select>
            </div>
            <div id="occasion">
                <label for="occasion">Occasion: </label>
                <input type:text id="occasion" v-model="filter.category" placeholder="Search by category...">
            </div>
            <div id="dietary">
                <label for="dietary">Diet Type: </label>
                <input type:text id="dietary" v-model="filter.dietary" placeholder="Search by diet...">
            </div>
        </div>
        <div class="main">
            <recipesDesign :recipes="filteredList" />
        </div>
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
import recipesDesign from '../components/recipe.vue';
import authService from '../services/AuthService';

export default {
    components: {
        recipesDesign
    },
    data() {
        return {
            recipes: [
                {
                    recipeId: 1,
                    image: "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/chorizo-mozarella-gnocchi-bake-cropped-9ab73a3.jpg?quality=90&resize=556,505",
                    title: "Chicken alfredo with some parmesan encrustated cauliflower",
                    duration: 30,
                    category: "Lunch",
                    dietary: "none",
                    servings: 2
                },
                {
                    recipeId: 2,
                    image: "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/chorizo-mozarella-gnocchi-bake-cropped-9ab73a3.jpg?quality=90&resize=556,505",
                    title: "Gnocchi",
                    duration: 60,
                    category: "Dinner",
                    dietary: "none",
                    servings: 4
                },
                {
                    recipeId: 3,
                    image: "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/chorizo-mozarella-gnocchi-bake-cropped-9ab73a3.jpg?quality=90&resize=556,505",
                    title: "Pie",
                    duration: 30,
                    category: "Dessert",
                    dietary: "none",
                    servings: 2
                },
                {
                    recipeId: 4,
                    image: "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/chorizo-mozarella-gnocchi-bake-cropped-9ab73a3.jpg?quality=90&resize=556,505",
                    title: "Chicken alfredo",
                    duration: 30,
                    category: "Lunch",
                    dietary: "none",
                    servings: 2
                },
                {
                    recipeId: 5,
                    image: "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/chorizo-mozarella-gnocchi-bake-cropped-9ab73a3.jpg?quality=90&resize=556,505",
                    title: "iced tea",
                    duration: 60,
                    category: "Dinner",
                    dietary: "none",
                    servings: 4
                },
                {
                    recipeId: 6,
                    image: "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/chorizo-mozarella-gnocchi-bake-cropped-9ab73a3.jpg?quality=90&resize=556,505",
                    title: "Pie",
                    duration: 30,
                    category: "Dessert",
                    dietary: "none",
                    servings: 2
                }
],
            filter: {
                name: "",
                duration: "",
                category: "",
                dietary: ""
            }
        }
    },
    methods: {
        
    },
    created(){
        authService.getRecipes().then(response => {
                this.recipes = response.data;
            })
    },
    computed: {
        filteredList() {
            let filterRecipe = this.recipes;
            if (this.filter.name != "") {
                filterRecipe = filterRecipe.filter(recipe =>
                    recipe.title
                        .toLowerCase()
                        .includes(this.filter.name.toLowerCase())
                );
            }
            if (this.filter.category != "") {
                filterRecipe = filterRecipe.filter(recipe =>
                    recipe.category
                        .toLowerCase()
                        .includes(this.filter.category.toLowerCase())
                )
            }
            if (this.filter.duration != 0) {
                filterRecipe = filterRecipe.filter(recipe =>
                    recipe.duration <= this.filter.duration
                )
            }
            if (this.filter.dietary != "") {
                filterRecipe = filterRecipe.filter(recipe =>
                    recipe.dietary
                        .toLowerCase()
                        .includes(this.filter.dietary.toLowerCase())
                )
            }
            return filterRecipe;
        }
    }
}

</script>

<style>
#recipeList {
    background-color: #369cdb;
}

.nav {
    background-color: white;
    height: 70px;
    width: 100vw;
    display: flex;
    justify-content: space-around;
    align-items: center;
    font-family: Varela Round;
    font-size: 15px;
    font-weight: bold;
}

nav a {
  margin: 0 10px;
  color: white;
  text-decoration: none;
  font-size: 20px
}

nav a:hover {
  text-decoration: underline;
}

.recipeTitle{
  font-family: Merienda;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 25px;
  font-size : 75px;
  margin-top : 0;
}

.main{
    display: flex;
    justify-content: center;
    flex-wrap: wrap;
}

label {
    display: inline;
}

input {
  padding: 4px;
  max-width: 150px;
}

#nameSearch{
    width: 300px;
    display: flex;
    justify-content: center;
    align-items: center;
}

#nameSearch label{
    margin : 10px;
}

#timeSearch{
    width: 250px;
    display: flex;
    justify-content: center;
    align-items: center;
}

#timeSearch label{
    margin: 10px;
}

#occasion{
    width: 300px;
    display: flex;
    justify-content: center;
    align-items: center;
}

#occasion label {
    margin: 10px;
}

#dietary{
    width: 300px;
    display: flex;
    justify-content: center;
    align-items: center;
}

#dietary label {
    margin: 10px;
}

footer {
  padding: 20px;
  background-color: #369cdb;
  color: white;
  text-align: center;
  font-size: 25px;
}

footer p{
    margin-top : 0;
    margin-bottom: 10px;
}

footer .socials a {
  margin: 0 10px;
  color: white;
  text-decoration: none;
}

footer .socials a:hover {
  text-decoration: underline;
}

#waltFooter{
  height: 55px;
}

</style>