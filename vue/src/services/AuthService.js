import axios from 'axios';

export default {
  login(user) {
    return axios.post('/login', user);
  },

  register(user) {
    return axios.post('/register', user)
  },
  
  getRecipes(){
    return axios.get('/recipes')
  },

  getRecipeDetails(user){
    return axios.get(`/recipes/${user.id}/information`)
  },

  getRecipeById(id){
    return axios.get(`/recipes/${id}`)
  },

  getMyRecipes(){
    return axios.get('/library')
  },

  getMyPlans(user){
    return axios.get(`/plans/${user.id}`)
  },

  addToMyRecipe(recipe){
    return axios.post('/library', recipe.id)
  },

  deleteMyRecipe(){
    return axios.delete()
  }
}