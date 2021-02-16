<template>
    <v-container>
        <div class="col-8 centerContent webBox">
            <div>
                <h1 class="height-70">{{ project.project_name }}</h1>
                <h2 class="height-70">
                    Project Period : {{ project.start_date }} ~ {{ project.end_date }}
                </h2>
            </div>
            <v-row>
                <h2 class="height-50 ml-6">Add Sprint</h2>
                <v-dialog v-model="dialog" max-width="500" max-height="600">
                    <template v-slot:activator="{ on, attrs }">
                        <v-btn class="mx-2" dark small color="#AB47BC" v-bind="attrs" v-on="on">
                            <v-icon dark>
                                mdi-plus
                            </v-icon>
                        </v-btn>
                    </template>
                    <v-card class="px-5">
                        <v-card-title class="headline mb-1">
                            어떤 일정을 추가하시겠어요?
                        </v-card-title>
                        <v-card-text class="font-weight-bold">
                            <v-text-field
                                v-model="newTask.sprint_name"
                                type="text"
                                label="제목"
                                class="width-350"
                            ></v-text-field>
                            <v-text-field
                                v-model="newTask.sprint_subject"
                                type="text"
                                label="주제"
                                class="width-350"
                            ></v-text-field>
                            <v-autocomplete
                                ref="newTask.sprint_member"
                                v-model="newTask.sprint_member"
                                :items="memberName"
                                label="담당자"
                                class="width-350"
                                required
                            >
                            </v-autocomplete>
                            <v-text-field
                                v-model="newTask.sprint_start_date"
                                type="date"
                                label="시작 날짜"
                                class="width-350"
                            ></v-text-field>
                            <v-text-field
                                v-model="newTask.sprint_end_date"
                                type="date"
                                label="종료 날짜"
                                class="width-350"
                            ></v-text-field>
                            <v-textarea
                                outlined
                                v-model="newTask.sprint_description"
                                type="text"
                                label="내용"
                                class="width-350"
                            ></v-textarea>
                        </v-card-text>
                        <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn
                                class="font-weight-bold"
                                color="#845ec2"
                                text
                                @click="dialog = false"
                            >
                                취소
                            </v-btn>
                            <v-btn class="font-weight-bold" color="#845ec2" text @click="addSprint">
                                추가
                            </v-btn>
                        </v-card-actions>
                    </v-card>
                </v-dialog>
            </v-row>
            <div v-for="(sprint, i) in sprints" :key="i">
                <drop class="drop list width-1200" @drop="handleDrop(sprint, ...arguments)">
                    <div class="col-9">
                        <v-row>
                            <h2>{{ items[i] }}</h2>
                        </v-row>
                    </div>
                    <v-sheet class="mx-auto" max-width="1250">
                        <v-progress-linear
                            class="mt-2"
                            color="#CE93D8"
                            rounded
                            value="100"
                        ></v-progress-linear>
                        <v-slide-group
                            v-model="model"
                            class="pa-4"
                            active-class="success"
                            show-arrows
                        >
                            <drag
                                v-for="item in sprint"
                                class="drag"
                                :key="item"
                                :class="{ [item]: true }"
                                :transfer-data="{
                                    item: item,
                                    sprint: sprint,
                                    example: 'sprints',
                                }"
                            >
                                <v-slide-item>
                                    <schedule-card
                                        :index="i"
                                        :sprint="item"
                                        @reload="reLoad"
                                    ></schedule-card>
                                </v-slide-item>
                            </drag>
                        </v-slide-group>
                    </v-sheet>
                </drop>
            </div>
        </div>
        <div class="col-12 col-md-8 centerContent notWebBox">
            <h1>WEB에서 접속해주세요</h1>
        </div>
    </v-container>
</template>

<script>
import http from '@/util/http-common';
import ScheduleCard from './ScheduleCard.vue';
import { Drag, Drop } from 'vue-drag-drop';

