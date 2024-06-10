<template>
    <div id="recipeList">
        <div id="searchBar">
            <div id="nameSearch">
                <label for="name">Recipe Name: </label>
                <input type:text id="name" v-model="filter.name">
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
                <input type:text id="occasion" v-model="filter.category">
            </div>
            <div id="dietary">
                <label for="dietary">Diet Type: </label>
                <input type:text id="dietary" v-model="filter.dietary">
            </div>
        </div>
        <div>
            <Recipes :recipes="recipes" />
        </div>
    </div>
</template>

<script>
import Recipes from '../components/recipe.vue';
import authService from '../services/AuthService';

export default {
    components: {
        Recipes
    },
    data() {
        return {
            recipes: [],
            filter : {
                name : "",
                time : "",
                category : "",
                dietary : ""
            }
        }
    },
    methods: {
        getRecipes() {
            authService.getRecipes().then(response => {
                this.recipes = response.data;
            });
        }
    },
    computed: {
        filteredList() {
            let filterRecipe = this.filter;
            if(this.filter.name != ""){
                filterRecipe = filterRecipe.filter(recipe => 
                recipe.name
                .toLowerCase()
                .includes(this.filter.name.toLowerCase())
                );
            }
            if(this.filter.category != ""){
                filterRecipe = filterRecipe.filter(recipe => 
                recipe.category
                .toLowerCase()
                .includes(this.filter.category.toLowerCase())
                )
            }
            if(this.filter.time != 0){
                filterRecipe = filterRecipe.filter(recipe => 
                recipe.time <= this.filter.time
                )
            }
            if(this.filter.dietary != ""){
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
#recipeList{
    background-color: blue;
}

#searchBar {
    background-color: lightgrey;
    height: 35px;
    width: 100vw;
    display: flex;
    justify-content: space-evenly;
    align-items: center;
}
</style>