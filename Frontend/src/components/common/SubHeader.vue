<template>
    <header>
        <v-container class="col-8">
            <v-row v-if="isLogin !== false">
                <div class="col-2">
                    <router-link to="/">
                        <img
                            src="@/assets/images/main/logo(Bg).png"
                            alt="로고"
                            class="width-150 height-70 centerContent"
                        />
                    </router-link>
                </div>
                <v-spacer></v-spacer>
                <li class="col-1 centerText headerMenu" @click="movePage('FundingOpen')">
                    펀딩 오픈
                </li>
                <li class="col-1 centerText headerMenu" @click="movePage('MyPage')">
                    My Page
                </li>
                <li class="col-1 centerText headerMenu" @click.prevent="onClickLogout">
                    LOGOUT
                </li>
            </v-row>
            <v-row v-else>
                <div>
                    <router-link to="/">
                        <img
                            src="@/assets/images/main/logo(Bg).png"
                            alt="로고"
                            class="width-150 height-70 centerContent"
                        />
                    </router-link>
                </div>
                <v-spacer></v-spacer>
                <li class="col-1 centerText headerMenu" @click="movePage('Join')">
                    회원가입
                </li>
                <li class="col-1 centerText headerMenu" @click="movePage('Login')">
                    로그인
                </li>
            </v-row>
        </v-container>
    </header>
</template>

<script>
import { mapState } from 'vuex';

export default {
    computed: {
        ...mapState(['memberInfo', 'isLogin']),
    },
    created() {
        if (localStorage.getItem('access-token') != null) this.$store.commit('setIsLogined', true);
    },
    methods: {
        onClickLogout() {
            this.$store
                .dispatch('LOGOUT')
                .then(() => {
                    // this.$router.push({ name: "" });

                    if (this.$route.path !== '/') this.$router.replace('/');
                })
                .catch(() => {
                    console.log('로그아웃 문제!!!');
                });
        },
        movePage(name) {
            this.$router.push({ name: name });
        },
    },
};
</script>

<style scoped>
@import '../../assets/css/header.css';
header {
    border-bottom: 1px solid black;
}
.headerMenu {
    color: #000;
}
.headerMenu:hover {
    color: #ffce55;
}
</style>
