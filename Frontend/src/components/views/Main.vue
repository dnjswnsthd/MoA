<template>
    <v-container class="col-8">
        <div class="progressing mt-10">
            <h2 class="centerText mainTitle">진행중인 팀 펀딩</h2>
            <div class="mt-10">
                <v-row class="centerContent my-10 col-12 mx-4">
                    <v-col
                        v-for="(funding, index) in fundingDatas"
                        :key="index"
                        class="funding col-3 height-300 my-5 mx-12"
                    >
                        <router-link to="/fundingdetail">
                            <!-- <div class="invisibleBox">
                                <p>프로젝트 이름 : {{ funding.project_name }}</p>
                                <p>참가인원 : {{ funding.participants }}명</p>
                                <p>팀장 : {{ funding.leader }}</p>
                                <p>멘토 : {{ funding.mentor_chk }}</p>
                                <p class="shorthand">설명 : {{ funding.description }}</p>
                            </div> -->
                            <img
                                src="@/assets/images/funding/fox.jpg"
                                class="fullWidth"
                                alt="예시"
                            />
                            <br />
                            <div>
                                <v-progress-linear
                                    color="#CE93D8"
                                    buffer-value="funding.start_date"
                                    value="funding.ending_date"
                                    stream
                                ></v-progress-linear>
                            </div>
                            <ul class="pt-4">
                                <v-row>
                                    <li>프로젝트 이름 : {{ funding.project_name }}</li>
                                    <v-spacer></v-spacer>
                                    <li>참가인원 : {{ funding.participants }}</li>
                                    <v-spacer></v-spacer>
                                </v-row>
                            </ul>
                            <ul class="pt-4">
                                <v-row>
                                    <li>종료 일자 : ~ {{ funding.end_date }}</li>
                                    <li>|</li>
                                    <li>팀장 : {{ funding.leader }}</li>
                                </v-row>
                            </ul>
                        </router-link>
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
        http.get(`project/fundingList`)
            .then(({ data }) => {
                this.fundingDatas = data.list;
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
        };
    },
};
</script>

<style scoped>
@import '../../assets/css/mainpage.css';
</style>
