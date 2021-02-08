<template>
    <v-container class="col-8">
        <div class="progressing mt-10">
            <h2 class="centerText mainTitle">진행중인 팀 펀딩</h2>
            <div class="mt-10">
                <v-row class="centerContent my-10 col-12 mx-4">
                    <v-col
                        v-for="(funding, index) in fundingDatas"
                        :key="index"
                        class="funding col-3 my-5 mx-12"
                    >
                        <div @click="goDetail(funding.project_num)">
                            <img
                                src="@/assets/images/funding/fox.jpg"
                                class="fullWidth"
                                alt="예시"
                            />
                            <br />
                            <div>
                                <v-progress-linear
                                    color="#CE93D8"
                                    :value="dategap[index]"
                                    stream
                                ></v-progress-linear>
                            </div>
                            <ul>
                                <li class="shorthand" style="color:#ab47bc; font-weight:900">
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
        <div>
            <h2 class="centerText mainTitle">멘토 랭킹</h2>
            <div class="mt-10">
                <v-row class="fundingRow col-12 mx-1 ">
                    <!-- <v-spacer></v-spacer> -->
                    <div
                        class="col-4 height-300"
                        v-for="(rankName, index) in rankings"
                        :key="index"
                    >
                        <div class="halfHeight">
                            <p class="centerText" style="font-size:20px">{{ rankName }}</p>
                        </div>
                        <v-col
                            class="rankingBox"
                            v-for="(rankData, index) in rankDatas[index]"
                            :key="index"
                        >
                            <v-row class="halfHeight mx-2">
                                <v-col class="centerText col-2"> {{ index + 1 }}위 </v-col>
                                <v-col class="centerText col-7">
                                    {{ rankData.id }}
                                </v-col>
                                <v-col class="rightText col-3">
                                    {{ rankData.score }}
                                </v-col>
                            </v-row>
                        </v-col>
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
            fundings: [
                'funding01',
                'funding02',
                'funding03',
                'funding03',
                'funding01',
                'funding02',
            ],
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
.pdate {
    width: 50%;
    margin: 0;
    float: left;
}
</style>
