<template>
    <div class="">
        <div class="banner col-6 centerContent">
            <p class="pl-5 pageTitle">
                {{ project.category }}
            </p>
        </div>

        <div class="col-6 centerContent px-10 py-10">
            <v-row>
                <div class="col-6">
                    <div class="projectImgBox">
                        <img
                            src="@/assets/category/marketing(c).png"
                            alt="샘플이미지"
                            class="projectImg"
                        />
                    </div>
                </div>
                <v-spacer></v-spacer>
                <div class="col-6">
                    <v-col
                        style="
              background-color: #fff;
              font-size: 20px;
              margin: 20% auto 10% auto;
            "
                    >
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
                    </v-col>
                </div>
            </v-row>
            <v-row class="col-12 btnList" style="margin: 0; padding: 0">
                <v-spacer></v-spacer>
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
                            <v-btn color="green darken-1" text @click="fundingApply">
                                참여
                            </v-btn>
                        </v-card-actions>
                    </v-card>
                </v-dialog>

                <v-spacer></v-spacer>
                <v-dialog v-model="participantsDialog" scrollable max-width="300px">
                    <template v-slot:activator="{ on, attrs }">
                        <div class="col-3">
                            <p class="fundingBtn" v-bind="attrs" v-on="on" @click="getParticipants">
                                참여 멤버
                            </p>
                        </div>
                    </template>
                    <v-card>
                        <v-card-title>참여 멤버</v-card-title>
                        <v-divider></v-divider>
                        <v-card-text style="height: 300px">
                            <p v-for="(participant, index) in participants" :key="index">
                                {{ participant.id }}
                            </p>
                        </v-card-text>
                        <v-divider></v-divider>
                        <v-card-actions>
                            <v-sapcer></v-sapcer>
                            <v-btn color="blue darken-1" text @click="participantsDialog = false">
                                닫기
                            </v-btn>
                            <v-btn color="blue darken-1" text @click="participantsDialog = false">
                                확인
                            </v-btn>
                        </v-card-actions>
                    </v-card>
                </v-dialog>

                <v-spacer></v-spacer>
                <div class="col-3">
                    <p class="fundingBtn">공유 하기</p>
                </div>
                <v-spacer></v-spacer>
                <div class="col-3" @click="plusLove">
                    <p class="fundingBtn" v-if="loveFlag">{{ this.love }}</p>
                    <p class="fundingBtn" v-else>{{ this.cancelLove }}</p>
                </div>
                <v-spacer></v-spacer>
            </v-row>
            <v-col class="explainBox my-10">
                <p
                    style="
            text-decoration: underline overline;
            text-underline-position: under;
          "
                >
                    상세 설명
                </p>

                <v-textarea
                    solo
                    name="input-7-4"
                    v-model="project.description"
                    readonly
                ></v-textarea>
            </v-col>
        </div>
        <div class="col-8 centerContent">
            <v-row class="fill-height">
                <v-col>
                    <v-sheet height="64">
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
                    <v-sheet height="600">
                        <v-calendar
                            ref="calendar"
                            v-model="focus"
                            color="primary"
                            :events="events"
                            :event-color="getEventColor"
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
                                    <v-btn icon>
                                        <v-icon>mdi-pencil</v-icon>
                                    </v-btn>
                                    <v-toolbar-title v-html="selectedEvent.name"></v-toolbar-title>
                                    <v-spacer></v-spacer>
                                    <v-btn icon>
                                        <v-icon>mdi-heart</v-icon>
                                    </v-btn>
                                    <v-btn icon>
                                        <v-icon>mdi-dots-vertical</v-icon>
                                    </v-btn>
                                </v-toolbar>
                                <v-card-text>
                                    <span v-html="selectedEvent.details"></span>
                                </v-card-text>
                                <v-card-actions>
                                    <v-btn text color="secondary" @click="selectedOpen = false">
                                        Cancel
                                    </v-btn>
                                </v-card-actions>
                            </v-card>
                        </v-menu>
                    </v-sheet>
                </v-col>
            </v-row>
        </div>
    </div>
</template>
<script>
import { mapState } from 'vuex';
import http from '@/util/http-common';

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
        selectedEvent: {},
        selectedElement: null,
        selectedOpen: false,
        events: [],
        colors: ['blue', 'indigo', 'deep-purple', 'cyan', 'green', 'orange', 'grey darken-1'],
        names: ['Meeting', 'Holiday', 'PTO', 'Travel', 'Event', 'Birthday', 'Conference', 'Party'],
        project: {},
        id: '',
        loveFlag: true,
        project_num: '',
        dialog: false,
        participants: [],
        participantsDialog: false,
        love: '관심등록',
        cancelLove: '관심취소',
    }),
    computed: {
        ...mapState(['memberInfo', 'isLogin']),
    },
    mounted() {
        this.$refs.calendar.checkChange();

        console.log(this.project_num);
    },
    created() {
        this.project_num = this.$route.params.pn;
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
    },

    methods: {
        plusLove() {
            console.log('dddd');
            this.loveFlag = !this.loveFlag;
            if (this.loveFlag) alert('관심 펀딩에 취소되었습니다');
            else alert('관심 펀딩이 추가되었습니다.');
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
        updateRange({ start, end }) {
            const events = [];

            const min = new Date(`${start.date}T00:00:00`);
            const max = new Date(`${end.date}T23:59:59`);
            const days = (max.getTime() - min.getTime()) / 86400000;
            const eventCount = this.rnd(days, days + 20);

            for (let i = 0; i < eventCount; i++) {
                const allDay = this.rnd(0, 3) === 0;
                const firstTimestamp = this.rnd(min.getTime(), max.getTime());
                const first = new Date(firstTimestamp - (firstTimestamp % 900000));
                const secondTimestamp = this.rnd(2, allDay ? 288 : 8) * 900000;
                const second = new Date(first.getTime() + secondTimestamp);

                events.push({
                    name: this.names[this.rnd(0, this.names.length - 1)],
                    start: first,
                    end: second,
                    color: this.colors[this.rnd(0, this.colors.length - 1)],
                    timed: !allDay,
                });
            }

            this.events = events;
        },
        rnd(a, b) {
            return Math.floor((b - a + 1) * Math.random()) + a;
        },
        moveSchedule() {
            this.$router.push({ name: 'Schedule', params: { pn: this.project_num } });
        },
        fundingApply() {
            // http.post('/project/waiting,{
            // })
            //     .then((response) => {
            //       if(response.data.message == 'success'){
            //         alert('신청 성공');
            //       }
            //       else{
            //         alert('신청 실패');
            //       }
            //     })
            //     .catch(() => {
            //     });
        },
    },
};
</script>
<style scoped>
@import '../../assets/css/fundingdetail.css';
</style>
