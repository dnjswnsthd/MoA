<template>
  <v-dialog v-model="modifyDialog" max-width="600">
    <template v-slot:activator="{ on, attrs }">
      <v-card class="width-350 mx-2" outlined style="position:relative;">
        <v-list-item three-line class="scheduleList" v-bind="attrs" v-on="on">
          <v-list-item-content>
            <v-list-item-title>
              <h1>{{ sprint.sprint_name }}</h1>
            </v-list-item-title>
            <div class="mt-2">
              <div class="ml-2 mb-1">
                {{ sprint.sprint_subject }}
              </div>
              <div class="ml-2 mb-2">담당자: {{ sprint.sprint_member }}</div>
            </div>
            <v-list-item-subtitle class="ml-2 mb-1">
              기간: {{ sprint.sprint_start_date }} ~
              {{ sprint.sprint_end_date }}
            </v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>
        <v-btn
          small
          outlined
          rounded
          text
          @click="deleteSprint(sprint)"
          style="position:absolute; right:10px; top:47px;"
        >
          삭제하기
        </v-btn>
      </v-card>
    </template>

    <v-card class="px-5">
      <v-card-title class="headline mb-1">
        {{ sprint.sprint_name }}
      </v-card-title>
      <v-card-text class="font-weight-bold">
        Name :
        <v-text-field v-model="modSprint.sprint_name"></v-text-field>
        Subject :
        <v-text-field v-model="modSprint.sprint_subject"></v-text-field>
        Member :
        <v-autocomplete
          ref="modSprint.sprint_member"
          v-model="modSprint.sprint_member"
          :items="memberName"
          label="담당자"
          required
        >
        </v-autocomplete>

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
        <v-textarea
          outlined
          v-model="modSprint.sprint_description"
        ></v-textarea>
      </v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn
          class="font-weight-bold"
          color="green darken-1"
          text
          @click="modifyDialog = false"
        >
          취소
        </v-btn>

        <v-btn
          class="font-weight-bold"
          color="green darken-1"
          text
          @click="modifySprint"
        >
          수정
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
import http from '@/util/http-common';
import swal from 'sweetalert';
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
        sprint_member: '',
      },
      project_num: 0,
      members: {},
      memberName: [],
    };
  },
  created() {
    this.modSprint = this.sprint;
    this.project_num = this.$route.query.pn;
    http
      .get(`project/memberchk/${this.project_num}`)
      .then((response) => {
        this.members = response.data.member;
        for (let i = 0; i < this.members.length; i++) {
          this.memberName[i] = this.members[i].name;
        }
      })
      .catch(() => {});
  },
  props: {
    index: Number,
    sprint: Object,
  },
  methods: {
    deleteSprint(sprint) {
      this.modifyDialog = false;
      http
        .delete(`/sprint/delete/${sprint.sprint_num}`, {
          sprint_num: sprint.sprint_num,
        })
        .then(() => {
          this.$emit('reload');
        });
    },
    modifySprint() {
      http
        .put(`sprint/modifycontent`, this.modSprint)
        .then(() => {
          this.modifyDialog = false;
          swal('수정 완료!', {
            icon: 'success',
          });
        })
        .catch(() => {
          // alert('변경 실패!');
        });
    },
  },
};
</script>

<style scoped>
.scheduleList {
  border: 2px solid #b39ddb;
}
</style>
