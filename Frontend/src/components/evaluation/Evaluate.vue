<template>
    <div class="col-8 centerContent height-1000">
        <p class="evaluateTitle">평가</p>
        <v-toolbar
            color="#ab47bc"
            dark
            style="border-top-left-radius:20px; border-top-right-radius:20px;"
        >
            <v-tabs v-model="tab" align-with-title>
                <v-tabs-slider color="yellow" style="border: 2px;"></v-tabs-slider>
                <v-tab v-if="mentor.id" class="mt-2" @click="createCharts">
                    <p class="evaluateFont">{{ mentor.name }} 멘토</p>
                </v-tab>
                <v-tab
                    class="mt-2"
                    v-for="(mentee, index) in mentees"
                    :key="index"
                    @click="createCharts"
                >
                    <p class="evaluateFont">{{ mentee.name }}</p>
                </v-tab>
                <v-spacer></v-spacer>
                <v-btn text class="mt-3 evaluateFont mr-3" @click="evaluate">제출하기</v-btn>
            </v-tabs>
        </v-toolbar>
        <v-tabs-items v-model="tab">
            <!-- 멘토가 있을 경우 멘토 평가 항목을 보여줌 -->
            <v-tab-item v-if="mentor.id">
                <v-row>
                    <v-spacer></v-spacer>
                    <v-col style="margin: auto;">
                        <div class="width-500 height-300">
                            <canvas class="canvas" height="40vh" width="80vw"></canvas>
                        </div>
                    </v-col>
                    <v-spacer></v-spacer>
                    <v-col>
                        <v-card flat width="320">
                            <v-card-text>
                                <h2 class="pb-5 pt-5">
                                    {{ mentor.name }} 멘토를 평가해주세요!
                                    <span>
                                        <v-btn
                                            class="ml-3"
                                            medium
                                            outlined
                                            color="#bc6ff1"
                                            @click="initMentor"
                                        >
                                            초기화
                                        </v-btn>
                                    </span>
                                </h2>
                                <p
                                    v-for="(item, index) in mentorEvaluationItem"
                                    :key="index"
                                    class="evaluateFont"
                                >
                                    {{ item.title }}&nbsp;은/는 어땠나요?
                                    <v-rating
                                        v-model="mentor[`${item.value}`]"
                                        background-color="purple lighten-3"
                                        color="#bc6ff1"
                                        large
                                    ></v-rating>
                                </p>
                            </v-card-text>
                        </v-card>
                    </v-col>
                    <v-spacer></v-spacer>
                </v-row>
            </v-tab-item>
            <!-- 멘티들의 평가를 위한 tab-item -->
            <v-tab-item v-for="(mentee, index) in mentees" :key="index">
                <v-row>
                    <v-spacer></v-spacer>
                    <v-col style="margin: auto;">
                        <div class="width-500 height-300">
                            <canvas class="canvas" height="40vh" width="80vw"></canvas>
                        </div>
                    </v-col>
                    <v-spacer></v-spacer>
                    <v-card flat width="320">
                        <v-card-text>
                            <h2 class="pb-5 pt-5">
                                {{ mentee.name }}&nbsp;을(를) 평가해주세요!

                                <span>
                                    <v-btn
                                        class="ml-3"
                                        medium
                                        outlined
                                        color="#bc6ff1"
                                        @click="initMentee(index)"
                                    >
                                        초기화
                                    </v-btn>
                                </span>
                            </h2>
                            <p
                                v-for="(item, index) in menteeEvaluationItem"
                                :key="index"
                                class="evaluateFont"
                            >
                                {{ item.title }}&nbsp;은/는 어땠나요?
                                <v-rating
                                    v-model="mentee[`${item.value}`]"
                                    background-color="purple lighten-3"
                                    color="#bc6ff1"
                                    large
                                ></v-rating>
                            </p>
                        </v-card-text>
                    </v-card>
                    <v-spacer></v-spacer>
                </v-row>
            </v-tab-item>
        </v-tabs-items>
    </div>
</template>

<script>
import http from '@/util/http-common';
import { mapState } from 'vuex';
import Chart from 'chart.js';

