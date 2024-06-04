<template>
  <div id="login">
    <Spline/>
    <form v-on:submit.prevent="login">
      <h1 >Please Sign In</h1>
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
// import Spline from '@splinetool/react-spline';

export default {
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false,
      Spline : 'https://prod.spline.design/brJtcyoxW8TRCvoW/scene.splinecode'
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
#login{
  height: 100vh;
  width: 100vw;
 display:flex;
 justify-content: center;
 align-items: center;
}

form{
  background-color:  #00b35c;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  padding: 175px;
  border: black dotted 2px;
}

h1{
  display: flex;
  justify-content: center;
}

input{
  border-radius: 25px;
  border-width: 1px;
}

#submit{
  display: flex;
  justify-content: center;
}

button{
  border-radius: 25px;
  border-width: 1px;
}
.form-input-group {
  margin-bottom: 1rem;
}
label {
  margin-right: 0.5rem;
}
</style>