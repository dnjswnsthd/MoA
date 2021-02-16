<template>
    <v-container class="col-lg-8 col-md-8 col-12 my-5">
        <div class="my-10">
            <h2 class="centerText mainTitle col-lg-4 col-md-12 col-12">모집 중인 팀 펀딩</h2>

            <div class="mt-10">
                <v-row class="centerContent col-12">
                    <v-col
                        v-for="(funding, index) in fundingDatas"
                        :key="index"
                        class="funding col-lg-4 col-md-6 col-6 my-5 px-2"
                    >
                        <div @click="goDetail(funding.project_num)">
                            <img
                                :src="categoriesImg[funding.category]"
                                alt="예시"
                                class="centerContent"
                                style="width:50%; height:50%;"
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
                        </div>
                    </v-col>
                </v-row>
            </div>
        </div>
        <div class="my-10">
            <h2 class="centerText mainTitle col-lg-2 col-md-12 col-12">멘토 랭킹</h2>
            <div class="mt-10">
                <v-row class="centerContent col-12">
                    <!-- <v-spacer></v-spacer> -->
                    <div
                        class="col-lg-4 col-md-6 col-12 my-lg-5 px-lg-2 rankInfoBox"
                        v-for="(rankName, index) in rankings"
                        :key="index"
                    >
                        <p class="centerText rankName centerContent">
                            {{ rankName }}
                        </p>

                        <v-row
                            class="mx-2 rankingBox"
                            v-for="(rankData, index) in rankDatas[index]"
                            :key="index"
                        >
                            <div class="rankInfo col-12">
                                <p class="centerText shorthand col-lg-2 col-md-2 col-2">
                                    {{ index + 1 }}위
                                </p>
                                <p class="centerText shorthand col-lg-7 col-md-8 col-8 ">
                                    {{ rankData.id }}
                                </p>
                                <p class="rightText shorthand col-lg-3 col-md-2 col-2">
                                    {{ rankData.score }}
                                </p>
                            </div>
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
        // state에 있는 memberInfo와 isLogin을 가져옴.
        ...mapState(['memberInfo', 'isLogin']),
        getMemberInfo() {
            return this.$store.getters.getMemberInfo; // store의 memberInfo를 get함.
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
        // 랭킹정보를 가져옴.
        http.get('member/rank')
            .then(({ data }) => {
                this.rankDatas = data.list; // data에 선언한 rankDatas에 data의 리스트를 넣음.
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
            categoriesImg: {
                디자인: require('@/assets/images/category/design(c).png'),
                'IT·프로그래밍': require('@/assets/images/category/computer(c).png'),
                '번역·통역': require('@/assets/images/category/translate(c).png'),
                '영상·사진·음향': require('@/assets/images/category/video(c).png'),
                '운세·상담': require('@/assets/images/category/lucky(c).png'),
                마케팅: require('@/assets/images/category/marketing(c).png'),
            },
            topic: '',
        };
    },

    methods: {
        // pn을 가지고, 해당 pn의 FundingDetail페이지로 이동.
        goDetail(project_num) {
            this.$router.push({ name: 'FundingDetail', params: { pn: project_num } });
        },
        // 관심목록을 가져옴.
        getInterestingList() {
            http.get(`project/interesting/${this.memberInfo.id}`)
                .then(({ data }) => {
                    this.interestingList = data.interestingProjectInfo;
                })
                .catch(() => {
                    alert(`관심 목록 가져오기 실패`);
                });
        },
        // 프로젝트 리스트를 가져옴.
        getProjectList() {
            let today = new Date();
            let year = today.getFullYear();
            let month = today.getMonth() + 1; // 0부터 시작이라 +1
            let date = today.getDate();
            let current = year + '-' + month + '-' + date; // 현재 시간을 0000-00-00 형태로 가져옴.

            // 리스트를 가져옴.
            http.get('project/fundingList')
                .then(({ data }) => {
                    var list = data.list; // list를 담음

                    // 프로젝트 이미지 아래에 progressbar표시를 위한 계산
                    for (var i = 0; i < list.length; i++) {
                        let startdate = list[i].start_date; // 해당 프로젝트의 시작날짜
                        let deadline = list[i].deadline; // 해당 프로젝트의 데드라인

                        let ttoday = new Date(current);
                        let start = new Date(startdate);
                        let end = new Date(deadline);

                        list[i].love = false;

                        for (var j = 0; j < this.interestingList.length; j++) {
                            if (this.interestingList[j].project_num == list[i].project_num)
                                list[i].love = true;
                        }

                        let gap = Math.floor(
                            (end.getTime() - start.getTime()) / (1000 * 60 * 60 * 24) // 끝 시간 - 시작 시간
                        );
                        let gap2 = Math.floor(
                            (end.getTime() - ttoday.getTime() + 32400000) / (1000 * 60 * 60 * 24) // 끝 시간 - 현재 시간
                        );

                        let result = 100 - Math.ceil((gap2 / gap) * 100); // 결과값을 저장해서 완료된 시점을 100이라 하고 거기서 뺀만큼을 표시.

                        this.dategap[i] = result;
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
.row + .row {
    margin-top: 10px;
}
.rankName {
    margin: 0 auto;
    margin-bottom: 15px;
}
</style>
