<template>
    <header>
        <v-container class="col-12 col-lg-8">
            <v-row v-if="isLogin !== false">
                <div class="col-lg-2 col-md-2 col-2 logo">
                    <img
                        src="@/assets/images/main/logo(Bg).png"
                        alt="로고"
                        class="width-150 height-70"
                        @click="movePage('Main')"
                        style="cursor:pointer"
                    />
                </div>
                <v-spacer></v-spacer>
                <li class="col-4 centerText headerMenu menuBtn" @click="openMenu">메뉴</li>
                <li
                    class="col-12 col-md-2 centerText headerMenu mainCloseMenu"
                    v-if="!this.openFlag"
                    @click="movePage('FundingOpen')"
                >
                    펀딩 오픈
                </li>
                <li
                    class="col-12 col-md-2 centerText headerMenu mainOpenMenu"
                    v-else
                    @click="movePage('FundingOpen')"
                >
                    펀딩 오픈
                </li>
                <li
                    class="col-12 col-md-2 centerText headerMenu mainCloseMenu"
                    v-if="!this.openFlag"
                    @click="movePage('MyPage')"
                >
                    My Page
                </li>
                <li
                    class="col-12 col-md-2 centerText headerMenu mainOpenMenu"
                    v-else
                    @click="movePage('MyPage')"
                >
                    My Page
                </li>
                <li
                    class="col-12 col-md-2 centerText headerMenu mainCloseMenu"
                    v-if="!this.openFlag"
                    @click.prevent="onClickLogout"
                >
                    LOGOUT
                </li>
                <li
                    class="col-12 col-md-2 centerText headerMenu mainOpenMenu"
                    v-else
                    @click.prevent="onClickLogout"
                >
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
                <li class="col-4 centerText headerMenu menuBtn" @click="openMenu">메뉴</li>
                <li
                    class="col-12 col-md-2 centerText headerMenu mainCloseMenu"
                    v-if="!this.openFlag"
                    @click="movePage('Join')"
                >
                    회원가입
                </li>
                <li
                    class="col-12 col-md-2 centerText headerMenu mainOpenMenu"
                    v-else
                    @click="movePage('Join')"
                >
                    회원가입
                </li>
                <li
                    class="col-12 col-md-2 centerText headerMenu mainCloseMenu"
                    v-if="!this.openFlag"
                    @click="movePage('Login')"
                >
                    로그인
                </li>
                <li
                    class="col-12 col-md-2 centerText headerMenu mainOpenMenu"
                    v-else
                    @click="movePage('Login')"
                >
                    로그인
                </li>
            </v-row>
        </v-container>
    </header>
</template>

<script>
import { mapState } from 'vuex';

export default {
    data() {
        return {
            openFlag: false,
        };
    },
    computed: {
        ...mapState(['memberInfo', 'isLogin']),
    },
    created() {
        if (localStorage.getItem('access-token') != null) this.$store.commit('setIsLogined', true);
    },
    methods: {
        // 로그아웃 클릭 시
        onClickLogout() {
            this.$store
                .dispatch('LOGOUT') // store의 LOGOUT을 동작.
                .then(() => {
                    // this.$router.push({ name: "" });

                    if (this.$route.path !== '/') {
                        this.openFlag = false;
                        this.$router.replace('/');
                    }
                })
                .catch(() => {
                    console.log('로그아웃 문제!!!');
                });
        },
        // 페이지이동. 컴포넌트의 이름을 가지고,
        movePage(name) {
            this.$router.push({ name: name });
        },
        // 모바일에서 menu버튼을클릭 시 메뉴가 나타남.
        openMenu() {
            this.openFlag = !this.openFlag;
            console.log(this.openFlag);
        },
    },
};
</script>

<style scoped>
@import '../../assets/css/header.css';
header {
    padding-bottom: 12px;
    border-bottom: 1px solid black;
}
.headerMenu {
    color: #000;
}
.headerMenu:hover {
    color: #ffce55;
}
</style>