export default {
    name: 'Evaluate',
    computed: {
        ...mapState(['memberInfo', 'isLogin']),
        getMemberInfo() {
            return this.$store.getters.getMemberInfo;
        },
    },
    mounted() {
        this.project_num = this.$route.params.pn;
        setTimeout(this.init(), 200);
    },
    watch: {
        mentor() {
            this.createCharts();
        },
    },
    data() {
        return {
            project_num: Number,
            tab: null,
            mentorEvaluationItem: [
                { title: '도덕성', value: 'morality' },
                { title: '적극성', value: 'positiveness' },
                { title: '신뢰성', value: 'reliability' },
                { title: '전문성', value: 'professional' },
                { title: '리더십', value: 'leadership' },
            ],
            menteeEvaluationItem: [
                { title: '의사소통', value: 'communication' },
                { title: '책임감', value: 'responsibility' },
                { title: '수행능력', value: 'performance' },
                { title: '수행자세', value: 'positiveness' },
                { title: '리더십', value: 'leadership' },
            ],
            mentorData: {
                labels: ['도덕성', '적극성', '신뢰성', '전문성', '리더십'],
                datasets: [
                    {
                        data: [5, 3, 4, 2, 5],
                        backgroundColor: 'rgba(188, 111, 241, .5)',
                        borderColor: '#ab47bc',
                        borderWidth: '1',
                    },
                ],
            },
            options: {
                legend: {
                    display: false,
                },
                reponsive: false,
                scale: {
                    angleLines: {
                        display: false,
                    },
                    ticks: {
                        min: 0,
                        max: 5,
                        stepSize: 1,
                    },
                    pointLabels: {
                        fontSize: 18,
                        fontColor: '#ab47bc',
                        fontFamily: 'CookieRunOTF-Bold',
                        src: `url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_twelve@1.0/CookieRunOTF-Bold00.woff') format('woff')`,
                        fontWeight: 'normal',
                        fontStyle: 'normal',
                    },
                },
            },
            mentor: {},
            mentees: [],
        };
    },
    methods: {
        init() {
            console.log(`call init()`);
            http.get(`project/evaluateList/${this.project_num}`)
                .then(({ data }) => {
                    data.list.forEach((el) => {
                        if (el.id != this.memberInfo.id) {
                            if (el.status == 1) {
                                this.mentor = el;
                            } else {
                                this.mentees.push(el);
                            }
                        }
                    });
                })
                .catch(() => {
                    alert(`평가 대상자 가져오기 실패`);
                });
            if (this.mentor) {
                this.createCharts();
                console.log('ㅅㅄㅄㅄㅅㅄㅄㅂ');
            }
        },
        initMentor() {
            this.mentorEvaluationItem.forEach((el) => {
                this.mentor[`${el.value}`] = 0;
            });
        },
        initMentee(index) {
            this.menteeEvaluationItem.forEach((el) => {
                this.mentees[index][`${el.value}`] = 0;
            });
        },
        evaluate() {
            console.log(`evaluate()`);
            // 평가 완료 상태 반영
            http.put(`member/evaluate/change`, {
                id: this.memberInfo.id,
                project_num: this.project_num,
            })
                .then(({ data }) => {
                    console.log(data.message);
                    if (data.message == 'fail') {
                        alert(`평가 등록 실패!!`);
                        return;
                    }
                })
                .catch(() => {
                    alert(`평가 등록 실패`);
                });

            // 평가 반영
            http.post(`member/evaluate`, {
                project_num: this.project_num,
                mentor: this.mentor,
                mentees: this.mentees,
            })
                .then(({ data }) => {
                    console.log(data.message);
                })
                .catch(() => {
                    alert(`평가 등록 실패`);
                });
            alert(`평가 완료!`);

            this.$router.push({ name: 'MyPage' });
        },
        createCharts() {
            const ctx = document.getElementsByClassName('canvas');

            for (var i = 0; i < ctx.length; i++) {
                console.log(ctx[i]);
                new Chart(ctx[i], {
                    type: 'radar',
                    data: this.mentorData,
                    options: this.options,
                });
            }
        },
        chart() {
            console.log(`call chart`);
            console.log(this.mentor);
            // if (this.mentor.id) {
            //     this.createCharts(this.mentor.id);
            // }
            console.log(this.mentees);
            this.mentees.forEach((el) => {
                this.createCharts(el.id);
            });
        },
    },
};
</script>

<style scoped>
.evaluateFont {
    font-size: large;
}

.evaluateTitle {
    font-size: xx-large;
}

/* .canvas {
    width: 100%;
    height: 300px;
} */
</style>
