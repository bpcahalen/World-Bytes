<template>
    <div id="account">
        <header id="naviagtion">
            <nav>
                <router-link to="/">Home</router-link>
                <router-link to="/account">Account</router-link>
                <router-link to="/recipes">Recipes</router-link>
                <router-link to="/meal-plans">Meal Plans</router-link>
                <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
            </nav>
        </header>
        <img src="../photos/world_byte.png" />
        <h1 class="title">{{ choice }}</h1>
        <div id="menu">
            <ul>
                <li @click="getUserSelection($event)">My Plans</li>
                <li @click="getUserSelection($event)">My Recipes</li>
                <li @click="getUserSelection($event)">Grocery List</li>
                <li @click="getUserSelection($event)">Account Info</li>
            </ul>
        </div>
        <div id="details">
            <div id="myPlans" v-show="choice === 'My Plans'">
                <div id="searchBar">
                    <div class="nameSearch">
                        <label for="name">Recipe Name: </label>
                        <input type:text placeholder="Search by name...">
                    </div>
                    <div class="timeSearch">
                        <label for="duration">Time(in mins): </label>
                        <select>
                            <option value="0">0</option>
                            <option value="15">15</option>
                            <option value="30">30</option>
                            <option value="45">45</option>
                            <option value="60">60</option>
                        </select>
                    </div>
                    <div class="dietary">
                        <label for="dietary">Diet Type: </label>
                        <input type:text placeholder="Search by diet...">
                    </div>
                </div>
                <div>
                    <Recipes :recipes="filteredPlanList" />
                </div>
                <div class="addLink">
                    <routerLink to="/meal-plans">Create a Meal Plan</routerLink>
                </div>
            </div>
            <div id="myRecipes" v-show="choice === 'My Recipes'">
                <div id="searchBar">
                    <div class="nameSearch">
                        <label for="name">Recipe Name: </label>
                        <input type:text v-model="filteredRecipe.title" placeholder="Search by name...">
                    </div>
                    <div class="timeSearch">
                        <label for="duration">Time(in mins): </label>
                        <select v-model="filteredRecipe.duration">
                            <option value="0">0</option>
                            <option value="15">15</option>
                            <option value="30">30</option>
                            <option value="45">45</option>
                            <option value="60">60</option>
                        </select>
                    </div>
                    <div class="dietary">
                        <label for="dietary">Diet Type: </label>
                        <input type:text v-model="filteredRecipe.dietary" placeholder="Search by diet...">
                    </div>
                </div>
                <div>
                    <Recipes :recipes="filteredRecipeList" />
                </div>
                <div class="addLink">
                    <routerLink to="/recipes">Add Recipes to Your Library</routerLink>
                </div>
            </div>
            <div id="groceryList" v-show="choice === 'Grocery List'">
                <p>Grocery List Here</p>
            </div>
            <div id="accountInfo" v-if="choice === 'Account Info'">
                <p id="user"> <strong>Username:</strong> {{ $store.state.user.username }}</p>
                <!-- <p id="password"> <strong>Password:</strong> {{ $store.state.user }}</p> -->
            </div>
        </div>
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
import Recipes from '../components/myRecipe.vue';
import authService from '../services/AuthService';

