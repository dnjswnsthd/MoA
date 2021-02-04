<template>
  <header class="backgroundImg">
    <v-container class="col-8">
      <v-row v-if="isLogin !== false">
        <div>
          <router-link to="/">
            <img src="@/assets/images/main/logo.png" alt="로고" style="width: 150px; height: 60px;" />          
          </router-link>
        </div>
        <v-spacer></v-spacer>
        <li class="width-120 centerText height-70">
          <router-link to="/fundingopen" style="color:#fff;" class="cookie">펀딩 오픈</router-link>
        </li>
        <li class="width-120 centerText height-70">
          <router-link to="/mypage" style="color:#fff;" class="cookie">My Page</router-link>
        </li>
        <li class="width-120 centerText height-70 logoutBtn cookie" @click.prevent="onClickLogout" style="color:#fff;">
          LOGOUT
        </li>
      </v-row>
      <v-row v-else>
        <div>
          <router-link to="/">
            <img
              src="@/assets/images/main/logo.png"
              alt="로고"
              style="width: 150px; height: 70px;"
            />
          </router-link>
        </div>
        <v-spacer></v-spacer>
        <li class="width-120 centerText height-70">
          <router-link to="/join" style="color:#fff;" class="cookie">회원가입</router-link>
        </li>
        <li class="width-120 centerText height-70">
          <router-link to="/login" style="color:#fff;" class="cookie">로그인</router-link>
        </li>
      </v-row>
      <div class="headerPadding">
        <div>
          <v-row>
            <div class="col-6">
              <h1 class="headerH1 cookie">
                "최고의 팀"
                <br />꾸려보고 싶은가요?
              </h1>
            </div>

            <div class="searchBar col-6">
              <input type="text" class="col-11" />
              <button class="col-1 searchIcon"><v-icon>mdi-magnify</v-icon></button>
            </div>
          </v-row>
        </div>
      </div>
      <div class="mt-10" style="opacity:0.5;">
        <img src="@/assets/images/main/logo.png" alt="로고">
      </div>
    </v-container>
    <div class="categoryMenu col-8">
        <v-row>
          <v-spacer></v-spacer>
          <div class="col-2 mainCategory">
            <img class="width-40 height-40 centerContent" src="@/assets/category/design.png" alt="디자인">
            <router-link to="/fundinglist">디자인</router-link>
          </div>
          <div class="col-2 mainCategory">
            <img class="width-40 height-40 centerContent" src="@/assets/category/computer.png" alt="디자인">
            <router-link to="/fundinglist">IT·프로그래밍</router-link>
          </div>
          <div class="col-2 mainCategory">
            <img class="width-40 height-40 centerContent" src="@/assets/category/translate.png" alt="디자인">
            <router-link to="/fundinglist">번역·통역</router-link>
          </div>
          <div class="col-2 mainCategory">
            <img class="width-40 height-40 centerContent" src="@/assets/category/video.png" alt="디자인">
            <router-link to="/fundinglist">영상·사진·음향</router-link>
          </div>
          <div class="col-2 mainCategory">
            <img class="width-40 height-40 centerContent" src="@/assets/category/777.png" alt="디자인">
            <router-link to="/fundinglist">운세·상담</router-link>
          </div>
          <div class="col-2 mainCategory">
            <img class="width-40 height-40 centerContent" src="@/assets/category/marketing.png" alt="디자인">
            <router-link to="/fundinglist">마케팅</router-link>
          </div>
          <v-spacer></v-spacer>
        </v-row>
    </div>
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
          if (this.$route.path !== '/') {
            this.$router.replace('/');
          }
        })
        .catch(() => {
          console.log('로그아웃 문제!!!');
        });
    },
  },
};
</script>

<style>

.cookie{
  font-family: 'CookieRunOTF-Bold';
}
.button {
  background-color: white;
  color: black;
}
.searchBar {
  box-sizing: border-box;
  border-radius: 1.3rem;
  border: white 3px solid;
  background-color: rgba(255, 255, 255, 0.5);
  /* background-color:rgba(255,255,255,1); */
  height: 80px;
  margin: 20px auto;
}
.searchBar input {
  padding: 0;
  height: 100%;
  margin: 0 auto;
  color: #000;
  font-weight: bold;
  font-size: 25px;
  display: inline-block;
}
.searchBar .searchIcon {
  width: 100%;
  height: 100%;
  padding: 0;
}
.searchBar .searchIcon i {
  color: white;
}
.headerH1 {
  color: white;
  font-family: 'CookieRunOTF-BOLD';
}
.backgroundImg {
  background-image: url('../../assets/images/main/bg_6.jpg');
  background-attachment: fixed;
  background-position:center;
  background-size:cover;
}
/* header .container .row .row div div .v-icon {
        color : white;
    } */

.mainCategory {
  text-align: center;
  width: 100px;
  height: 80px;
}

.headerPadding {
  padding-top: 50px;
}

.categoryMenu{
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
  word-break: break-all;
  background-color: white;
  border-radius: 50px 50px 0px 0px;
  line-height: 1.8;
  margin: 0 auto;
  margin-top:2  00px;

}
.logoutBtn{
    cursor:pointer;
}

</style>
