<template>
    <div id="recipe" v-for="recipe in recipes" :key="recipe.id">
        <img :src="recipe.image" class="photo"> 
        <div id="info">
            <h3>{{ recipe.category }}  Recipe</h3>
            <h3>Time: {{ recipe.duration }} </h3>
        </div>
        <h2>{{ recipe.title }}</h2>
    </div>
</template>

<script>
export default {
    props: ['recipes']
};
</script>

<style>
#recipe {
    border: black solid 2px;
    display: inline-block;
    background-color: antiquewhite;
    margin: 20px 35px;
    padding: 5px;
    width: 350px;
    height: 350px;
}

#info{
    display: flex;
    align-items: flex-start;
    justify-content: space-around;
    font-size: small;
    color: #00b35c;
    border-top:black solid 2px;
}

.photo{
    display: flex;
    justify-content: center;
    align-items: center;
}

h2{
    display: flex;
    justify-content: center;
    font-size: 25px;
    flex-wrap: wrap;
}
</style>