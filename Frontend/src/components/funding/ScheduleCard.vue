<template>
  <v-dialog v-model="modifyDialog" persistent max-width="600">
    <template v-slot:activator="{ on, attrs }">
      <v-card class="width-350 mx-2" outlined>
        <v-list-item three-line class="scheduleList" v-bind="attrs" v-on="on">
          <v-list-item-content>
            <v-list-item-title class="headline mb-1">
              {{ sprint.sprint_name }}
            </v-list-item-title>
            <div class="overline mb-4">
              {{ sprint.sprint_subject }}
            </div>
            <v-list-item-subtitle>
              기간: {{ sprint.sprint_start_date }} ~
              {{ sprint.sprint_end_date }}
            </v-list-item-subtitle>
          </v-list-item-content>
          <v-card-actions>
            <v-btn small outlined rounded text @click="deleteSprint(sprint)">
              삭제하기
            </v-btn>
          </v-card-actions>
        </v-list-item>
      </v-card>
    </template>

    <v-card class="px-5">
      <v-card-title class="headline mb-1">
        {{ sprint.sprint_name }}
      </v-card-title>
      <v-card-text>
        Name :
        <v-text-field v-model="modSprint.sprint_name"></v-text-field>
        Subject :
        <v-text-field v-model="modSprint.sprint_subject"></v-text-field>
        startDate :
        <v-text-field
          v-model="modSprint.sprint_start_date"
          type="date"
        ></v-text-field>
        endDate :
        <v-text-field
          v-model="modSprint.sprint_end_date"
          type="date"
        ></v-text-field>
        Description :
        <v-textarea v-model="modSprint.sprint_description"></v-textarea>
      </v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="green darken-1" text @click="modifyDialog = false">
          취소
        </v-btn>

        <v-btn color="green darken-1" text @click="modifySprint">
          수정
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
import http from '@/util/http-common';
export default {
  name: 'ScheduleCard',
  data() {
    return {
      items: ['할 일', '진행 중', '완료'],
      modifyDialog: false,
      modSprint: {
        sprint_name: '',
        sprint_description: '',
        sprint_subject: '',
        sprint_start_date: '',
        sprint_end_date: '',
      },
    };
  },
  created() {
    this.modSprint = this.sprint;
  },
  props: {
    index: Number,
    sprint: Object,
  },
  methods: {
    deleteSprint(sprint) {
      http.delete(`/sprint/delete/${sprint.sprint_num}`, {
        sprint_num: sprint.sprint_num,
      });
      location.href = '/schedule';
    },
    modifySprint() {
      http
        .put(`sprint/modifycontent`, this.modSprint)
        .then(() => {
          this.modifyDialog = false;
          alert('변경 완료!');
        })
        .catch(() => {
          alert('변경 실패!');
        });
    },
    // showCardDetail() {
    //     console.log("dsfadf");
    // },
  },
};
</script>

<style></style>
