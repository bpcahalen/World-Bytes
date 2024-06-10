<template>
    <div id="account" class="account-page">
      <h1>My Account</h1>
      <router-link to="/meal-plans">My Meal Plans</router-link>
      <router-link to="/recipes">My Recipes</router-link>
      <router-link to="/grocery-list">Grocery List</router-link>
      <router-view></router-view>
    </div>
  </template>
  
  <script>
  export default {
    name: "AccountView",
  };
  </script>
  
  <style scoped>
  .account-page {
    padding: 2rem;
  }
  </style>
  