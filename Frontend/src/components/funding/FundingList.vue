<template>
    <div class="width-1000 fundingMargin">
        <h1>펀딩목록</h1>
        <v-carousel cycle height="300" hide-delimiter-background show-arrows-on-hover>
            <v-carousel-item v-for="(slide, i) in slides" :key="i">
                <v-sheet :color="colors[i]" height="100%">
                    <v-row class="fill-height" align="center" justify="center">
                        <div class="display-3">
                            <img
                                src="@/assets/images/sample.jpg"
                                alt="샘플"
                                class="widht-950 height-300"
                            />
                        </div>
                    </v-row>
                </v-sheet>
            </v-carousel-item>
        </v-carousel>
        <ul class="fundingListPadding_2">
            <v-row>
                <v-col v-for="category in categories" :key="category">
                    <v-spacer></v-spacer>
                    <div class="mainCategory" @click="change(category.name)">
                        <router-link to="/fundinglist">
                            <div :id="category.value">
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
            <v-row class="pt-5 pl-9">
                <v-col class="width-300" v-for="(project, index) in projectList" :key="index">
                    <v-row>
                        <img
                            src="@/assets/images/funding/fox.jpg"
                            class="width-300 height-200"
                            alt="예시"
                            @click="goDetail(project.project_num)"
                        />
                    </v-row>
                    <!-- <br /> -->
                    <v-row class="width-300 pt-4">
                        <v-progress-linear
                            color="#CE93D8"
                            :value="dategap[index]"
                            stream
                        ></v-progress-linear>
                    </v-row>
                    <v-row class="pt-2">
                        <p
                            style="font-size: large; font-weight: 900;"
                            @click="goDetail(project.project_num)"
                        >
                            {{ project.project_name }}
                        </p>
                    </v-row>
                    <v-row class="col-12 width-300 mt-0 pt-0">
                        <v-col class="pl-3 col-9">
                            <v-row>
                                <p style="margin: 0px">카테고리 : {{ project.category }}</p>
                            </v-row>
                            <v-row>
                                <p style="margin: 0px">모집기간 : ~ {{ project.deadline }}</p>
                            </v-row>
                            <v-row>
                                <p style="margin: 0px">담당자 : {{ project.leader }}</p>
                            </v-row>
                        </v-col>
                        <v-col class="col-3 pl-8 pb-5" style="margin: auto;">
                            <img
                                v-if="!project.love"
                                :src="require('@/assets/category/heart.png')"
                                alt="하트"
                                class="width-30 height-30"
                            />
                            <img
                                v-else
                                :src="require('@/assets/category/heart(c).png')"
                                alt="하트"
                                class="width-30 height-30"
                            />
                        </v-col>
                    </v-row>
                    <br />
                </v-col>
                <br /><br /><br /><br /><br />
            </v-row>
        </div>
        <br /><br />
    </div>
</template>

<script>
import http from '@/util/http-common';
import { mapState } from 'vuex';

export default {
    name: 'FundingList',
    computed: {
        ...mapState(['memberInfo', 'isLogin']),
    },
    created() {
        this.categoryName = this.$route.params.cn;

        this.getInterestingList();
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
            interestingList: [],
            projectList: [],
            dategap: [],
            heart: {
                img: require('@/assets/category/heart.png'),
                afterImg: require('@/assets/category/heart(c).png'),
                name: '관심',
                value: 'heart',
            },
        };
    },
    methods: {
        goDetail(project_num) {
            console.log('index : ' + project_num);
            // this.project_num = this.fundingDatas[index].project_num;
            // console.log(this.project_num);
            this.$router.push({ name: 'FundingDetail', params: { pn: project_num } });
        },
        getInterestingList() {
            console.log(`getIL`);
            http.get(`project/interesting/${this.memberInfo.id}`)
                .then(({ data }) => {
                    this.interestingList = data.interestingProjectInfo;
                })
                .catch(() => {
                    alert(`관심 목록 가져오기 실패`);
                });
        },
        change(name) {
            this.categoryName = name;

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
            let today = new Date();
            let year = today.getFullYear();
            let month = today.getMonth() + 1;
            let date = today.getDate();
            let current = year + '-' + month + '-' + date;

            var url = null;
            if (name == undefined) url = 'project/fundingList';
            else url = `project/fundingList/${name}`;

            console.log(url);

            http.get(url)
                .then(({ data }) => {
                    this.projectList = data.list;

                    for (var i = 0; i < this.projectList.length; i++) {
                        this.projectList[i].love = false;
                        for (var j = 0; j < this.interestingList.length; j++) {
                            if (
                                this.interestingList[j].project_num ==
                                this.projectList[i].project_num
                            )
                                this.projectList[i].love = true;
                        }

                        let startdate = this.projectList[i].start_date;
                        let deadline = this.projectList[i].deadline;

                        let ttoday = new Date(current);
                        let start = new Date(startdate);
                        let end = new Date(deadline);

                        // let gap = (today.getTime(this.fundingDatas[i].end_date) - today.getTime(this.fundingDatas[i].start_date)) / (1000 * 60 * 60 * 24);
                        let gap = Math.floor(
                            (end.getTime() - start.getTime()) / (1000 * 60 * 60 * 24)
                        );
                        let gap2 = Math.floor(
                            (end.getTime() - ttoday.getTime() + 32400000) / (1000 * 60 * 60 * 24)
                        );

                        let result = 100 - Math.ceil((gap2 / gap) * 100);

                        // let res = Math.ceil(gap );
                        this.dategap[i] = result;
                        // console.log(res);
                    }
                })
                .catch(() => {
                    console.log('fail');
                });
        },
    },
};
</script>

<style></style>
