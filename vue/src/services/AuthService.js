import axios from 'axios';

export default {
  login(user) {
    return axios.post('/login', user);
  },

  register(user) {
    return axios.post('/register', user);
  },

  getMealPlans() {
    return axios.get('/meal-plans');
  },

  getRecipes() {
    return axios.get('/recipes');
  },

  getRecipeDetails(recipeId) {
    return axios.get(`/recipes/${recipeId}`);
  },

  getGroceryList() {
    return axios.get('/grocery-list');
  },

  createMealPlan(mealPlan) {
    return axios.post('/meal-plans', mealPlan);
  },

  updateMealPlan(mealPlanId, mealPlan) {
    return axios.put(`/meal-plans/${mealPlanId}`, mealPlan);
  },

  deleteMealPlan(mealPlanId) {
    return axios.delete(`/meal-plans/${mealPlanId}`);
  },

  addRecipe(recipe) {
    return axios.post('/recipes', recipe);
  },

  updateRecipe(recipeId, recipe) {
    return axios.put(`/recipes/${recipeId}`, recipe);
  },

  deleteRecipe(recipeId) {
    return axios.delete(`/recipes/${recipeId}`);
  },

  updateGroceryList(groceryList) {
    return axios.put('/grocery-list', groceryList);
  }
};
