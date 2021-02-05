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
        <li class="width-120 centerText height-70" @mouseover="selectOpButton = true" @mouseout="selectOpButton = false">
          <router-link v-if="selectOpButton==true" to="/fundingopen" style="color:#ffce55;" class="cookie">펀딩 오픈</router-link>
          <router-link v-else to="/fundingopen" style="color:#fff;" class="cookie">펀딩 오픈</router-link>
        </li>
        <li class="width-120 centerText height-70" @mouseover="selectMyButton = true" @mouseout="selectMyButton = false">
          <router-link v-if="selectMyButton==true" to="/mypage" style="color:#ffce55;" class="cookie">My Page</router-link>
          <router-link v-else to="/mypage" style="color:#fff;" class="cookie">My Page</router-link>
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
        <li class="width-120 centerText height-70" @mouseover="selectSgButton = true" @mouseout="selectSgButton = false">
          <router-link v-if="selectSgButton==true" to="/join" style="color:#ffce55;" class="cookie">회원가입</router-link>
          <router-link v-else to="/join" style="color:#fff;" class="cookie">회원가입</router-link>
        </li>
        <li class="width-120 centerText height-70" @mouseover="selectLgButton = true" @mouseout="selectLgButton = false">
          <router-link v-if="selectLgButton==true" to="/login" style="color:#ffce55;" class="cookie">로그인</router-link>
          <router-link v-else to="/login" style="color:#fff;" class="cookie">로그인</router-link>
        </li>
      </v-row>
      <div class="headerPadding">
        <div>
          <v-row>
            <div class="col-6">
              <h1 class="headerH1 cookie pl-10">
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
      <div class="bgLogo" style="opacity:0.5;">
        <img src="@/assets/images/main/logo.png" alt="로고">
      </div>
    </v-container>
    <div class="categoryMenu col-8">
        <v-row>
          <v-spacer></v-spacer>
          <div class="col-2 mainCategory" @mouseover="showDgImg = afterDgImg" @mouseout="showDgImg= beforeDgImg">
            <router-link to="/fundinglist"><img class="width-40 height-40 centerContent " :src="showDgImg" alt="디자인"></router-link>
            <router-link to="/fundinglist">디자인</router-link>
          </div>
          <div class="col-2 mainCategory" @mouseover="showItImg = afterItImg" @mouseout="showItImg= beforeItImg">
            <router-link to="/fundinglist"><img class="width-40 height-40 centerContent" :src="showItImg" alt="디자인"></router-link>
            <router-link to="/fundinglist">IT·프로그래밍</router-link>
          </div>
          <div class="col-2 mainCategory" @mouseover="showTrImg = afterTrImg" @mouseout="showTrImg= beforeTrImg">
            <router-link to="/fundinglist"><img class="width-40 height-40 centerContent" :src="showTrImg" alt="디자인"></router-link>
            <router-link to="/fundinglist">번역·통역</router-link>
          </div>
          <div class="col-2 mainCategory" @mouseover="showVdImg = afterVdImg" @mouseout="showVdImg= beforeVdImg">
           <router-link to="/fundinglist"><img class="width-40 height-40 centerContent" :src="showVdImg" alt="디자인"></router-link>
            <router-link to="/fundinglist">영상·사진·음향</router-link>
          </div>
          <div class="col-2 mainCategory" @mouseover="showCsImg = afterCsImg" @mouseout="showCsImg= beforeCsImg">
            <router-link to="/fundinglist"><img class="width-40 height-40 centerContent" :src="showCsImg" alt="디자인"></router-link>
            <router-link to="/fundinglist">운세·상담</router-link>
          </div>
          <div class="col-2 mainCategory" @mouseover="showMkmg = afterMkImg" @mouseout="showMkmg= beforeMkImg">
            <router-link to="/fundinglist"><img class="width-40 height-40 centerContent" :src="showMkmg" alt="디자인"></router-link>
            <router-link to="/fundinglist">마케팅</router-link>
          </div>
          <v-spacer></v-spacer>
        </v-row>
    </div>
    <div class="mal">
        <img src="../../assets/images/main/mal.png" alt="말풍선">
    </div>
    <div class="mal2">
        <img src="../../assets/images/main/mal.png" alt="말풍선">
    </div>
    <div class="mal3">
        <img src="../../assets/images/main/mal.png" alt="말풍선">
    </div>

       
  </header>
</template>

<script>
import { mapState } from 'vuex';

export default {
  computed: {
    ...mapState(['memberInfo', 'isLogin']),
  },
  data() {
    return {
      selectOpButton: false,
      selectMyButton: false,
      selectSgButton: false,
      selectLgButton: false,
      showDgImg: require('@/assets/category/design.png'),
      showItImg: require('@/assets/category/computer.png'),
      showTrImg: require('@/assets/category/translate.png'),
      showVdImg: require('@/assets/category/video.png'),
      showCsImg: require('@/assets/category/777.png'),
      showMkmg: require('@/assets/category/marketing.png'),
       beforeDgImg: [
            require('@/assets/category/design.png')
       ],
       afterDgImg: [
             require('@/assets/category/design(c).png')
       ],
       beforeItImg: [
            require('@/assets/category/computer.png')
       ],
       afterItImg: [
             require('@/assets/category/computer(c).png')
       ],
       beforeTrImg: [
            require('@/assets/category/translate.png')
       ],
       afterTrImg: [
             require('@/assets/category/translate(c).png')
       ],
       beforeVdImg: [
            require('@/assets/category/video.png')
       ],
       afterVdImg: [
             require('@/assets/category/video(c).png')
       ],
       beforeCsImg: [
            require('@/assets/category/777.png')
       ],
       afterCsImg: [
             require('@/assets/category/777(c).png')
       ],
       beforeMkImg: [
            require('@/assets/category/marketing.png')
       ],
       afterMkImg: [
             require('@/assets/category/marketing(c).png')
       ]
    }
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
  font-size:45px;
  color: white;
  font-family: 'CookieRunOTF-BOLD';
}
.backgroundImg {
  background-image: url('../../assets/images/main/bg_6.jpg');
  background-attachment: fixed;
  background-position:center;
  background-size:cover;
  position:relative;
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
  margin-top:330px;

}
.logoutBtn{
    cursor:pointer;
}

        .mal{         
            animation-name:moving;
            animation-duration:2s;
            animation-iteration-count:infinite;
            animation-direction: noraml;
            position:absolute;
            top:50%;    right:13%;
            width:50px; height:50px;
        }
        .mal img{
            width:100%;height:100%;
        }
        .mal2{
            animation-name:moving;
            animation-duration:2s;
            animation-iteration-count:infinite;
            animation-direction: noraml;
            position:absolute;
            top:45%;    left:45%;
            width:60px; height:60px;
        }
        .mal2 img{
            width:100%;height:100%;
        }
        .mal3{
            animation-name:moving;
            animation-duration:2s;
            animation-iteration-count:infinite;
            animation-direction: noraml;
            position:absolute;
            bottom:35%;    right:22%;
            width:80px; height:80px;
        }
        .mal3 img{
            width:100%;height:100%;
        }
        @keyframes moving{
            0%{transform:translateY(0px); }
            50%{transform:translateY(20px); }
            100%{transform:translateY(0px);}
        }
.bgLogo{
  position:absolute;
  top:55%; left:20%;
}


</style>
