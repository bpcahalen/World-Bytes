import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:9000"
});

export default {
    
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

  addMyRecipe(){
    return axios.post('/library')
  },

  deleteMyRecipe(){
    return axios.delete()
  }
}