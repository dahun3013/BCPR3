import { createWebHistory, createRouter } from "vue-router";
// import Vue from 'vue'
import Papago from './components/Papago.vue';
// import Login from './components/Login.vue';
// import VueRouter from 'vue-router'


// Vue.use(VueRouter);

const routes = [
  // {
  //   path: "/login",
  //   component: Login,
  // },
  {
    path: "/papago",
    component: Papago,
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router; 