<template>
    <div id="account">
        <img src="../photos/world_byte.png"/>
        <h1>{{  choice }}</h1>
        <div id="menu">
            <ul>
                <li @click="getUserSelection($event)">My Plans</li>
                <li  @click="getUserSelection($event)">My Recipes</li>
                <li  @click="getUserSelection($event)">Grocery List</li>
                <li  @click="getUserSelection($event)">Account Info</li>
            </ul>
        </div>
        <div id="details">
            <div id="myPlans" v-show="choice === 'My Plans'">
                <div id="searchBar">
                    <div id="nameSearch">
                        <label for="name">Recipe Name: </label>
                        <input type:text id="name">
                    </div>
                    <div id="timeSearch">
                        <label for="duration">Time(in mins): </label>
                        <select id="duration">
                            <option value="0">0</option>
                            <option value="15">15</option>
                            <option value="30">30</option>
                            <option value="45">45</option>
                            <option value="60">60</option>
                        </select>
                    </div>
                    <div id="description">
                        <label for="description">Description: </label>
                        <input type:text id="description">
                    </div>
                    <div id="dietary">
                        <label for="dietary">Diet Type: </label>
                        <input type:text id="dietary">
                    </div>
                </div>
            </div>
            <div id="myRecipes" v-show="choice === 'My Recipes'">
                <div id="searchBar">
                    <div id="nameSearch">
                        <label for="name" >Recipe Name: </label>
                        <input type:text id="name" v-model="filteredRecipe.name">
                    </div>
                    <div id="timeSearch">
                        <label for="duration">Time(in mins): </label>
                        <select id="duration" v-model="filteredRecipe.duration">
                            <option value="0">0</option>
                            <option value="15">15</option>
                            <option value="30">30</option>
                            <option value="45">45</option>
                            <option value="60">60</option>
                        </select>
                    </div>
                    <div id="occasion">
                        <label for="occasion">Occasion: </label>
                        <input type:text id="occasion" v-model="filteredRecipe.category">
                    </div>
                    <div id="dietary">
                        <label for="dietary">Diet Type: </label>
                        <input type:text id="dietary">
                    </div>
                </div>
                <div>
                    <Recipes :recipes="filteredRecipeList" />
                </div>
            </div>
            <div id="groceryList" v-show="choice === 'Grocery List'">
                grocery list will go here
            </div>
            <div id="accountInfo" v-if="choice === 'Account Info'">
                <p>Username: {{ $store.state.user.username}}</p> 
                <!-- <p>Password: {{ $store.state.user }}</p> -->
            </div>
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
           choice : "My Plans",
           filteredRecipe : {
                name : "",
                duration : "",
                category : "",
                dietary : ""
           },
           filteredPlan : {
                name : "",
                duration : "",
                description : "",
                dietary : ""
           },
           myRecipes : [],
           myPlans : []

        }
    },
    methods: {
        getUserSelection(e){
                this.choice = e.target.innerText;
                if(this.choice != "My Recipes"){
                    this.filteredRecipe.name = "";
                    this.filteredRecipe.category = "";
                    this.filteredRecipe.time = "";
                }
                if(this.choice != "My Plans"){
                    this.filteredPlan.name = "";
                    this.filteredPlan.length = "";
                    this.filteredPlan.description = "";
                }
        },
        getRecipes(){
            authService.myRecipes.then(response => {
                this.myRecipes = response.data;
            });
        }

    },
    computed: {
        filteredRecipeList() {
            let filterRecipe = this.myRecipes;
            if(this.filteredRecipe.name != ""){
                filterRecipe = filterRecipe.filter(recipe => 
                recipe.name
                .toLowerCase()
                .includes(this.filteredRecipe.name.toLowerCase())
                );
            }
            if(this.filteredRecipe.category != ""){
                filterRecipe = filterRecipe.filter(recipe => 
                recipe.category
                .toLowerCase()
                .includes(this.filteredRecipe.category.toLowerCase())
                )
            }
            if(this.filteredRecipe.time != 0){
                filterRecipe = filterRecipe.filter(recipe => 
                recipe.time <= this.filteredRecipe.time
                )
            }
            if(this.filteredRecipe.dietary != ""){
                filterRecipe = filterRecipe.filter(recipe => 
                recipe.dietary 
                .toLowerCase()
                .includes(this.filteredRecipe.dietary.toLowerCase())
                )
            }
            return filterRecipe;
        }
    }

};
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Merienda:wght@600&family=Varela+Round&display=swap');

#account {
    display: grid;
    grid-template-columns: 0.2fr 1.5fr .2fr;
    grid-template-areas:
        "image title ."
        "menu details ."
    ;
    grid-template-rows: 1fr 4fr;
    height: 100vh;
    background-color: #369cdb;
}

img{
    grid-area: image;
    height: 200px;
}

h1{
    grid-area: title;
    display: flex;
    justify-self: center;
    align-items: center;
    font-family: Merienda;
    margin: 0;
    font-size: 50px;
    padding-right: 15px;
}


li{
    list-style: none;
    margin: 20px 10px 0px;
    text-decoration: underline;
    display: flex;
    flex-grow: .06;
}



ul{
    display: flex;
    flex-direction: column;
}

ul :hover{
    background-color: #2892d4;
    cursor: pointer;
    color: white;
}


#menu{
    grid-area: menu;
    display: flex;
    justify-content: space-between;
}

#details{
    grid-area: details;
    background-color: white;
    display: flex;
    justify-content: flex-start;
    align-items: flex-start;
    padding: 20px 40px;
    border-top: black solid 2px;
    border-left: black solid 2px;
    border-right: black solid 2px;
    width: 75vw;
}

#searchBar{
    background-color: lightgrey;
    height: 35px;
    width: 75vw;
    display: flex;
    justify-content: space-evenly;
    align-items: center;
}

#nameSearch{
    display:inline-block;
}

#timeSearch{
    display: inline-block;
    margin-left: 20px;
}

#occasion{
    display: inline-block;
}
</style>
