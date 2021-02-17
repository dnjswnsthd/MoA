<template>
    <v-container class="col-lg-8 col-md-12 col-12 centerContent mb-15">
        <p class="pageTitle">
            {{ project.category }}
        </p>

        <div class="col-12 centerContent">
            <v-row>
                <div class="col-6">
                    <div class="projectImgBox">
                        <img
                            :src="categoriesImg[project.category]"
                            alt="샘플이미지"
                            class="projectImg"
                        />
                    </div>
                </div>
                <v-spacer></v-spacer>
                <div class="col-6">
                    <div class="infoBox">
                        <v-spacer></v-spacer>
                        <p>제목 : {{ project.project_name }}</p>
                        <v-spacer></v-spacer>
                        <p>프로젝트 일정 : {{ project.start_date }} ~ {{ project.end_date }}</p>
                        <v-spacer></v-spacer>
                        <p>펀딩 금액 : {{ project.funding_cost }}원</p>
                        <p>참가 인원 : {{ project.participants }}명</p>
                        <v-spacer></v-spacer>
                        <p>
                            Mentor : <span>{{ project.mentor_chk }}</span>
                        </p>
                        <v-spacer></v-spacer>
                    </div>
                </div>
            </v-row>
            <v-row class="col-12 centerText">
                <v-dialog v-model="dialog" persistent max-width="290">
                    <template v-slot:activator="{ on, attrs }">
                        <div class="col-3">
                            <p class="fundingBtn" v-bind="attrs" v-on="on">펀딩하기</p>
                        </div>
                    </template>
                    <v-card>
                        <v-card-title class="headline"> 펀딩신청 </v-card-title>
                        <v-card-text>이 프로젝트에 정말 참여하시겠습니까?</v-card-text>
                        <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn color="green darken-1" text @click="dialog = false">
                                취소
                            </v-btn>
                            <v-btn
                                color="green darken-1"
                                text
                                @click="fundingApply(project.participants, project.funding_cost)"
                            >
                                참여
                            </v-btn>
                        </v-card-actions>
                    </v-card>
                </v-dialog>

                <v-dialog v-model="participantsDialog" scrollable max-width="300px">
                    <template v-slot:activator="{ on, attrs }">
                        <div class="col-3">
                            <p
                                class="fundingBtn"
                                v-bind="attrs"
                                v-on="on"
                                @click="getParticipants(project.project_num)"
                            >
                                참여 멤버
                            </p>
                        </div>
                    </template>
                    <v-card>
                        <v-card-title>참여 멤버</v-card-title>
                        <v-divider></v-divider>
                        <v-card-text class="height-300">
                            <p v-for="(participant, index) in participants" :key="index">
                                {{ participant.id }}
                            </p>
                        </v-card-text>
                        <v-divider></v-divider>
                        <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn color="blue darken-1" text @click="participantsDialog = false">
                                닫기
                            </v-btn>
                        </v-card-actions>
                    </v-card>
                </v-dialog>

                <div class="col-3" @click="kakaoShare" id="shareBtn">
                    <p class="fundingBtn" @click="kakaoShare" id="shareBtn">공유 하기</p>
                </div>

                <div class="col-3" @click="plusLove">
                    <p class="fundingBtn" v-if="loveFlag">{{ this.love }}</p>
                    <p class="fundingBtn" v-else>{{ this.cancelLove }}</p>
                </div>
            </v-row>
            <v-col class="explainBox my-10">
                <p class="pageSubTitle">상세 설명</p>

                <div
                    class="mt-5 px-5 py-5 height-300"
                    v-html="project.description"
                    style="border: 1px solid black; overflow: scroll-y"
                />
            </v-col>
        </div>

        <div class="col-12 centerContent mb-15">
            <v-row>
                <v-col>
                    <v-sheet height="64" class="col-12 mb-5">
                        <v-toolbar flat>
                            <v-btn outlined class="mr-4" color="grey darken-2" @click="setToday">
                                Today
                            </v-btn>
                            <v-btn fab text small color="grey darken-2" @click="prev">
                                <v-icon small> mdi-chevron-left </v-icon>
                            </v-btn>
                            <v-toolbar-title v-if="$refs.calendar">
                                {{ $refs.calendar.title }}
                            </v-toolbar-title>
                            <v-btn fab text small color="grey darken-2" @click="next">
                                <v-icon small> mdi-chevron-right </v-icon>
                            </v-btn>
                            <v-spacer></v-spacer>
                            <v-menu bottom right>
                                <template v-slot:activator="{ on, attrs }">
                                    <v-btn outlined color="grey darken-2" v-bind="attrs" v-on="on">
                                        <span>{{ typeToLabel[type] }}</span>
                                        <v-icon right> mdi-menu-down </v-icon>
                                    </v-btn>
                                </template>
                                <v-list>
                                    <v-list-item @click="type = 'day'">
                                        <v-list-item-title>Day</v-list-item-title>
                                    </v-list-item>
                                    <v-list-item @click="type = 'week'">
                                        <v-list-item-title>Week</v-list-item-title>
                                    </v-list-item>
                                    <v-list-item @click="type = 'month'">
                                        <v-list-item-title>Month</v-list-item-title>
                                    </v-list-item>
                                    <v-list-item @click="type = '4day'">
                                        <v-list-item-title>4 days</v-list-item-title>
                                    </v-list-item>
                                </v-list>
                            </v-menu>

                            <v-btn style="margin-left: 20px" @click="moveSchedule">일정 관리</v-btn>
                        </v-toolbar>
                    </v-sheet>

                    <v-sheet height="550">
                        <v-calendar
                            ref="calendar"
                            v-model="focus"
                            color="primary"
                            :events="events"
                            :type="type"
                            @click:event="showEvent"
                            @click:more="viewDay"
                            @click:date="viewDay"
                            @change="updateRange"
                        ></v-calendar>
                        <v-menu
                            v-model="selectedOpen"
                            :close-on-content-click="false"
                            :activator="selectedElement"
                            offset-x
                        >
                            <v-card color="grey lighten-4" min-width="350px" flat>
                                <v-toolbar :color="selectedEvent.color" dark>
                                    <!-- <v-btn icon>
                    <v-icon>mdi-pencil</v-icon>
                  </v-btn> -->
                                    <v-toolbar-title v-html="selectedEvent.name"></v-toolbar-title>
                                    <v-spacer></v-spacer>
                                    <!-- <v-btn icon>
                    <v-icon>mdi-heart</v-icon>
                  </v-btn>
                  <v-btn icon>
                    <v-icon>mdi-dots-vertical</v-icon>
                  </v-btn> -->
                                </v-toolbar>

                                <v-card-text>
                                    <h2 class="mb-1">일정 설명</h2>
                                    <v-textarea
                                        v-model="selectedEvent.description"
                                        readonly
                                    ></v-textarea>
                                </v-card-text>
                                <!-- <v-card-actions>
                  <v-btn text color="secondary" @click="selectedOpen = false">
                    Cancel
                  </v-btn>
                </v-card-actions> -->
                            </v-card>
                        </v-menu>
                    </v-sheet>
                </v-col>
            </v-row>
        </div>
    </v-container>
