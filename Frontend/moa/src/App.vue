<template>
  <v-app>
    <nav-header></nav-header>
    <router-view></router-view>
    <nav-footer></nav-footer>
  </v-app>
</template>

<script>
import NavHeader from '@/components/common/Header.vue';
import NavFooter from '@/components/common/Footer.vue';
// import NavHeader from "@/components/common/MainHeader.vue";

export default {
  name: 'App',
  components: {
    NavHeader,
    NavFooter,
  },
};
</script>
<style>
* {
  margin: 0;
  padding: 0;
}
img {
  display: block;
}
ul,
li {
  list-style: none;
}
a {
  text-decoration: none;
}
</style>
