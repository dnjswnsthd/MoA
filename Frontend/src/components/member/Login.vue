<template>
    <v-container class="col-lg-3 col-md-5 col-sm-7 col-12 my-10">
        <div>
            <v-spacer></v-spacer>
            <img src="@/assets/images/main/logo(Bg).png" alt="로고" class="col-12" />
            <v-spacer></v-spacer>
            <h2 class="centerText">로그인</h2>
        </div>

        <div>
            <form class="col-12 formBox">
                <v-text-field
                    id="memberId"
                    v-model="member.id"
                    required
                    label="ID"
                    type="email"
                    class="col-12"
                    @keyup.enter="confirm"
                ></v-text-field>

                <v-text-field
                    id="memberPw"
                    v-model="member.pw"
                    required
                    label="PW"
                    type="password"
                    class="col-12"
                    @keyup.enter="confirm"
                ></v-text-field>
            </form>
            <div class="col-12" @click="confirm">
                <div class="col-4 loginBtn">
                    Login
                </div>
            </div>
        </div>

        <v-card-actions>
            <v-spacer></v-spacer>

            <v-btn color="primary" text @click="movePage('FindPassword')">
                비밀번호 찾기
            </v-btn>
            <v-btn color="primary" text @click="movePage('Join')">
                회원가입
            </v-btn>
        </v-card-actions>
    </v-container>
</template>
<script>
import { login } from '@/api/user.js';
import swal from 'sweetalert';
export default {
    data() {
        return {
            member: {
                id: '',
                pw: '',
            },
            isLoginError: false,
        };
    },
    methods: {
        confirm() {
            localStorage.setItem('access-token', ''); // 로컬스토리지의 access토큰을 가져옴.
            console.log('클릭');
            // 로그인.
            login(
                this.member,
                (response) => {
                    console.log(response);
                    if (response.data.message === 'success') {
                        console.log('여기까지옴2');
                        let token = response.data['access-token'];
                        this.$store.commit('setIsLogined', true); // 로그인 여부를 체크하는 isLogin을 true로.
                        // this.$store.commit('setMemberInfo', response.data.memberInfo);
                        localStorage.setItem('access-token', token); // 가져온 token을 localStorage에 저장.
                        this.$store.dispatch('GET_MEMBER_INFO', token);
                        this.$router.push('/'); // 로그인 후 메인으로 이동.
                    } else {
                        this.isLoginError = true;
                        if (this.member.id == '') {
                            // id가 비어있다면
                            swal('아이디를 입력해 주세요!', {
                                // 메시지 알림
                                icon: 'error',
                            });
                        } else if (this.member.pw == '') {
                            // pw가 비어있다면,
                            swal('비밀번호를 입력해 주세요!', {
                                // 메시지 알림.
                                icon: 'error',
                            });
                        } else {
                            // response의 메시지가 fail이라면,
                            swal('아이디 또는 비밀번호가 일치하지 않습니다!', {
                                // 해당 아이디나 pw가 틀렸음을 알림.
                                icon: 'error',
                            });
                        }
                    }
                },
                (error) => {
                    console.error(error);
                }
            );
        },
        // 페이지 이동.
        movePage(name) {
            this.$router.push({ name: name });
        },
    },
};
</script>

<style scoped>
@import '../../assets/css/member.css';
</style>
