<template>
    <header class="backgroundImg">
        <v-container class="col-12 col-lg-8">
            <v-row v-if="isLogin !== false">
                <div class="col-lg-2 col-md-2 col-2 logo">
                    <img
                        src="@/assets/images/main/logo.png"
                        alt="로고"
                        class="width-150 height-70 centerContent"
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
                <div class="col-lg-2 col-md-2 col-sm-2 col-2">
                    <router-link to="/">
                        <img
                            src="@/assets/images/main/logo.png"
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
            <v-row class="introduceBox mt-15">
                <div class="col-12 col-sm-6">
                    <h1 class="introduceMessage">
                        "최고의 팀"
                        <br />꾸려보고 싶은가요?
                    </h1>
                </div>

                <div class="col-12 col-sm-6">
                    <div class="searchBar">
                        <input type="text" class="col-11" v-model="topic" />
                        <button class="col-1 searchIcon" @click="moveSearchMain">
                            <v-icon>mdi-magnify</v-icon>
                        </button>
                    </div>
                </div>
            </v-row>
            <div class="bgLogo">
                <img src="@/assets/images/main/logo.png" alt="로고" />
            </div>

            <v-row class="categoryMenu" id="focusing">
                <div
                    class="col-lg-2 col-md-4 col-4 mainCategory"
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
                    img: require('@/assets/images/category/design.png'),
                    afterImg: require('@/assets/images/category/design(c).png'),
                    name: '디자인',
                    value: 'design',
                },
                {
                    img: require('@/assets/images/category/computer.png'),
                    afterImg: require('@/assets/images/category/computer(c).png'),
                    name: 'IT·프로그래밍',
                    value: 'computer',
                },
                {
                    img: require('@/assets/images/category/translate.png'),
                    afterImg: require('@/assets/images/category/translate(c).png'),
                    name: '번역·통역',
                    value: 'translate',
                },
                {
                    img: require('@/assets/images/category/video.png'),
                    afterImg: require('@/assets/images/category/video(c).png'),
                    name: '영상·사진·음향',
                    value: 'video',
                },
                {
                    img: require('@/assets/images/category/lucky.png'),
                    afterImg: require('@/assets/images/category/lucky(c).png'),
                    name: '운세·상담',
                    value: 'lucky',
                },
                {
                    img: require('@/assets/images/category/marketing.png'),
                    afterImg: require('@/assets/images/category/marketing(c).png'),
                    name: '마케팅',
                    value: 'marketing',
                },
            ],
            openFlag: false,
            topic: '',
            project: {},
        };
    },
    created() {
        if (localStorage.getItem('access-token') != null) this.$store.commit('setIsLogined', true);
    },
    methods: {
        // 로그아웃
        onClickLogout() {
            this.$store
                .dispatch('LOGOUT')
                .then(() => {
                    if (this.$route.path !== '/') {
                        this.openFlag = false;
                        this.$router.replace('/');
                    }
                })
                .catch(() => {
                    console.log('로그아웃 문제!!!');
                });
        },
        // category클릭 시 해당 카테고리의 펀딩리스트로 이동.
        moveByCategory(name) {
            this.$router.push({ name: 'FundingList', params: { cn: name } });
        },
        // 페이지 이동.
        movePage(name) {
            this.$router.push({ name: name });
        },
        // 메뉴버튼 클릭시 메뉴보이게함.
        openMenu() {
            this.openFlag = !this.openFlag;
        },
        // 검색을 하면 검색어를 가지고 검색결과가 나오는 페이지로 이동.
        moveSearchMain() {
            this.$router.push({ name: 'SearchMain', params: { topic: this.topic } });
        },
    },
};
</script>

<style scoped>
@import '../../assets/css/header.css';
</style>
