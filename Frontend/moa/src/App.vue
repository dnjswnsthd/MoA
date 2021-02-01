<template>
  <v-app>
    <router-view name="header"></router-view>
    <router-view></router-view>
    <nav-footer></nav-footer>
  </v-app>
</template>

<script>
import NavFooter from "@/components/common/Footer.vue";


export default {
  name: 'App',
  components: {
  NavFooter

  },
};
</script>
<style>
@import './assets/css/reset.css';
@import './assets/css/member.css';
@import './assets/css/funding.css';
</style>
