<template>
  <v-app>
    <nav-header></nav-header>
    <router-view></router-view>
    <nav-footer></nav-footer>
  </v-app>
</template>

<script>

import NavHeader from "@/components/common/Header.vue";
import NavFooter from "@/components/common/Footer.vue";
export default {
  name: "App",
  components: {
   NavHeader,
   NavFooter
  },

};
</script>
