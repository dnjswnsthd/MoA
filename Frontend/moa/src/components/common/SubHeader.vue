<template>
    <header >
        <v-container class="col-8">
            <v-row v-if="isLogin !== false">
                <div>
                  <router-link to="/">
                    <img src="@/assets/images/main/logo(Bg).png" alt="로고" style="width: 150px; height: 60px;">
                  </router-link>
                  </div>
                <v-spacer></v-spacer>                
                    <li class="width-120 centerText height-70">
                    <router-link to="/FundingOpen">펀딩 오픈</router-link>
                    </li>
                    <li class="width-120 centerText height-70">
                    <router-link to="/MyPage">My Page</router-link>
                    </li>
                    <li class="width-120 centerText height-70 logoutBtn" @click.prevent="onClickLogout">
                    LOGOUT
                    </li>
            </v-row>
            <v-row v-else>
                <div>
                    <router-link to="/">
                    <img
                        src="@/assets/images/main/logo(Bg).png"
                        alt="로고"
                        style="width: 150px; height: 70px;"
                    />
                    </router-link>
                </div>
                <v-spacer></v-spacer>
                <li class="width-120 centerText height-70">
                    <router-link to="/join">회원가입</router-link>
                </li> 
                <li class="width-120 centerText height-70">
                    <router-link to="/login">로그인</router-link>                         
                </li>
            </v-row>               
        </v-container>

        
    </header>
</template>

<script>
import { mapState } from 'vuex';

export default {
  computed: {
    ...mapState(['memberInfo', 'isLogin']),
  },
  created() {
    if (localStorage.getItem('access-token') != null) this.$store.commit('setIsLogined', true);
  },
  methods: {
    onClickLogout() {
      this.$store
        .dispatch('LOGOUT')
        .then(() => {
          // this.$router.push({ name: "" });

          if (this.$route.path !== '/') this.$router.replace('/');
        })
        .catch(() => {
          console.log('로그아웃 문제!!!');
        });
    },
  },
};
</script>

<style>
header {
  border-bottom: 1px solid black;
}
header .container .row li {
  line-height: 70px;
}
header .container .row li a {
  color: black;
}
</style>
