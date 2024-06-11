<template>
    <div id="recipe" v-for="recipe in recipes" :key="recipe.id">
        <div class="picture">
            <img :src="recipe.image" class="photo">
        </div>
        <div id="info">
            <h3>Servings: {{ recipe.servings }}</h3>
            <h3>Duration: {{ recipe.duration }} </h3>
        </div>
        <h2>{{ recipe.title }}</h2>
        <div class="recipeOptions ">
            <div class="addBtn" id="addButton" @click="addToMyLib()" @mouseover="makeBounce($event)" @mouseout="killBounce($event)">
                <fa icon="utensils" class="addRecipe"></fa>
            </div>
        </div>
    </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
    props: ['recipes'],
    methods : {
        addToMyLib(){
            authService.addToMyRecipe(this.recipe).then(response => {
                if (response.status == 200) {
                    this.makeBounce();
                }
            })
        },
        makeBounce(event, target){
            if(event.target.id === "addButton"){
                event.target.classList.add("fa-bounce");
            } 
            else {
                event.target.addButton.classList.add("fa-bounce")
            }
        },
        killBounce(event){
            if(event.target.id === "addButton"){
                event.target.classList.remove("fa-bounce");
            }
        }
    }
};
</script>

<style>
#info {
    display: flex;
    align-items: flex-start;
    justify-content: space-around;
    font-size: 25px;
    color: grey;
}

.picture {
    display: flex;
    justify-content: center;
    align-items: center;
}

.photo {
    width: 70%;
    height: auto;
    border-radius: 8px;
    margin-bottom: 10px;
    ;
}

h2 {
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 35px;
    margin: 0;
}

#recipe {
    display: inline-block;
    background: white;
    border: 1px solid #ccc;
    border-radius: 8px;
    padding: 10px;
    margin: 10px;
    flex: 1 30%;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    height: 80%;
}

.recipeOptions {
    display: flex;
    justify-content: flex-end;
    align-items: center;
}

.recipeOptions :hover {
    cursor: pointer;
    background-color: #00b35c;
    animation: pulse 1s vertical infinite;
    color: white;
}

.addBtn {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 40px;
    height: 40px;
    background-color: #369cdb;
}

.addRecipe{
    height: 20px;
    width: 20px;
}

/* .fa-bounce{
    animation-iteration-count: 1;
} */

@keyframes button {
    from {
        bottom: 0px;
    }

    to {
        bottom: 5px;
    }
}</style>