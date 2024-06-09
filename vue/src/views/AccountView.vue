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
                meal plans will go here
            </div>
            <div id="myRecipes" v-show="choice === 'My Recipes'">
                recipes will go here
            </div>
            <div id="groceryList" v-show="choice === 'Grocery List'">
                grocery list will go here
            </div>
            <div id="accountInfo" v-show="choice === 'Account Info'">
                <p>Username:</p> 
                <p>Password:</p>
            </div>
        </div>
    </div>
</template>

<script>

export default {
    data() {
        return {
           choice : "My Plans"
        }
    },
    methods: {
        getUserSelection(e){
                this.choice = e.target.innerText
        }
    }

}


</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Merienda:wght@600&family=Varela+Round&display=swap');

#account {
    display: grid;
    grid-template-columns: .5fr 1.5fr;
    grid-template-areas:
        "image title"
        "menu details"
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
    padding-left: 40px;
    padding-top: 20px;
    border-top: black solid 2px;
    border-left: black solid 2px;
}
</style>