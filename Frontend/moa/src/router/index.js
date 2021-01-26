import Vue from "vue";
import VueRouter from "vue-router";


import Main from "@/views/Main.vue";
// import Sub from "@/views/Sub.vue";
Vue.use(VueRouter);

export default new VueRouter({
  mode: "history",
  routes: [
    {
      path: "/",
      name: "Main",
      component: Main
    },

    
  ]
});
