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
      <h2 class="centerText">비밀번호 찾기</h2>
    </div>

    <!-- <v-card-title class="headline grey lighten-2 titleText">
            비밀번호 찾기
        </v-card-title> -->
    <div class="height-100">
      <form class="formBox">
        <v-text-field label="ID" type="text" class="idBox"></v-text-field>
      </form>
    </div>

    <div class="certificationBox">
      <v-row>
        <v-spacer></v-spacer>
        <v-btn>이메일로 찾기</v-btn>
        <v-spacer></v-spacer>
        <v-btn>휴대폰번호로 찾기</v-btn>
        <v-spacer></v-spacer>
      </v-row>
    </div>

    <v-card-actions class="cardActionBox">
      <v-spacer></v-spacer>
      <v-btn color="primary" text>
        <router-link to="/join">
          회원가입
        </router-link>
      </v-btn>
      <v-spacer></v-spacer>
      <v-btn color="primary" text>
        <router-link to="/login">
          로그인
        </router-link>
      </v-btn>
      <v-spacer></v-spacer>
    </v-card-actions>
  </div>
</template>

<script>
export default {
  name: "FindPassword"
};
</script>

<style scoped>
.cardBox {
  margin: 10.5rem auto;
}
.certificationBox {
  margin: 20px auto;
}
</style>
