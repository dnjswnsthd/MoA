<template>
    <v-app class="wrap">
        <router-view name="header"></router-view>
        <router-view class="wrap_2"></router-view>
        <nav-footer></nav-footer>
    </v-app>
</template>

<script>
import NavFooter from '@/components/common/Footer.vue';

export default {
    name: 'App',
    components: {
        NavFooter,
    },
};
</script>
<style>
@import './assets/css/reset.css';
@import './assets/css/member.css';
@import './assets/css/funding.css';
@import './assets/css/fundingdetail.css';

.wrap {
    min-height: 100%;
    position: relative;
    padding-bottom: 0px;
}
.wrap_2 {
    padding-bottom: 70px;
}

* {
    box-sizing: border-box;
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
}
</style>
