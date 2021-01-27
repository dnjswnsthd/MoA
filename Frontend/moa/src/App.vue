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
