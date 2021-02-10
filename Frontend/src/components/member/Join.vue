<template>
  <div class="cardBox">
    <div>
      <v-spacer></v-spacer>
      <img
        src="@/assets/images/main/logo(Bg).png"
        alt="로고"
        class="memberFormLogo"
      />
      <v-spacer></v-spacer>
      <h2 class="centerText loginTitle">회원가입</h2>
      <!-- <div class="lineBox"></div> -->
    </div>

    <v-tabs>
      <v-tab @click="openMentor">멘토</v-tab>
      <v-tab @click="openMentee">멘티</v-tab>
    </v-tabs>

    <form class="formBox" novalidate="true">
      <v-row>
        <v-text-field
          label="ID"
          type="email"
          v-model="member.id"
        ></v-text-field>
        <v-card-actions>
          <v-btn outlined @click="checkId">중복체크</v-btn>
        </v-card-actions>
      </v-row>
      <v-row>
        <v-text-field
          label="Password"
          type="password"
          v-model="member.pw"
        ></v-text-field>
      </v-row>
      <v-row>
        <v-text-field
          label="Password Confirm"
          type="password"
          v-model="pwChecked"
        ></v-text-field>
      </v-row>
      <v-row>
        <v-text-field
          label="Name"
          type="name"
          v-model="member.name"
        ></v-text-field>
        <v-spacer></v-spacer>
        <v-text-field
          label="Age"
          type="number"
          v-model="member.age"
        ></v-text-field>
      </v-row>

      <v-row>
        <v-text-field
          label="major"
          type="text"
          v-model="member.major"
        ></v-text-field>
        <div class="checkBtn"></div>
      </v-row>
      <v-row>
        <v-text-field
          label="phone"
          type="tel"
          v-model="member.phone"
        ></v-text-field>
        <div class="checkBtn"></div>
      </v-row>
      <v-row>
        <v-text-field
          label="선호 분야"
          dense
          solo
          class="width-50"
          v-model="member.favorite_1"
        ></v-text-field>
        <v-text-field
          label="선호 분야"
          dense
          solo
          class="width-50 mx-10"
          v-model="member.favorite_2"
        ></v-text-field>
        <v-text-field
          label="선호 분야"
          dense
          solo
          class="width-50"
          v-model="member.favorite_3"
        ></v-text-field>
      </v-row>

      <v-row>
        <v-textarea
          label="introduce"
          solo
          v-model="member.introduce"
        ></v-textarea>
      </v-row>

      <v-row v-if="mentorForm">
        <v-textarea label="career" solo v-model="member.career"></v-textarea>
      </v-row>
      <v-row v-else-if="menteeForm">
        <v-textarea label="skill" solo v-model="member.skill"></v-textarea>
      </v-row>
    </form>

    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="primary" text>
        초기화
      </v-btn>
      <v-btn color="primary" text @click="join">
        가입하기
      </v-btn>
    </v-card-actions>
  </div>
</template>
<script>
import http from '@/util/http-common';
import PV from 'password-validator';
import swal from 'sweetalert';

export default {
  data() {
    return {
      passwordSchema: new PV(),
      mentorForm: true,
      menteeForm: false,
      // selectItems: ['디자인', 'IT·프로그래밍', '번역·통역', '영상·사진·음향', '운세·상담', '마케팅'],
      member: {
        id: '',
        pw: '',
        name: '',
        age: Number,
        major: '',
        status: 1,
        phone: '',
        favorite_1: '',
        favorite_2: '',
        favorite_3: '',
        career: '',
        skill: '',
        introduce: '',
      },
      idChecked: false,
      pwChecked: '',
    };
  },
  created() {
    this.passwordSchema
      .is()
      .min(8)
      .is()
      .max(100)
      .has()
      .digits()
      .has()
      .letters();
  },
  methods: {
    validEmail(email) {
      var re = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
      return re.test(email);
    },
    openMentor() {
      (this.mentorForm = true),
        (this.menteeForm = false),
        (this.member.status = 1);
    },
    openMentee() {
      (this.mentorForm = false),
        (this.menteeForm = true),
        (this.member.status = 2);
    },
    join() {
      console.log(this.member);
      if (this.member.id == '') {
        console.log(this.member.id);
        swal('아이디를 입력해 주세요!', {
          icon: 'error',
        });
      } else if (!this.idChecked) {
        swal('아이디 중복 검사를 해 주세요!', {
          icon: 'error',
        });
      } else if (this.member.pw == '') {
        swal('비밀번호를 입력해 주세요!', {
          icon: 'error',
        });
      } else if (this.pwChecked == '') {
        swal('비밀번호 확인을 입력해 주세요!', {
          icon: 'error',
        });
      } else if (this.member.pw != this.pwChecked) {
        swal('비밀번호가 일치하지 않습니다!', {
          icon: 'error',
        });
      } else if (this.member.name == '') {
        swal('이름을 입력해 주세요!', {
          icon: 'error',
        });
      } else if (this.member.age == Number) {
        swal('나이를 입력해 주세요!', {
          icon: 'error',
        });
      } else if (this.member.major == '') {
        swal('전공을 입력해 주세요!', {
          icon: 'error',
        });
      } else if (this.member.phone == '') {
        swal('전화번호를 입력해 주세요!', {
          icon: 'error',
        });
      } else {
        let data = this.member;
        console.log(data);
        http
          .post(`/member/join`, data)
          .then(() => {
            alert('회원가입 성공!');
            location.href = '/';
          })
          .catch(() => {
            alert('회원가입 실패!');
          });
      }
    },
    checkId() {
      if (!this.validEmail(this.member.id)) {
        swal('이메일 양식을 맞춰주세요!', {
          icon: 'error',
        });
      } else {
        http
          .get(`/member/join/${this.member.id}`)
          .then((response) => {
            console.log(response);
            if (response.data.message == 'fail') {
              swal('이미 존재하는 아이디 입니다!!', {
                icon: 'error',
              });
              this.isChecked = false;
            } else if (response.data.message == 'success') {
              swal('사용가능한 아이디 입니다!!', {
                icon: 'success',
              });
              this.idChecked = true;
            }
          })
          .catch();
      }
    },
  },
};
</script>
<style scoped>
.cardBox {
  margin: 0 auto;
}
.formBox {
  padding: 0;
}
.checkBtn {
  line-height: 20px;
  padding: 8px 0 8px;
  max-width: 100%;
  min-width: 0px;
  width: 80px;
}
</style>
