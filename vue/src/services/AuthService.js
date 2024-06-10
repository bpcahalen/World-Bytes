import axios from 'axios';

export default {
  login(user) {
    return axios.post('/login', user);
  },

  register(user) {
    return axios.post('/register', user)
  },

  myRecipes(){
    return axios.get('/library')
  },

  myPlans(user){
    return axios.get(`/plans/${user.id}`)
  },
  
  recipes(){
    return axios.get('/recipes')
  },

  recipeDetails(user){
    return axios.get(`/recipes/${user.id}/information`)
  },

  recipeById(id){
    return axios.get(`/recipes/${id}`)
  },

  addMyRecipe(){
    return axios.post('/library')
  },

  deleteMyRecipe(){
    return axios.delete()
  }
}