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

  getRecipeById(id){
    return axios.get(`/recipes/${id}/information`)
  },

  getMyRecipes(){
    return axios.get('/library')
  },

  getMyPlans(user){
    return axios.get(`/plans/${user.id}`)
  },

  addToMyRecipe(recipe, user){
    return axios.post('/recipes/library', recipe, user)
  },

  deleteMyRecipe(){
    return axios.delete()
  }
}