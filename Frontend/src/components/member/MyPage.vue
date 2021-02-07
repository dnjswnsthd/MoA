<template>
  <div class="cardBox">
    <h1 class="width-1000 centerText">내 정보</h1>
    <p class="myPoint rightText">포인트: {{ memberInfo.point }}</p>

    <form class="width-1000">
      <v-row class="width-1000">
        <v-spacer></v-spacer>
        <v-text-field
          label="ID"
          type="text"
          class="width-700"
          v-model="memberInfo.id"
          readonly
        ></v-text-field>
        <v-spacer></v-spacer>
      </v-row>

      <v-row class="width-1000">
        <v-spacer></v-spacer>
        <v-text-field
          label="NAME"
          type="text"
          class="width-250"
          v-model="memberInfo.name"
          readonly
        ></v-text-field>
        <v-spacer></v-spacer>
        <v-text-field
          label="AGE"
          type="text"
          class="width-250"
          v-model="memberInfo.age"
          readonly
        ></v-text-field>
        <v-spacer></v-spacer>
      </v-row>

      <v-row class="width-1000">
        <v-spacer></v-spacer>
        <v-text-field
          label="MAJOR"
          type="text"
          class="width-700"
          v-model="memberInfo.major"
        ></v-text-field>
        <v-spacer></v-spacer>
      </v-row>
      <v-row class="width-1000">
        <v-spacer></v-spacer>
        <v-text-field
          label="favorite 1"
          type="text"
          class="width-100"
          v-model="memberInfo.favorite_1"
        ></v-text-field>
        <v-spacer></v-spacer>
        <v-text-field
          label="favorite 2"
          type="text"
          class="width-100"
          v-model="memberInfo.favorite_2"
        ></v-text-field>
        <v-spacer></v-spacer>
        <v-text-field
          label="favorite 3"
          type="text"
          class="width-100"
          v-model="memberInfo.favorite_3"
        ></v-text-field>
        <v-spacer></v-spacer>
      </v-row>
    </form>

    <div>
      <v-row class="btnBox">
        <v-spacer></v-spacer>
        <v-btn color="primary" text>
          <router-link to="/"> 비밀번호 변경 </router-link>
        </v-btn>
        <v-spacer></v-spacer>

        <!-- <v-btn
                color="primary"
                text
                >
                내 정보 변경
                </v-btn> -->
        <v-dialog v-model="modifyDialog" persistent max-width="290">
          <template v-slot:activator="{ on, attrs }">
            <v-btn color="primary" text v-bind="attrs" v-on="on">
              내 정보 변경
            </v-btn>
          </template>
          <v-card>
            <v-card-title class="headline"> 비밀번호 확인 </v-card-title>
            <v-card-text>
              <v-text-field
                label="Password"
                type="password"
                class="width-700"
                v-model="password"
              ></v-text-field>
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="green darken-1" text @click="modifyDialog = false">
                취소
              </v-btn>
              <v-btn color="green darken-1" text @click="modifyMember">
                변경
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
        <v-spacer></v-spacer>
        <v-dialog v-model="deleteDialog" persistent max-width="290">
          <template v-slot:activator="{ on, attrs }">
            <v-btn color="primary" text v-bind="attrs" v-on="on">
              탈퇴 하기
            </v-btn>
          </template>
          <v-card>
            <v-card-title class="headline"> 비밀번호 확인 </v-card-title>
            <v-card-text>
              <v-text-field
                label="Password"
                type="password"
                class="width-700"
                v-model="password"
              ></v-text-field>
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="green darken-1" text @click="deleteDialog = false">
                취소
              </v-btn>
              <v-btn color="green darken-1" text @click="deleteMember">
                탈퇴
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
        <v-spacer></v-spacer>
      </v-row>
    </div>

    <div class="projectBox">
      <v-tabs color="deep-purple accent-4" left>
        <v-tab>관심중인 펀딩</v-tab>
        <v-tab>진행중인 펀딩</v-tab>
        <v-tab>종료된 펀딩</v-tab>
        <v-tab>참여 대기 중 펀딩</v-tab>

        <v-tab-item v-for="n in 4" :key="n">
          <v-container fluid>
            <v-row v-if="n == 1">
              <v-col v-for="(interest, index) in interesting" :key="index" cols="12" md="4">
                <img
                  src="@/assets/images/funding/fox.jpg"
                  class="fullWidth height-200"
                  alt="예시"
                />

                <div>
                  <v-row>
                    <p class="shorthand col-7">프로젝트 이름 : {{ interest.project_name }}</p>
                    <p class="col-5">상태보기</p>
                  </v-row>
                </div>
              </v-col>
            </v-row>

            <v-row v-if="n == 2">
              <v-col v-for="(proceed, index) in proceeding" :key="index" cols="12" md="4">
                <img
                  src="@/assets/images/funding/fox.jpg"
                  class="fullWidth height-200"
                  alt="예시"
                />

                <div>
                  <v-row>
                    <p class="shorthand col-7">프로젝트 이름 : {{ proceed.project_name }}</p>
                    <p class="col-5">상태보기</p>
                  </v-row>
                </div>
              </v-col>
            </v-row>

            <v-row v-if="n == 3">
              <v-col v-for="(completed, index) in complete" :key="index" cols="12" md="4">
                <img
                  src="@/assets/images/funding/fox.jpg"
                  class="fullWidth height-200"
                  alt="예시"
                />

                <div>
                  <v-row>
                    <p class="shorthand col-7">프로젝트 이름 : {{ completed.project_name }}</p>
                    <p class="col-5">상태보기</p>
                  </v-row>
                </div>
              </v-col>
            </v-row>

            <v-row v-if="n == 4">
              <v-col v-for="(wait, index) in waiting" :key="index" cols="12" md="4">
                <img
                  src="@/assets/images/funding/fox.jpg"
                  class="fullWidth height-200"
                  alt="예시"
                />

                <div>
                  <v-row>
                    <p class="shorthand col-7">프로젝트 이름 : {{ wait.project_name }}</p>
                    <p class="col-5">상태보기</p>
                  </v-row>
                </div>
              </v-col>
            </v-row>
          </v-container>
        </v-tab-item>
      </v-tabs>
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
      modifyDialog: false,
      deleteDialog: false,
      password: '',
      proceeding: {},
      categories: [
        {
          img: require('@/assets/category/design.png'),
          afterImg: require('@/assets/category/design(c).png'),
          name: '디자인',
          value: 'design',
        },
        {
          img: require('@/assets/category/computer.png'),
          afterImg: require('@/assets/category/computer(c).png'),
          name: 'IT·프로그래밍',
          value: 'computer',
        },
        {
          img: require('@/assets/category/translate.png'),
          afterImg: require('@/assets/category/translate(c).png'),
          name: '번역·통역',
          value: 'translate',
        },
        {
          img: require('@/assets/category/video.png'),
          afterImg: require('@/assets/category/video(c).png'),
          name: '영상·사진·음향',
          value: 'video',
        },
        {
          img: require('@/assets/category/lucky.png'),
          afterImg: require('@/assets/category/lucky(c).png'),
          name: '운세·상담',
          value: 'lucky',
        },
        {
          img: require('@/assets/category/marketing.png'),
          afterImg: require('@/assets/category/marketing(c).png'),
          name: '마케팅',
          value: 'marketing',
        },
      ],
      complete: {},
      interesting: {},
      waiting: {},
    };
  },
  created() {
    http
      .get(`/member/mypage/${this.memberInfo.id}`)
      .then(({ data }) => {
        this.member = data;
      })
      .catch(() => {
        alert('에러가 발생하였습니다.');
      });

    http
      .get(`/project/proceeding/${this.memberInfo.id}`)
      .then((response) => {
        if (response.data.message == 'success') {
          this.proceeding = response.data.projectInfo;
        } else {
          alert('진행중플젝 가져오기 실패!');
        }
      })
      .catch(() => {
        alert('에러발생!');
      });

    http
      .get(`/project/complete/${this.memberInfo.id}`)
      .then((response) => {
        if (response.data.message == 'success') {
          this.complete = response.data.completeProjectInfo;
        } else {
          alert('진행중플젝 가져오기 실패!');
        }
      })
      .catch(() => {
        alert('에러발생!');
      });
    http
      .get(`/project/interesting/${this.memberInfo.id}`)
      .then((response) => {
        if (response.data.message == 'success') {
          this.interesting = response.data.interestingProjectInfo;
        } else {
          alert('진행중플젝 가져오기 실패!');
        }
      })
      .catch(() => {
        alert('에러발생!');
      });

    http
      .get(`/project/waiting/${this.memberInfo.id}`)
      .then((response) => {
        if (response.data.message == 'success') {
          this.waiting = response.data.waitingProjectInfo;
        } else {
          alert('진행중플젝 가져오기 실패!');
        }
      })
      .catch(() => {
        alert('에러발생!');
      });
  },
  methods: {
    movePage(n) {
      if (n == 3) this.$router.replace('/evaluate');
    },
    deleteMember() {
      this.deleteDialog = false;
      http
        .post('/member/pwcheck', {
          id: this.memberInfo.id,
          pw: this.password,
        })
        .then((response) => {
          if (response.data.message == 'success') {
            http
              .delete(`/member/delete/${this.memberInfo.id}`)
              .then(() => {
                console.log('@@@');
                this.$store
                  .dispatch('LOGOUT')
                  .then(() => {
                    // this.$router.push({ name: "" });

                    alert('삭제 성공!');
                    if (this.$route.path !== '/') this.$router.replace('/');
                  })
                  .catch(() => {
                    console.log('로그아웃 문제!!!');
                  });
              })
              .catch(() => {
                alert('삭제 실패!');
              });
          }
        })
        .catch(() => {
          alert('에러 발생!');
        });
    },

    modifyMember() {
      this.modifyDialog = false;
      console.log('1');
      http
        .post('/member/pwcheck', {
          id: this.memberInfo.id,
          pw: this.password,
        })
        .then((response) => {
          console.log('2');
          console.log(response);
          if (response.data.message == 'success') {
            console.log('4');
            http
              .put('/member/update', {
                id: this.memberInfo.id,
                name: this.memberInfo.name,
                age: this.memberInfo.age,
                major: this.memberInfo.major,
                phone: this.memberInfo.phone,
                favorite_1: this.memberInfo.favorite_1,
                favorite_2: this.memberInfo.favorite_2,
                favorite_3: this.memberInfo.favorite_3,
                introduce: this.memberInfo.introduce,
              })
              .then(() => {
                alert('수정 성공!');
              })
              .catch(() => {
                alert('수정 실패!');
              });
          }
        })
        .catch(() => {
          console.log('3');
          alert('에러 발생!');
        });
    },
    // checkCategory(category) {
    //   if (category == '마케팅') {
    //     img: require('@/assets/category/design.png');
    //   }
    // },
  },
};
</script>

<style scoped>
.cardBox {
  width: 1000px;
  margin: 50px auto;
}
p {
  padding: 10px 0 0 10px;
}
</style>
