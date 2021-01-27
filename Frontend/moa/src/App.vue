<template>
  <v-app>
    <router-view name="header"></router-view>
    <router-view></router-view>
    <nav-footer></nav-footer>
  </v-app>
</template>

<script>
<<<<<<< HEAD

import NavFooter from "@/components/common/Footer.vue";

=======
import NavHeader from '@/components/common/Header.vue';
import NavFooter from '@/components/common/Footer.vue';
// import NavHeader from "@/components/common/MainHeader.vue";
>>>>>>> 0cc07a62ac65066ec97e3aac2e10559da0479efb

export default {
  name: 'App',
  components: {
<<<<<<< HEAD
   NavFooter
=======
    NavHeader,
    NavFooter,
>>>>>>> 0cc07a62ac65066ec97e3aac2e10559da0479efb
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
