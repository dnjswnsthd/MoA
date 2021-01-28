<template>
<v-card>
    <div>
        <v-spacer></v-spacer>
        <img src="@/assets/images/main/logo(Bg).png" alt="로고" class="findPasswordImg">
        <v-spacer></v-spacer>
        <h2 class="centerText">비밀번호 찾기</h2>
        <div class="lineBox"></div>
    </div>
    
        <!-- <v-card-title class="headline grey lighten-2 titleText">
            비밀번호 찾기
        </v-card-title> -->
    <div class="findPasswordWidth">
        <v-row>
        <form class="col-9 formBox">
            <v-row>
                <v-text-field label="ID" type="text" class="idbox"></v-text-field>
                <button class="idchkbox">체크</button>
            </v-row>
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
            >
            <router-link to="/join">
            회원가입
            </router-link>
            </v-btn>
        <v-spacer></v-spacer>
            <v-btn
                color="primary"
                text
            >
            <router-link to="/login">
            로그인
            </router-link>
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
    .lineBox{
        width: 350px;
        height:10px;
        background-color:#ddd;
        margin:0 auto;
        border-radius: 20px;
    }
    .idbox{
        width:60%;
    }
    .idchkbox{
        width:40%;
    }
    .formBox{
        margin:20px auto;
    }
</style>