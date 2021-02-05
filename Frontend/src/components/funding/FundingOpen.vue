<template>
  <div class="width-1000 centerContent">
    <h1>펀딩오픈</h1>
    <div class="width-1000 centerContent pt-3">
      <h2>카테고리</h2>
      <ul class="pt-5">
        <v-row>
          <div
            class="fundingCategory fundingMargin"
            v-for="(category, index) in categories"
            :key="index"
          >
            <li @click="categorySelect(index)">
              <img class="width-40 height-40 fundingMargin" :src="category.img" />
              <p>{{ category.name }}</p>
            </li>
          </div>
        </v-row>
      </ul>
    </div>
    <div class="cardBox">
      <form class="width-1000">
        <v-row class="width-1000">
          <v-spacer></v-spacer>
          <v-text-field
            label="제목"
            type="text"
            class="width-700"
            v-model="project.project_name"
          ></v-text-field>
          <v-spacer></v-spacer>
        </v-row>
        <v-row class="width-1000">
          <v-spacer></v-spacer>
          <v-text-field
            label="인원"
            type="number"
            class="width-700"
            v-model="project.participants"
          ></v-text-field>
          <v-spacer></v-spacer>
        </v-row>
        <v-row class="width-1000">
          <v-spacer></v-spacer>
          <v-radio-group v-model="project.mentor_chk" row>
            멘토
            <v-radio value="필요있음" label="필요있음"></v-radio>
            <v-radio value="필요없음" label="필요없음"></v-radio>
          </v-radio-group>
          <v-spacer></v-spacer>
        </v-row>
        <v-row class="width-1000">
          <v-spacer></v-spacer>
          <v-text-field
            label="펀딩액"
            type="text"
            class="width-130"
            v-model="project.funding_cost"
          ></v-text-field>
          <v-spacer></v-spacer>
          <v-text-field
            label="모집기간"
            type="date"
            class="width-100"
            v-model="project.deadline"
          ></v-text-field>
          <v-spacer></v-spacer>
        </v-row>
        <v-row class="width-1000">
          <v-spacer></v-spacer>
          <v-text-field
            label="프로젝트 Start"
            type="date"
            class="width-130"
            v-model="project.start_date"
          ></v-text-field>
          <v-spacer></v-spacer>
          <v-text-field
            label="프로젝트 End"
            type="date"
            class="width-130"
            v-model="project.end_date"
          ></v-text-field>
          <v-spacer></v-spacer>
        </v-row>
      </form>
    </div>
    <div class="width-700 fundingContent">
      <v-spacer></v-spacer>
      <h3>세부내용</h3>
      <v-spacer></v-spacer>
      <div class="py-10 fundingMargin">
        <input type="text" class="fundingBox" v-model="project.description" />
      </div>
      <v-spacer></v-spacer>
    </div>
    <div class="height-50">
      <v-row>
        <v-spacer></v-spacer>
        <v-btn color="primary" text class="width-100">
          취소하기
        </v-btn>
        <v-spacer></v-spacer>
        <v-btn color="primary" text @click="openFunding">
          오픈하기
        </v-btn>
        <v-spacer></v-spacer>
      </v-row>
    </div>
  </div>
</template>

<script>
import http from '@/util/http-common';
import { mapState } from 'vuex';
export default {
  computed: {
    ...mapState(['memberInfo', 'isLogin']),
  },
  data() {
    return {
      project: {
        category: '',
        project_name: '',
        participants: '',
        mentor_chk: '',
        funding_cost: '',
        deadline: '',
        start_date: '',
        end_date: '',
        description: '',
        leader: '',
      },
      categories: [
        {
          img: require('@/assets/category/design.png'),
          name: '디자인',
        },
        {
          img: require('@/assets/category/design.png'),
          name: 'IT·프로그래밍',
        },
        {
          img: require('@/assets/category/design.png'),
          name: '번역·통역',
        },
        {
          img: require('@/assets/category/video.png'),
          name: '영상·사진·음향',
        },
        {
          img: require('@/assets/category/777.png'),
          name: '운세·상담',
        },
        {
          img: require('@/assets/category/marketing.png'),
          name: '마케팅',
        },
      ],
    };
  },
  methods: {
    categorySelect(index) {
      this.project.category = this.categories[index].name;
    },
    openFunding() {
      this.project.leader = this.memberInfo.id;
      let data = this.project;
      http
        .post('/project/create', data)
        .then((response) => {
          if (response.data.message == 'success') {
            alert('추가 성공!');
            this.$router.push({ name: 'Main' });
          } else {
            console.log(this.project);
            alert('추가 실패!');
          }
        })
        .catch(() => {
          alert('추가 실패!');
        });
    },
  },
};
</script>

<style></style>
