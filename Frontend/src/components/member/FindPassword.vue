<template>
    <v-container class="col-lg-3 col-md-5 col-sm-7 col-12 my-10">
        <div>
            <v-spacer></v-spacer>
            <img src="@/assets/images/main/logo(Bg).png" alt="로고" class="col-12" />
            <v-spacer></v-spacer>
            <h2 class="centerText">비밀번호 찾기</h2>
        </div>

        <!-- <v-card-title class="headline grey lighten-2 titleText">
            비밀번호 찾기
        </v-card-title> -->
        <div class="height-100">
            <form class="formBox">
                <v-text-field label="ID" type="text"></v-text-field>
            </form>
        </div>

        <div class="certificationBox">
            <v-row>
                <v-spacer></v-spacer>
                <v-btn
                    class="ml-4"
                    style="background-color:#ab47bc; color:white"
                    v-bind="attrs"
                    v-on="on"
                    >이메일로 찾기</v-btn
                >
                <v-spacer></v-spacer>
                <v-btn
                    class="ml-4"
                    style="background-color:#ab47bc; color:white"
                    v-bind="attrs"
                    v-on="on"
                    >휴대폰번호로 찾기</v-btn
                >
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
    </v-container>
</template>

<script>
export default {
    name: 'FindPassword',
};
</script>

<style scoped>
@import '../../assets/css/findpassword.css';
</style>