export default {
    components: {
        Recipes
    },
    data() {
        return {
            choice: "My Plans",
            filteredRecipe: {
                title: "",
                duration: "",
                dietary: ""
            },
            filteredPlan: {
                name: "",
                duration: "",
                dietary: ""
            },
            myRecipes: [
                // {
                //     recipeId: 1,
                //     image: "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/chorizo-mozarella-gnocchi-bake-cropped-9ab73a3.jpg?quality=90&resize=556,505",
                //     title: "Chicken alfredo with some parmesan encrustated cauliflower",
                //     duration: 30,
                //     category: "Lunch",
                //     dietary: "none",
                //     servings: 2
                // },
                // {
                //     recipeId: 1,
                //     image: "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/chorizo-mozarella-gnocchi-bake-cropped-9ab73a3.jpg?quality=90&resize=556,505",
                //     title: "Chicken alfredo with some parmesan encrustated cauliflower",
                //     duration: 30,
                //     category: "Lunch",
                //     dietary: "none",
                //     servings: 2
                // },
                // {
                //     recipeId: 1,
                //     image: "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/chorizo-mozarella-gnocchi-bake-cropped-9ab73a3.jpg?quality=90&resize=556,505",
                //     title: "Chicken alfredo with some parmesan encrustated cauliflower",
                //     duration: 30,
                //     category: "Lunch",
                //     dietary: "paleo",
                //     servings: 2
                // },
                // {
                //     recipeId: 1,
                //     image: "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/chorizo-mozarella-gnocchi-bake-cropped-9ab73a3.jpg?quality=90&resize=556,505",
                //     title: "Chicken alfredo with some parmesan encrustated cauliflower",
                //     duration: 30,
                //     category: "Lunch",
                //     dietary: "none",
                //     servings: 2
                // },
                // {
                //     recipeId: 1,
                //     image: "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/chorizo-mozarella-gnocchi-bake-cropped-9ab73a3.jpg?quality=90&resize=556,505",
                //     title: "Chicken alfredo with some parmesan encrustated cauliflower",
                //     duration: 30,
                //     category: "Lunch",
                //     dietary: "vegan",
                //     servings: 2
                // },
                // {
                //     recipeId: 2,
                //     image: "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/chorizo-mozarella-gnocchi-bake-cropped-9ab73a3.jpg?quality=90&resize=556,505",
                //     title: "Gnocchi",
                //     duration: 60,
                //     category: "Dinner",
                //     dietary: "none",
                //     servings: 4
                // }
            ],
            myPlans: []

        }
    },
    methods: {
        getUserSelection(e) {
            this.choice = e.target.innerText;
            if (this.choice != "My Recipes") {
                this.filteredRecipe.name = "";
                this.filteredRecipe.dietary = "";
                this.filteredRecipe.duration = "";
            }
            if (this.choice != "My Plans") {
                this.filteredPlan.name = "";
                this.filteredPlan.duration = "";
                this.filteredPlan.dietary = "";
            }
        }
    },
    created() {
        authService.getMyRecipes().then(response => {
            this.myRecipes = response.data;
        })
            // authService.getMyPlans(this.$store.state.user).then(response => {
            //     this.myPlans = response.data;
            // })
    },
    computed: {
        filteredRecipeList() {
            let filterRecipe = this.myRecipes;
            if (this.filteredRecipe.title != "") {
                filterRecipe = filterRecipe.filter(recipe =>
                    recipe.title
                        .toLowerCase()
                        .includes(this.filteredRecipe.name.toLowerCase())
                );
            }
            if (this.filteredRecipe.duration != 0) {
                filterRecipe = filterRecipe.filter(recipe =>
                    recipe.duration <= this.filteredRecipe.duration
                )
            }
            if (this.filteredRecipe.dietary != "") {
                filterRecipe = filterRecipe.filter(recipe =>
                    recipe.dietary
                        .toLowerCase()
                        .includes(this.filteredRecipe.dietary.toLowerCase())
                )
            }
            return filterRecipe;
        },
        filteredPlanList() {
            let filterPlan = this.myPlans;
            if (this.filteredPlan.name != "") {
                filterPlan = filterPlan.filter(recipe =>
                    recipe.name
                        .toLowerCase()
                        .includes(this.filteredPlan.name.toLowerCase())
                );
            }
            if (this.filteredPlan.time != 0) {
                filterPlan = filterPlan.filter(recipe =>
                    recipe.time <= this.filteredPlan.time
                )
            }
            if (this.filteredPlan.dietary != "") {
                filterPlan = filterPlan.filter(recipe =>
                    recipe.dietary
                        .toLowerCase()
                        .includes(this.filteredPlan.dietary.toLowerCase())
                )
            }
            return filterPlan;
        }
    }

};
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Merienda:wght@600&family=Varela+Round&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Varela+Round&display=swap');


#account {
    display: grid;
    grid-template-columns: 0.2fr 1.5fr .2fr;
    grid-template-areas:
        "nav nav nav"
        "image title ."
        "menu details ."
        "footer footer footer"
    ;
    grid-template-rows: .1fr 1fr 4fr .5fr;
    background-color: #369cdb;
}

header {
    grid-area: nav;
    display: flex;
    justify-content: flex-end;
    align-items: center;
    background-color: #369cdb;
    padding: 10px 20px 40px 20px;
    width: 96vw;
}

img {
    grid-area: image;
    height: 200px;
}

.title {
    grid-area: title;
    display: flex;
    justify-self: center;
    align-items: center;
    font-family: Merienda;
    margin: 0;
    font-size: 75px;
    padding-right: 15px;
}

.addLink {
    margin-top: 30px;
}

.addLink a {
    color: #369cdb;
    text-decoration: none;
    font-size: 15px;
    font-style: italic;
    padding: 8px;
    border: #369cdb solid 2px;
    font-family: Varela Round;
    border-radius: 8px;
}

.addLink :hover {
    color: black;
    background-color: #00b35c;
    border: black solid 2px;
}

li {
    list-style: none;
    margin: 20px 10px 0px;
    text-decoration: underline;
    display: flex;
    flex-grow: .06;
    font-family: Varela Round;
    font-size: 19px;
}



ul {
    display: flex;
    flex-direction: column;
}

ul :hover {
    background-color: #2892d4;
    cursor: pointer;
    color: white;
}


#menu {
    grid-area: menu;
    display: flex;
    justify-content: space-between;
}

#details {
    grid-area: details;
    background-color: white;
    display: flex;
    justify-content: flex-start;
    align-items: flex-start;
    padding: 20px 40px;
    border: black solid 2px;
    width: 75vw;
    border-radius: 4px;
}

#searchBar {
    height: 60px;
    width: 75vw;
    display: flex;
    justify-content: space-between;
    align-items: center;
    font-family: Varela Round;
    font-size: 30px;
    font-weight: bold;
}

.nameSearch {
    width: 450px;
    display: flex;
    justify-content: center;
    align-items: center;
}

.nameSearch label {
    margin: 10px;
}

input {
    width: 200px;
    border-radius: 4px;
    padding: 5px;
    font-size: 20px;;
}

select{
    width: 50px;
    height: 30px;
}

.timeSearch {
    width: 300px;
    display: flex;
    justify-content: center;
    align-items: center;
}

.timeSearch label {
    margin: 10px;
}

.dietary {
    width: 400px;
    display: flex;
    justify-content: center;
    align-items: center;
}

.dietary label {
    margin: 10px;

}

#groceryList {
    display: flex;
    justify-content: flex-start;
}

#user {
    font-size: 25px;
}

footer {
    grid-area: footer;
}
</style>
