<template>
    <div class="cardBox">
        <div>
            <div>
                <v-spacer></v-spacer>
                <img src="@/assets/images/main/logo(Bg).png" alt="로고" class="memberFormLogo">
                <v-spacer></v-spacer>
                <h2 class="centerText loginTitle">로그인</h2>
                <!-- <div class="lineBox"></div> -->
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
                        @keyup.enter="confirm"></v-text-field>

                    <v-text-field  
                        id="memberPw"
                        v-model="member.pw"
                        required 
                        label="PW" 
                        type="password" 
                        class="col-12"
                        @keyup.enter="confirm"></v-text-field>  
                </form>
                <div class="fullWidth">
                    <button class="loginBtn col-4" @click="confirm">Login</button>
                </div>
            </div>
        

        <v-card-actions class="cardActionBox">
        <v-spacer></v-spacer>
        
            <v-btn
                color="primary"
                text
            >
            <router-link to="/findpassword">
            비밀번호 찾기
            </router-link>
            </v-btn>
            <v-btn
                color="primary"
                text
            >
            <router-link to="/join">
            회원가입
            </router-link>
            </v-btn>
            </v-card-actions>
        </div>
        </div>
</template>
<script>
import { login } from '@/api/user.js';
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
      localStorage.setItem('access-token', '');
      console.log('클릭');
      login(
        this.member,
        (response) => {
          console.log(response);
          if (response.data.message === 'success') {
              console.log('여기까지옴2');
            let token = response.data['access-token'];
            this.$store.commit('setIsLogined', true);
            // this.$store.commit('setMemberInfo', response.data.memberInfo);
            localStorage.setItem('access-token', token);
            this.$store.dispatch('GET_MEMBER_INFO', token);
            this.$router.push('/');
          } else {
            this.isLoginError = true;
          alert('아이디 또는 비밀번호가 일치하지 않습니다.');
          }
        },
        (error) => {
          console.error(error);
        }
      );
    },
    
  },
}
</script>

<style scoped>
.cardBox {
  margin: 6.5rem auto;
}
.loginTitle{
    margin:20px auto;
}
</style>