<template>
  <div class="col-8 centerContent">
    <div>
      <h1 class="height-70">{{ project.project_name }}</h1>
      <h2 class="height-70">주제 : {{ project.category }}</h2>
      <h2 class="height-70">
        일정 : {{ project.start_date }} ~ {{ project.end_date }}
      </h2>
    </div>
    <v-row>
      <h2 class="height-50 ml-6"> Add Sprint </h2>
      <v-dialog v-model="dialog"
              persistent
              max-width="500"
              max-height="600">
         <template v-slot:activator="{ on, attrs }">
                <v-btn
                  class="mx-2"
                  dark
                  small
                  color="#AB47BC"
                  v-bind="attrs"
                  v-on="on"
                >
                  <v-icon dark>
                    mdi-plus
                  </v-icon>
                </v-btn>
          </template>
          <v-card class="px-5">
                <v-card-title class="headline mb-1">
                  어떤 일정을 추가하시겠어요?
                </v-card-title>
                <v-card-text>
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
                  <v-btn
                    class="font-weight-bold"
                    color="#845ec2"
                    text
                    @click="addSprint"
                  >
                    추가
                  </v-btn>
                </v-card-actions>
              </v-card>
      </v-dialog>
    </v-row>
    <div v-for="(sprint, i) in sprints" :key="i">
      <drop
        class="drop list width-1200"
        @drop="handleDrop(sprint, ...arguments)"
      >
        <div class="col-9">
          <v-row>
            <h2>{{ items[i] }}</h2>
          </v-row>
        </div>
        <v-sheet class="mx-auto" max-width="1250">
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
                <schedule-card :index="i" :sprint="item"></schedule-card>
              </v-slide-item>
            </drag>
          </v-slide-group>
        </v-sheet>
      </drop>
    </div>
  </div>
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
        project_num: 0,
      },
      items: ['할 일', '진행 중', '완료'],
      project: [{}],
      project_num: 0,
    };
  },
  created() {
    this.project_num = this.$route.query.pn;
    this.newTask.project_num = this.project_num;
    http
      .get(`sprint/search/${this.project_num}`)
      .then(({ data }) => {
        console.log(data.spprintList);
        data.sprintList.forEach((sprint) => {
          console.log(sprint);
          if (sprint.sprint_status == 0) this.sprints[0].push(sprint);
          else if (sprint.sprint_status == 1) this.sprints[1].push(sprint);
          else if (sprint.sprint_status == 2) this.sprints[2].push(sprint);
        });
        //location.href = '/schedule';
      })
      .catch(() => {
        //alert('에러가 발생했습니다.');
      });

    http
      .get(`project/fundingDetail/${this.project_num}`)
      .then((response) => {
        if (response.data.message == 'success') {
          this.project = response.data.project;
        } else {
          alert('정보조회실패');
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

        http
          .put(`sprint/modify`, {
            sprint_status: data.item.sprint_status,
            sprint_num: data.item.sprint_num,
          })
          .then(() => {
            alert('수정 성공!');
          })
          .catch(() => {
            alert('수정 실패!');
          });
      }
    },
    addSprint() {
      console.log(this.newTask);
      http
        .post(`sprint/add`, this.newTask)
        .then(() => {
          alert('추가 성공!');
          this.dialog = false;
          this.sprints[0].push(this.newTask);
        })
        .catch(() => {
          alert('추가 실패!');
        });
    },
  },
};
</script>

<style scoped>
.drag {
  display: inline-block;
}
.drop {
  display: inline-block;
  vertical-align: top;
  padding: 15px;
  margin-bottom: 20px;
  width: 1200px;
  height: 220px;
}
</style>
