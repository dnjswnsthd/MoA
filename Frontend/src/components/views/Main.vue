<template>
    <v-container class="col-8">
        <div class="mt-10">
            <h2 class="centerText mainTitle col-lg-2 col-md-12 col-sm-12">모집 중인 팀 펀딩</h2>
            <div class="mt-10">
                <v-row class="centerContent col-12">
                    <v-col
                        v-for="(funding, index) in fundingDatas"
                        :key="index"
                        class="funding col-lg-4 col-md-6 col-sm-6 my-5 px-2"
                    >
                        <div @click="goDetail(funding.project_num)">
                            <img
                                src="@/assets/images/funding/fox.jpg"
                                class="fullWidth fullHeight"
                                alt="예시"
                            />
                            <div class="mt-4">
                                <v-progress-linear
                                    color="#CE93D8"
                                    :value="dategap[index]"
                                    stream
                                ></v-progress-linear>
                            </div>
                            <ul>
                                <li class="shorthand projectName">
                                    프로젝트 이름 : {{ funding.project_name }}
                                </li>

                                <li>카테고리 : {{ funding.category }}</li>
                                <li>
                                    <p class="pdate">신청마감 : {{ funding.deadline }}</p>

                                    <p class="pdate">플젝마감 : {{ funding.end_date }}</p>
                                </li>
                                <li>팀장 : {{ funding.leader }}</li>
                            </ul>
                            <img
                                v-if="!funding.love"
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
                        </div>
                    </v-col>
                </v-row>
            </div>
        </div>
        <div class="mt-10">
            <h2 class="centerText mainTitle col-lg-2 col-md-12 col-sm-12">멘토 랭킹</h2>
            <div class="mt-10">
                <v-row class="centerContent col-12">
                    <!-- <v-spacer></v-spacer> -->
                    <div
                        class="col-lg-4 col-md-6 col-sm-6 my-5 px-2"
                        v-for="(rankName, index) in rankings"
                        :key="index"
                    >
                        <div>
                            <p class="centerText font-20">{{ rankName }}</p>
                        </div>

                        <v-row
                            class="mx-2 rankingBox mt-10"
                            v-for="(rankData, index) in rankDatas[index]"
                            :key="index"
                        >
                            <v-row>
                                <p class="centerText shorthand col-lg-2 col-md-1 col-sm-1">
                                    {{ index + 1 }}위
                                </p>
                                <p class="centerText shorthand col-lg-7 col-md-8 col-sm-8 ">
                                    {{ rankData.id }}
                                </p>
                                <p class="rightText shorthand col-lg-3 col-md-2 col-sm-2">
                                    {{ rankData.score }}
                                </p>
                            </v-row>
                        </v-row>
                    </div>
                </v-row>
            </div>
        </div>
    </v-container>
</template>

<script>
import http from '@/util/http-common';
import { mapState } from 'vuex';

export default {
    computed: {
        ...mapState(['memberInfo', 'isLogin']),
        getMemberInfo() {
            return this.$store.getters.getMemberInfo;
        },
    },
    watch: {
        getMemberInfo() {
            if (this.memberInfo) this.getInterestingList();
            else this.interestingList = [];

            setTimeout(this.getProjectList, 200);
        },
    },
    created() {
        http.put('project/projectmanage', {
            today: this.today,
        })
            .then((response) => {
                if (response.data.message == 'success') {
                    console.log('환영');
                } else {
                    alert('작업실패!');
                }
            })
            .catch(() => {
                alert('에러발생!');
            });
    },
    mounted() {
        http.get('member/rank')
            .then(({ data }) => {
                this.rankDatas = data.list;
            })
            .catch(() => {
                alert('랭킹 정보 가져오기 실패');
            });

        if (this.memberInfo) this.getInterestingList();
        setTimeout(this.getProjectList, 200);
    },
    data() {
        return {
            fundingDatas: [],
            interestingList: [],
            rankings: ['경험치', '도덕성', '적극성', '신뢰도', '전문성', '리더쉽'],
            rankDatas: [],
            dategap: [],
            project_num: '',
        };
    },
    methods: {
        goDetail(project_num) {
            this.$router.push({ name: 'FundingDetail', params: { pn: project_num } });
        },
        getInterestingList() {
            http.get(`project/interesting/${this.memberInfo.id}`)
                .then(({ data }) => {
                    this.interestingList = data.interestingProjectInfo;
                })
                .catch(() => {
                    alert(`관심 목록 가져오기 실패`);
                });
        },
        getProjectList() {
            let today = new Date();
            let year = today.getFullYear();
            let month = today.getMonth() + 1;
            let date = today.getDate();
            let current = year + '-' + month + '-' + date;

            http.get('project/fundingList')
                .then(({ data }) => {
                    var list = data.list;

                    for (var i = 0; i < list.length; i++) {
                        let startdate = list[i].start_date;
                        let deadline = list[i].deadline;

                        let ttoday = new Date(current);
                        let start = new Date(startdate);
                        let end = new Date(deadline);

                        list[i].love = false;

                        for (var j = 0; j < this.interestingList.length; j++) {
                            if (this.interestingList[j].project_num == list[i].project_num)
                                list[i].love = true;
                        }

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

                    this.fundingDatas = list;
                })
                .catch(() => {
                    alert('프로젝트 정보 가져오기 실패');
                });
        },
    },
};
</script>

<style scoped>
@import '../../assets/css/mainpage.css';
ul,
li {
    padding: 5px;
}
</style>
