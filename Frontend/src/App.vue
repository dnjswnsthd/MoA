<template>
    <v-app class="wrap">
        <router-view name="header"></router-view>
        <!-- 헤더가 들어갈 라우터뷰 -->
        <router-view class="wrap_2"></router-view
        ><!-- 가운데 내용이 들어갈 라우터뷰-->
        <nav-footer></nav-footer>
        <!--푸터는 모든화면에서 고정이라 App.vue에서 컴포넌트로 들고와 사용-->
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
@import './assets/css/category.css';
</style>
