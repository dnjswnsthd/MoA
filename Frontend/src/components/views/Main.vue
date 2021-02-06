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

export default {
    created() {
        let today = new Date();
        let year = today.getFullYear();
        let month = today.getMonth() + 1;
        let date = today.getDate();

        let current = year + '-' + month + '-' + date;
        // let end_day = today.getTime(this.fundingDatas.end_date);
        // let start_day = today.getTime(this.fundingDatas.start_date);
        // let gap = end_day - start_day;
        // let res = Math.ceil(gap / (1000 * 60 * 60 * 24));
        // console.log(res);

        http.get(`project/fundingList`)
            .then(({ data }) => {
                this.fundingDatas = data.list;

                for (var i = 0; i < 6; i++) {
                    let startdate = new Date(this.fundingDatas[i].start_date);
                    let deadline = new Date(this.fundingDatas[i].deadline);
                    let ttoday = new Date(current);
                    // let gap = (today.getTime(this.fundingDatas[i].end_date) - today.getTime(this.fundingDatas[i].start_date)) / (1000 * 60 * 60 * 24);
                    let gap = Math.ceil(
                        (deadline.getTime() - startdate.getTime()) / (1000 * 60 * 60 * 24)
                    );
                    let gap2 = Math.ceil(
                        (deadline.getTime() - ttoday.getTime()) / (1000 * 60 * 60 * 24)
                    );

                    let result = 100 - Math.ceil((gap / gap2) * 100);
                    console.log('gap : ' + gap);
                    console.log(gap2);
                    console.log('result : ' + result);
                    // let res = Math.ceil(gap );
                    this.dategap[i] = result;
                    // console.log(res);
                }
            })
            .catch(() => {
                alert(`프로젝트 정보 가져오기 실패`);
            });
        http.get(`member/rank`)
            .then(({ data }) => {
                this.rankDatas = data.list;
            })
            .catch(() => {
                alert(`랭킹 정보 가져오기 실패`);
            });
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
            rankings: ['경험치', '도덕성', '적극성', '신뢰도', '전문성', '리더쉽'],
            rankDatas: [],
            dategap: [],
            project_num: '',
        };
    },
    methods: {
        goDetail(project_num) {
            console.log('index : ' + project_num);
            // this.project_num = this.fundingDatas[index].project_num;
            // console.log(this.project_num);
            this.$router.push({ name: 'FundingDetail', params: { pn: project_num } });
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
