<template>
    <v-container class="col-lg-3 col-md-5 col-sm-7 col-12 my-10">
        <div>
            <v-spacer></v-spacer>
            <img src="@/assets/images/main/logo(Bg).png" alt="로고" class="col-12" />
            <v-spacer></v-spacer>
            <h2 class="centerText">비밀번호 찾기</h2>
        </div>

        <div class="height-100">
            <form class="formBox">
                <v-text-field label="ID" type="text" v-model="id"></v-text-field>
            </form>
        </div>

        <div class="certificationBox">
            <v-row>
                <v-spacer></v-spacer>
                <v-btn
                    class="ml-4"
                    style="background-color:#ab47bc; color:white"
                    @click="findPassword()"
                    >이메일로 찾기</v-btn
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
import http from '@/util/http-common';
import swal from 'sweetalert';
export default {
    name: 'FindPassword',
    data() {
        return {
            id: '',
        };
    },
    methods: {
        // 비밀번호찾기
        findPassword() {
            // 입력한 id를 가지고, pw를 찾음.
            // pw는 가입한 id(이메일)로 메일을 발송.
            http.put(`/member/findpw/${this.id}`)
                .then((response) => {
                    console.log(response.data.message);
                    if (response.data.message == 'success') {
                        swal('메일로 임시비밀번호가 보내졌습니다!', {
                            icon: 'success',
                        });
                    }
                })
                .catch(() => {
                    alert('에러발생!');
                });
        },
    },
};
</script>

<style scoped>
@import '../../assets/css/findpassword.css';
</style>
