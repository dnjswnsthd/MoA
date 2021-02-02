import Vue from "vue";
import VueRouter from "vue-router";


import Main from "@/components/views/Main.vue";
import FundingOpen from "@/components/funding/FundingOpen.vue";
import MainHeader from "@/components/common/MainHeader.vue";
import SubHeader from "@/components/common/SubHeader.vue";

import Login from "@/components/member/Login.vue";
import Join from "@/components/member/Join.vue";
import FindPassword from "@/components/member/FindPassword.vue";
import MyPage from "@/components/member/MyPage.vue";
import FundingList from "@/components/funding/FundingList.vue";

// import Sub from "@/views/Sub.vue";
Vue.use(VueRouter);

export default new VueRouter({
  mode: "history",
  routes: [
    {
      path: "/",
      name: "Main",
      components: {default: Main, header: MainHeader}
    },
    {
      path: "/login",
      name: "Login",
      components: {default: Login, header: SubHeader}
    },
    {
      path: "/join",
      name: "Join",
      components: {default: Join, header: SubHeader}
    },
    {
      path: "/findpassword",
      name: "FindPassword",
      components: {default: FindPassword, header: SubHeader}
    },
    {
      path: "/mypage",
      name: "MyPage",
      components: {default: MyPage, header: SubHeader}
    },
    {
      path: "/FundingOpen",
      name: "FundingOpen",
      components: {default: FundingOpen, header: SubHeader}
    },
    {
      path: "/FundingList",
      name: "FundingList",
      components: {default: FundingList, header: SubHeader}
    },
    
  ]
});
