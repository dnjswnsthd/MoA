<template>
    <div class="width-1000 fundingMargin">
        <h1>펀딩목록</h1>
        <v-carousel cycle height="300" hide-delimiter-background show-arrows-on-hover>
            <v-carousel-item v-for="(slide, i) in slides" :key="i">
                <v-sheet :color="colors[i]" height="100%">
                    <v-row class="fill-height" align="center" justify="center">
                        <div class="display-3">
                            {{ slide }}
                        </div>
                    </v-row>
                </v-sheet>
            </v-carousel-item>
        </v-carousel>
        <ul class="fundingListPadding_2">
            <v-row>
                <v-col v-for="category in categories" :key="category">
                    <v-spacer></v-spacer>
                    <div class="mainCategory">
                        <router-link to="/fundinglist">
                            <div :id="category.value" @click="change(category.name)">
                                <img
                                    class="width-40 height-40 centerContent "
                                    :src="category.img"
                                    alt="디자인"
                                />
                                <img
                                    class="width-40 height-40 centerContent "
                                    :src="category.afterImg"
                                    alt="디자인"
                                />
                            </div>
                        </router-link>
                        <router-link to="/fundinglist">{{ category.name }}</router-link>
                    </div>
                    <v-spacer></v-spacer>
                </v-col>
            </v-row>
        </ul>
        <v-divider></v-divider>
        <div class="pt-2">
            <h2>당신의 취향을 저격할 프로젝트</h2>
            <v-row class="pt-5">
                <v-col v-for="project in projectList" :key="project">
                    <img
                        src="@/assets/images/funding/fox.jpg"
                        class="width-250 height-200"
                        alt="예시"
                    />
                    <br />
                    <div class="width-250">
                        <v-progress-linear
                            color="#CE93D8"
                            buffer-value="0"
                            value="80"
                            stream
                        ></v-progress-linear>
                    </div>
                    <ul class="pt-4 pl-3">
                        <v-row>
                            <li style="font-size: large; font-weight: 900;">
                                {{ project.project_name }}
                            </li>
                        </v-row>
                    </ul>
                    <ul class="pt-4">
                        <v-row>
                            <li>카테고리 : {{ project.category }}</li>
                        </v-row>
                    </ul>
                    <ul class="pt-4">
                        <v-row>
                            <li>기간 : ~ {{ project.end_date }}</li>
                        </v-row>
                    </ul>
                    <ul class="pt-4">
                        <v-row>
                            <li>담당자 : {{ project.leader }}</li>
                        </v-row>
                    </ul>
                </v-col>
                <br /><br /><br /><br /><br />
            </v-row>
        </div>
        <br /><br />
    </div>
</template>

<script>
import http from '@/util/http-common';

export default {
    name: 'FundingList',
    created() {
        this.categoryName = this.$route.params.cn;
        this.change(this.categoryName);
    },
    data() {
        return {
            categoryName: '',
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
            colors: ['indigo', 'warning', 'pink darken-2', 'red lighten-1', 'deep-purple accent-4'],
            slides: [
                '반응형 웹 디자인',
                '오늘의 운세는?',
                '골목 시장 상권 살리기',
                '디자이너를 위한 프로젝트',
                '모바일 앱 제작',
            ],
            projectList: [],

            selectOpButton: false,
            selectMyButton: false,
            selectSgButton: false,
            selectLgButton: false,
        };
    },
    methods: {
        change(name) {
            for (var i = 0; i < this.categories.length; i++) {
                if (this.categories[i].name == name)
                    this.categories[
                        i
                    ].img = require(`@/assets/category/${this.categories[i].value}(c).png`);
                else
                    this.categories[
                        i
                    ].img = require(`@/assets/category/${this.categories[i].value}.png`);
            }

            http.get(`project/fundingList/${name}`)
                .then(({ data }) => {
                    this.projectList = data.list;
                    console.log(this.projectList);
                })
                .catch(() => {
                    console.log('fail');
                });
        },
    },
};
</script>

<style></style>
