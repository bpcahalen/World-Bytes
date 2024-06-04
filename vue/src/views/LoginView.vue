<template>
  <div id="login">
    <form v-on:submit.prevent="$store.state.logo">
      <h1 >Please Sign In <img src="../World_Byte.jpg"/></h1>
      <div role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div role="alert" v-if="this.$route.query.registration">
        Thank you for registering, please sign in.
      </div>
      <div class="form-input-group">
        <label for="username">Username:</label>
        <input type="text" id="username" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group">
        <label for="password">Password:</label>
        <input type="password" id="password" v-model="user.password" required />
      </div>
      <div id="submit">
      <button type="submit">Sign in</button>
      </div>
      <p>
      <router-link v-bind:to="{ name: 'register' }">Need an account? Sign up.</router-link></p>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Merienda:wght@600&family=Varela+Round&display=swap');

#login{
  height: 100vh;
  width: 100vw;
 display:flex;
 justify-content: center;
 align-items: center;
 background-color: #369cdb;
 font-family: 'Varela Round', sans-serif;
}

form{
  background-color:  white;
  display: flex;
  justify-content: flex-start;
  align-items: flex-start;
  flex-direction: column;
  padding: 2rem;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  max-width: 400px;
  width: 100%;
  max-height: 398.4px;
  height: 100%
}

h1{
  font-family: Merienda;
  color: #00b35c;
}

img{
  width: 60px;
  height: 60px;
  margin-left: 30px;
}

label{
  display: block;
  margin-bottom: 0.5rem;
}

input{
  border-radius: 4px;
  border-width: .5px;
  width: 100%;
  border: 1px solid #ccc;
  padding: 8px;
  box-sizing: border-box;
  display: inline-block;
}

input:hover{
  background-color: #fcf9f9;
}

#submit{
  display: flex;
  justify-content: center;
}

button{
  border-radius: 4px;
  border: none;
  background-color: #00b35c; 
  color: white;
  padding-top: 9px;
  padding-bottom: 9px;
  padding-left: 15px;
  padding-right: 15px;
  margin-top: 4px;
  cursor: pointer;
}

button:hover{
  background-color: #007f4b;
}
.form-input-group {
  margin-bottom: 1rem;
  width: 100%;
}

p{
  padding-top: 4px;
}

a, :visited{
  color: #00b35c;
  text-decoration: none;
}

a:hover{
  text-decoration: underline;
}
</style>