</template>

<script>
import { mapState } from 'vuex';
import http from '@/util/http-common';
import swal from 'sweetalert';

export default {
    data: () => ({
        focus: '',
        type: 'month',
        typeToLabel: {
            month: 'Month',
            week: 'Week',
            day: 'Day',
            '4day': '4 Days',
        },
        selectedEvent: {
            name: '',
            description: '',
        },
        selectedElement: null,
        selectedOpen: false,
        events: [],
        colors: ['orange', 'purple', 'green'],
        project: {},
        id: '',
        loveFlag: true,
        project_num: '',
        mwurl: '',
        wurl: '',
        dialog: false,
        participants: {},
        participantsDialog: false,
        love: '관심등록',
        cancelLove: '관심취소',
        schedule: {},
        categoriesImg: {
            디자인: require('@/assets/images/category/design(c).png'),
            'IT·프로그래밍': require('@/assets/images/category/computer(c).png'),
            '번역·통역': require('@/assets/images/category/translate(c).png'),
            '영상·사진·음향': require('@/assets/images/category/video(c).png'),
            '운세·상담': require('@/assets/images/category/lucky(c).png'),
            마케팅: require('@/assets/images/category/marketing(c).png'),
        },
    }),
    computed: {
        ...mapState(['memberInfo', 'isLogin']),
    },
    mounted() {
        this.project_num = this.$route.params.pn;
        this.mwurl = 'https://i4d111.p.ssafy.io/fundingDetail/' + this.project_num; // mobileurl
        this.wurl = 'https://i4d111.p.ssafy.io/fundingDetail/' + this.project_num; // weburl

        setTimeout(this.init, 200);
        // setTimeout(this.$refs.calendar.checkChange(), 1000);
        // 해당 project_num의 상세페이지로 이동.
        http.get(`project/fundingDetail/${this.project_num}`)
            .then((response) => {
                if (response.data.message == 'success') {
                    this.project = response.data.project;
                    this.id = this.memberInfo.id;
                } else {
                    alert('정보조회실패');
                }
            })
            .catch(() => {});

        http.get(`project/interesting/${this.memberInfo.id}`)
            .then(({ data }) => {
                console.log(data);
                for (var i = 0; i < data.interestingProjectInfo.length; i++) {
                    if (data.interestingProjectInfo[i].project_num == this.project.project_num) {
                        this.loveFlag = true;
                        break;
                    }
                }
            })
            .catch(() => {
                console.log('fffffff');
            });
    },

    methods: {
        init() {
            http.get(`project/fundingDetail/${this.project_num}`)
                .then((response) => {
                    if (response.data.message == 'success') {
                        this.project = response.data.project;
                        this.id = this.memberInfo.id;
                    } else {
                        alert('정보조회실패');
                    }
                })
                .catch(() => {});

            http.get(`project/interesting/${this.memberInfo.id}`)
                .then(({ data }) => {
                    console.log(data);
                    for (var i = 0; i < data.interestingProjectInfo.length; i++) {
                        if (
                            data.interestingProjectInfo[i].project_num == this.project.project_num
                        ) {
                            this.loveFlag = false;
                            break;
                        }
                    }
                })
                .catch(() => {
                    console.log('fffffff');
                });
            // 일정버튼을 클릭 시 pn을 가지고, 일정관리 페이지로 이동.
            http.get(`sprint/search/${this.project_num}`)
                .then((response) => {
                    console.log('pn : ' + this.project_num);
                    console.log('message : ' + response.data.message);
                    if (response.data.message == 'success') {
                        console.log(response.data.sprintList);
                        this.schedule = response.data.sprintList;
                        this.updateRange();
                    } else {
                        alert('받아오다 실패!');
                    }
                })
                .catcb(() => {
                    alert('에러발생!');
                });
        },
        // 관심등록.
        plusLove() {
            if (!this.loveFlag) {
                swal('관심 펀딩 취소되었습니다!', {
                    icon: 'success',
                });
                http.post(`project/interestingDelete`, {
                    project_num: this.project_num,
                    id: this.memberInfo.id,
                })
                    .then(({ data }) => {
                        console.log(data);
                    })
                    .catch(() => {
                        console.log(`fail`);
                    });
            } else {
                swal('관심 펀딩 추가되었습니다!', {
                    icon: 'success',
                });
                http.post(`project/interesting`, {
                    project_num: this.project_num,
                    id: this.memberInfo.id,
                })
                    .then(({ data }) => {
                        console.log(data);
                    })
                    .catch(() => {
                        console.log(`fail`);
                    });
            }
            this.loveFlag = !this.loveFlag;
        },

        viewDay({ date }) {
            this.focus = date;
            this.type = 'day';
        },
        getEventColor(event) {
            return event.color;
        },
        setToday() {
            this.focus = '';
        },
        prev() {
            this.$refs.calendar.prev();
        },
        next() {
            this.$refs.calendar.next();
        },
        showEvent({ nativeEvent, event }) {
            const open = () => {
                this.selectedEvent = event;
                this.selectedElement = nativeEvent.target;
                console.log(`open`);
                console.log(this.selectedEvent);
                setTimeout(() => {
                    this.selectedOpen = true;
                }, 10);
            };

            if (this.selectedOpen) {
                this.selectedOpen = false;
                setTimeout(open, 10);
            } else {
                open();
            }

            nativeEvent.stopPropagation();
        },
        updateRange() {
            const events = [];

            console.log(`updateRange`);
            for (let i = 0; i < this.schedule.length; i++) {
                console.log('status : ' + this.schedule[i].sprint_status);
                // 달력의 범위지정.
                events.push({
                    name: this.schedule[i].sprint_name, // 가져온 일정의 이름.
                    start: new Date(this.schedule[i].sprint_start_date), // 일정의 start_date
                    end: new Date(this.schedule[i].sprint_end_date), // 일정의 end_date
                    color: this.colors[this.schedule[i].sprint_status], // 달력에 표시할 때 색고름.
                    description: this.schedule[i].sprint_description, // 클릭 시 나타날 description
                });
            }

            this.events = events;
        },

        // schedule페이지로 pn을 가지고 이동.
        moveSchedule() {
            this.$router.push({ name: 'Schedule', query: { pn: this.project_num } });
        },

        // 펀딩 참여.
        fundingApply(participants, funding_cost) {
            if (this.memberInfo.point < funding_cost / participants) {
                // 포인트가 부족할 시
                swal('포인트가 부족합니다!', {
                    icon: 'error',
                });
            } else {
                if (this.memberInfo.id == this.project.leader) {
                    swal('당신이 리더인 프로젝트 입니다.', {
                        icon: 'error',
                    });
                } else {
                    http.post('/project/waiting', {
                        // 대기목록에 추가.
                        id: this.memberInfo.id,
                        project_num: this.project_num,
                    })
                        .then((response) => {
                            console.log('메시지 : ' + response.data.message);
                            if (response.data.message == 'success') {
                                console.log(response.data.message);
                                swal('신청 성공!', {
                                    icon: 'success',
                                });
                                this.dialog = false;
                            } else {
                                // 이미 신청을 한 프로젝트면,
                                swal('이미 신청되어있는 프로젝트 입니다.', {
                                    icon: 'error',
                                });
                                this.dialog = false;
                            }
                        })
                        .catch(() => {});
                }
            }
        },
        // 참여인원을 가져옴.
        getParticipants(project_num) {
            // pn을 가지고,
            console.log(project_num);
            http.get(`/project/memberchk/${project_num}`).then((response) => {
                // 해당 프로젝트의 멤버를 가져옴.
                if (response.data.message == 'success') {
                    this.participants = response.data.member;
                } else {
                    swal('확인 실패.', {
                        icon: 'error',
                    });
                }
            });
        },
        // 공유하기 버튼 클릭시 카카오톡 공유.
        kakaoShare() {
            window.Kakao.Link.createDefaultButton({
                container: '#shareBtn',
                objectType: 'feed',
                content: {
                    title: this.project.project_name, // 카카오톡에 나올 제목
                    description: this.project.description, // 카카오톡에 나올 설명
                    imageUrl: 'https://i4d111.p.ssafy.io/img/logo(Bg).e2cc6ce0.png', // 카카오톡에 나올 이미지
                    imageWidth: 250, // 이미지 넓이
                    imageHeight: 100, // 이미지 높이
                    link: {
                        // 연결될 링크
                        mobileWebUrl: this.mwurl, // 모바일 링크
                        webUrl: this.wurl, // 웹 링크
                    },
                },
            });
        },
    },
};
</script>
<style scoped>
@import '../../assets/css/fundingdetail.css';
</style>
