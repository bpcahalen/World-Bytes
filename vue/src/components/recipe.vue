<template>
    <div id="recipe" v-for="recipe in recipes" :key="recipe.id">
        <img src="../photos/world_byte.png">
        <div id="info">
            <h3>{{ recipe.category }}</h3>
            <h3>Time: {{ recipe.time }} </h3>
        </div>
        <h2>{{ recipe.name }}</h2>
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
}

#info{
    display: flex;
    align-items: flex-start;
    justify-content: space-around;
    font-size: small;
    color: #00b35c;
    border-top:black solid 2px;
}

h2{
    display: flex;
    justify-content: center;
}
</style>