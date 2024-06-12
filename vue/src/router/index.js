import { createRouter as createRouter, createWebHistory } from 'vue-router';
import { useStore } from 'vuex';

// Import components
import HomeView from '../views/HomeView.vue';
import LoginView from '../views/LoginView.vue';
import LogoutView from '../views/LogoutView.vue';
import RegisterView from '../views/RegisterView.vue';
import AccountView from '../views/AccountView.vue';
import RecipeView from '../views/RecipeView.vue';
<<<<<<< HEAD
import RecipeDetailsView from '../views/RecipeDetailsView.vue';
=======
import MealPlanView from '../views/MealPlanView.vue';
>>>>>>> f7e853a436f802f920e8b95e8b013552c7c020d0

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login.
 * If they have (or don't need to) they're allowed to go about their way.
 */
const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/login",
    name: "login",
    component: LoginView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/logout",
    name: "logout",
    component: LogoutView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/register",
    name: "register",
    component: RegisterView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: '/account',
    name: 'account',
    component: AccountView,
    meta: {
      requiresAuth: true
    }
  },
  {
<<<<<<< HEAD
    path: '/recipes',
    name: 'recipes',
    component: RecipeView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: '/recipes/:id',
    name: 'RecipeDetails',
    component: RecipeDetailsView,
    meta: {
      requiresAuth: false
    }
=======
  path: '/recipes',
  name: 'recipes',
  component: RecipeView,
  meta: {
    requiresAuth: true
  }
  },
  {
  path: '/meal-plans',
  name: 'mealPlans',
  component: MealPlanView,
  meta: {
    requiresAuth: true
  }
>>>>>>> f7e853a436f802f920e8b95e8b013552c7c020d0
  }
];

// Create the router
const router = createRouter({
  history: createWebHistory(),
  routes: routes
});

router.beforeEach((to) => {
  // Get the Vuex store
  const store = useStore();

  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    return { name: "login" };
  }
  // Otherwise, do nothing and they'll go to their next destination
});

export default router;
