<template>
    <header class="backgroundImg">
        <v-container class="col-8">
            <v-row v-if="isLogin !== false">
                <div class="col-lg-2 col-md-2 col-sm-2">
                    <router-link to="/">
                        <img
                            src="@/assets/images/main/logo.png"
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
                            src="@/assets/images/main/logo.png"
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
            <v-row class="introduceBox">
                <div class="col-6">
                    <h1 class="introduceMessage">
                        "최고의 팀"
                        <br />꾸려보고 싶은가요?
                    </h1>
                </div>

                <div class="col-6">
                    <div class="searchBar">
                        <input type="text" class="col-11" />
                        <button class="col-1 searchIcon">
                            <v-icon>mdi-magnify</v-icon>
                        </button>
                    </div>
                </div>
            </v-row>
            <div class="bgLogo">
                <img src="@/assets/images/main/logo.png" alt="로고" />
            </div>

            <v-row class="categoryMenu">
                <div
                    class="col-lg-2 col-md-4 col-sm-6 mainCategory"
                    v-for="(category, index) in categories"
                    :key="index"
                >
                    <v-spacer></v-spacer>
                    <div @click="moveByCategory(category.name)">
                        <div :id="category.value">
                            <img
                                class="width-40 height-40 centerContent "
                                :src="category.img"
                                :alt="category.name"
                            />
                            <img
                                class="width-40 height-40 centerContent "
                                :src="category.afterImg"
                                :alt="category.name"
                            />
                        </div>
                        <p>{{ category.name }}</p>
                    </div>
                    <v-spacer></v-spacer>
                </div>
            </v-row>
        </v-container>

        <div class="mal">
            <img src="../../assets/images/main/mal.png" alt="말풍선" />
        </div>
        <div class="mal2">
            <img src="../../assets/images/main/mal.png" alt="말풍선" />
        </div>
        <div class="mal3">
            <img src="../../assets/images/main/mal.png" alt="말풍선" />
        </div>
    </header>
</template>

<script>
import { mapState } from 'vuex';

export default {
    computed: {
        ...mapState(['memberInfo', 'isLogin']),
    },
    data() {
        return {
            categories: [
                {
                    img: require('@/assets/category/design.png'),
                    afterImg: require('@/assets/category/design(c).png'),
                    name: '디자인',
                    value: 'design',
                },
                {
                    img: require('@/assets/category/computer.png'),
                    afterImg: require('@/assets/category/computer(c).png'),
                    name: 'IT·프로그래밍',
                    value: 'computer',
                },
                {
                    img: require('@/assets/category/translate.png'),
                    afterImg: require('@/assets/category/translate(c).png'),
                    name: '번역·통역',
                    value: 'translate',
                },
                {
                    img: require('@/assets/category/video.png'),
                    afterImg: require('@/assets/category/video(c).png'),
                    name: '영상·사진·음향',
                    value: 'video',
                },
                {
                    img: require('@/assets/category/lucky.png'),
                    afterImg: require('@/assets/category/lucky(c).png'),
                    name: '운세·상담',
                    value: 'lucky',
                },
                {
                    img: require('@/assets/category/marketing.png'),
                    afterImg: require('@/assets/category/marketing(c).png'),
                    name: '마케팅',
                    value: 'marketing',
                },
            ],
        };
    },
    created() {
        if (localStorage.getItem('access-token') != null) this.$store.commit('setIsLogined', true);
    },
    methods: {
        onClickLogout() {
            this.$store
                .dispatch('LOGOUT')
                .then(() => {
                    if (this.$route.path !== '/') {
                        this.$router.replace('/');
                    }
                })
                .catch(() => {
                    console.log('로그아웃 문제!!!');
                });
        },
        moveByCategory(name) {
            this.$router.push({ name: 'FundingList', params: { cn: name } });
        },
        movePage(name) {
            this.$router.push({ name: name });
        },
    },
};
</script>

<style scoped>
@import '../../assets/css/header.css';
</style>
