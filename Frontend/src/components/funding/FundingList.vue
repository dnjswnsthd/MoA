<template>
    <v-container class="col-lg-8 col-md-12 col-12 centerContent">
        <p class="pageTitle">펀딩목록</p>
        <!-- <v-carousel cycle height="300" hide-delimiter-background show-arrows-on-hover> -->
        <v-carousel
            interval="2000"
            hide-delimiter-background
            show-arrows-on-hover
            cycle
            progress-color="purple"
            hide-delimiters
            prev-icon="mdi-arrow-left"
            next-icon="mdi-arrow-right"
        >
            <v-carousel-item v-for="item in items" :key="item" :src="item"></v-carousel-item>
        </v-carousel>
        <!-- </v-carousel> -->

        <v-row class="mt-1">
            <div class="col-lg-2 col-md-4 col-4" v-for="category in categories" :key="category">
                <v-spacer></v-spacer>
                <div class="category" @click="change(category.name)">
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

        <v-divider></v-divider>
        <div class="mt-2 mb-15">
            <h2 style="display:block;">당신의 취향을 저격할 프로젝트</h2>
            <v-row class="mt-5">
                <div class="col-6 col-lg-4" v-for="(project, index) in projectList" :key="index">
                    <img
                        :src="categoriesImg[project.category]"
                        alt="예시"
                        class="col-6 centerContent"
                        @click="goDetail(project.project_num)"
                    />

                    <!-- <br /> -->
                    <div class="col-12">
                        <v-progress-linear
                            color="#CE93D8"
                            :value="dategap[index]"
                            stream
                        ></v-progress-linear>
                    </div>
                    <div class="col-12">
                        <p class="projectTitle" @click="goDetail(project.project_num)">
                            {{ project.project_name }}
                        </p>
                    </div>
                    <div class="col-12 mt-0 pt-0">
                        <div>
                            <p>카테고리 : {{ project.category }}</p>

                            <p>모집기간 : ~ {{ project.deadline }}</p>

                            <p>담당자 : {{ project.leader }}</p>
                        </div>
                        <v-col class="col-12">
                            <img
                                v-if="!project.love"
                                :src="require('@/assets/images/category/heart.png')"
                                alt="하트"
                                class="width-30 height-30 centerContent"
                            />
                            <img
                                v-else
                                :src="require('@/assets/images/category/heart(c).png')"
                                alt="하트"
                                class="width-30 height-30 centerContent"
                            />
                        </v-col>
                    </div>
                </div>
            </v-row>
        </div>
    </v-container>
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
            categoriesImg: {
                디자인: require('@/assets/images/category/design(c).png'),
                'IT·프로그래밍': require('@/assets/images/category/computer(c).png'),
                '번역·통역': require('@/assets/images/category/translate(c).png'),
                '영상·사진·음향': require('@/assets/images/category/video(c).png'),
                '운세·상담': require('@/assets/images/category/lucky(c).png'),
                마케팅: require('@/assets/images/category/marketing(c).png'),
            },
            items: [
                require('@/assets/images/AD/ad3.jpg'),
                require('@/assets/images/AD/ad4.jpg'),
                require('@/assets/images/AD/ad5.jpg'),
                require('@/assets/images/AD/ad2.jpg'),
                require('@/assets/images/AD/ad1.jpg'),
                require('@/assets/images/AD/ad6.jpg'),
            ],
            interestingList: [],
            projectList: [],
            dategap: [],
            heart: {
                img: require('@/assets/images/category/heart.png'),
                afterImg: require('@/assets/images/category/heart(c).png'),
                name: '관심',
                value: 'heart',
            },
        };
    },
    methods: {
        // 상세페이지로 이동.
        goDetail(project_num) {
            console.log('index : ' + project_num);
            // this.project_num = this.fundingDatas[index].project_num;
            // console.log(this.project_num);
            this.$router.push({ name: 'FundingDetail', params: { pn: project_num } });
        },
        // 관심목록을 가져옴.
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
        // 카테고리 클릭 시 변경.
        change(name) {
            this.categoryName = name;

            for (var i = 0; i < this.categories.length; i++) {
                if (this.categories[i].name == name)
                    this.categories[
                        i
                    ].img = require(`@/assets/images/category/${this.categories[i].value}(c).png`);
                else
                    this.categories[
                        i
                    ].img = require(`@/assets/images/category/${this.categories[i].value}.png`);
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

<style scoped>
@import '../../assets/css/fundinglist.css';
.row + .row {
    margin-top: 0px;
}
</style>
