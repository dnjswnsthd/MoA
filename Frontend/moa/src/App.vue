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
  font-family:'Wemakeprice-Bold';
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

@font-face {
    font-family: 'Wemakeprice-Bold';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-10-21@1.0/Wemakeprice-Bold.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
        @font-face {
    font-family: 'TmoneyRoundWindExtraBold';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-07@1.0/TmoneyRoundWindExtraBold.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
</style>
