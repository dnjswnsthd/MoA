<template>
<v-card>
    <div>
        <v-spacer></v-spacer>
        <img src="@/assets/images/main/logo(Bg).png" alt="로고" class="findPasswordImg">
        <v-spacer></v-spacer>
        <h2 class="titleText grey lighten-2">비밀번호 찾기</h2>
    </div>
    
        <!-- <v-card-title class="headline grey lighten-2 titleText">
            비밀번호 찾기
        </v-card-title> -->
    <div class="findPasswordWidth">
        <v-row>
        <form class="col-9 formBox">
            <v-text-field label="ID" type="text" class="col-8"></v-text-field>
        </form>
        </v-row>
    </div>
        <div class="findPasswordWidth">
        <v-row>
        <v-spacer></v-spacer>
            <v-btn>이메일로 찾기</v-btn>
        <v-spacer></v-spacer>
            <v-btn>휴대폰번호로 찾기</v-btn>
        <v-spacer></v-spacer>
        </v-row>
        </div>
        <br>
        <v-card-actions class="findPasswordWidth">
        <v-spacer></v-spacer>
            <v-btn
                color="primary"
                text
                @click="dialogLogin = false"
            >
            회원가입
            </v-btn>
        <v-spacer></v-spacer>
            <v-btn
                color="primary"
                text
                @click="dialogLogin = false"
            >
            로그인
            </v-btn>
        <v-spacer></v-spacer>
            </v-card-actions>
        </v-card>
</template>

<script>
export default {
    name: "FindPassword"
}
</script>

<style>
    .titleText {
        text-align: center;
    }
    .findPasswordImg {
        margin: 0 auto;
        width: 450px;
        height: 100x;
    }
    form {
        width: 450px;
    }
    .findPasswordWidth {
        width: 450px;
        margin: 0 auto;
    }
</style>