export default {
    components: {
        ScheduleCard,
        Drag,
        Drop,
    },
    data() {
        return {
            dialog: false,
            sprints: [[], [], []],
            newTask: {
                sprint_name: '',
                sprint_status: 0,
                sprint_subject: '',
                sprint_description: '',
                sprint_start_date: '',
                sprint_end_date: '',
                sprint_member: '',
                project_num: 0,
            },
            items: ['할 일', '진행 중', '완료'],
            project: [{}],
            members: {},
            memberName: [],
            project_num: 0,
            webFlag: false,
        };
    },
    created() {
        this.project_num = this.$route.query.pn; // 쿼리로 가져온 pn을 저장.
        this.newTask.project_num = this.project_num;
        // pn을 가지고, 일정정보 다가져옴.
        http.get(`sprint/search/${this.project_num}`)
            .then(({ data }) => {
                console.log(data.sprintList);
                data.sprintList.forEach((sprint) => {
                    console.log(sprint);
                    if (sprint.sprint_status == 0) this.sprints[0].push(sprint);
                    else if (sprint.sprint_status == 1) this.sprints[1].push(sprint);
                    else if (sprint.sprint_status == 2) this.sprints[2].push(sprint);
                });
            })
            .catch(() => {
                alert('에러가 발생했습니다.!!');
            });
        // fundingDetail로 project정보를 가져옴
        http.get(`project/fundingDetail/${this.project_num}`)
            .then((response) => {
                if (response.data.message == 'success') {
                    this.project = response.data.project;
                } else {
                    alert('정보조회실패');
                }
            })
            .catch(() => {});

        http.get(`project/memberchk/${this.project_num}`)
            .then((response) => {
                this.members = response.data.member;
                for (let i = 0; i < this.members.length; i++) {
                    this.memberName[i] = this.members[i].name;
                }
            })
            .catch(() => {});
    },

    methods: {
        handleDrop(toList, data) {
            //toList : 이동한 곳의 데이터 , data : 이전 위치의 데이터
            const fromList = data.sprint;
            if (fromList) {
                data.item.sprint_status = this.sprints.indexOf(toList);
                toList.push(data.item);
                fromList.splice(fromList.indexOf(data.item), 1);
                toList.sort((a, b) => a > b);

                http.put(`sprint/modify`, {
                    sprint_status: data.item.sprint_status,
                    sprint_num: data.item.sprint_num,
                })
                    .then(() => {
                        // alert('수정 성공!');
                    })
                    .catch(() => {
                        // alert('수정 실패!');
                    });
            }
        },
        addSprint() {
            http.post(`sprint/add`, this.newTask)
                .then(() => {
                    this.dialog = false;
                    this.sprints[0].push({
                        sprint_name: this.newTask.sprint_name,
                        sprint_status: this.newTask.sprint_status,
                        sprint_subject: this.newTask.sprint_subject,
                        sprint_description: this.newTask.sprint_description,
                        sprint_start_date: this.newTask.sprint_start_date,
                        sprint_end_date: this.newTask.sprint_end_date,
                        sprint_member: this.newTask.sprint_member,
                        project_num: this.newTask.project_num,
                    });
                    // alert('추가 성공!');
                    this.newTask.sprint_name = '';
                    this.newTask.sprint_status = 0;
                    this.newTask.sprint_subject = '';
                    this.newTask.sprint_description = '';
                    this.newTask.sprint_start_date = '';
                    this.newTask.sprint_end_date = '';
                    this.newTask.sprint_member = '';
                    this.newTask.project_num = 0;
                    location.href = 'schedule?pn=' + this.project_num;
                })
                .catch(() => {
                    // alert('추가 실패!');
                });
        },
        reLoad() {
            location.href = 'schedule?pn=' + this.project_num;
        },
    },
};
</script>

<style scoped>
@import '../../assets/css/schedule.css';
</style>
