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
                <v-tab v-if="mentor.id" class="mt-2">
                    <p class="evaluateFont">{{ mentor.name }} 멘토</p>
                </v-tab>
                <v-tab class="mt-2" v-for="(mentee, index) in mentees" :key="index">
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
                            <RadarCanvas
                                :id="mentor.id"
                                :labels="mentorEv.labels"
                                :data="mentorEv.score"
                            ></RadarCanvas>
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
                                <p v-for="index in 5" :key="index" class="evaluateFont">
                                    {{ mentorEv.labels[index - 1] }}&nbsp;은/는 어땠나요?
                                    <v-rating
                                        v-model="mentorEv.score[index - 1]"
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
                            <!-- <canvas class="canvas" height="40vh" width="80vw"></canvas> -->
                            <RadarCanvas
                                :id="mentee.id"
                                :labels="menteeEv.labels"
                                :data="menteeEv.scores[index]"
                            ></RadarCanvas>
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
                            <p v-for="i in 5" :key="i" class="evaluateFont">
                                {{ menteeEv.labels[i - 1] }}&nbsp;은/는 어땠나요?
                                <v-rating
                                    v-model="menteeEv.scores[index][i - 1]"
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
import RadarCanvas from './RadarCanvas.vue';

export default {
    name: 'Evaluate',
    components: {
        RadarCanvas,
    },
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
    data() {
        return {
            project_num: Number,
            tab: null,
            mentorEv: {
                labels: ['도덕성', '적극성', '신뢰성', '전문성', '리더십'],
                values: ['morality', 'positiveness', 'reliability', 'professional', 'leadership'],
                score: [0, 0, 0, 0, 0],
            },
            menteeEv: {
                labels: ['의사소통', '책임감', '수행능력', '수행자세', '리더십'],
                values: [
                    'communication',
                    'responsibility',
                    'performance',
                    'positiveness',
                    'leadership',
                ],
                scores: [],
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
                                this.menteeEv.scores.push([0, 0, 0, 0, 0]);
                            }
                        }
                    });
                })
                .catch(() => {
                    alert(`평가 대상자 가져오기 실패`);
                });
            if (this.mentor) {
                console.log('ㅅㅄㅄㅄㅅㅄㅄㅂ');
                this.createCharts();
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

            for (var i = 0; i < 5; i++) {
                this.mentor[this.mentorEv.values[i]] = this.mentorEv.score[i];
            }
            this.menteeEv.scores.forEach((el, index) => {
                console.log(`index : ${index}`);
                console.log(el);
                for (i = 0; i < 5; i++) {
                    this.mentees[index][this.menteeEv.values[i]] = this.menteeEv.scores[index][i];
                }
            });

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
