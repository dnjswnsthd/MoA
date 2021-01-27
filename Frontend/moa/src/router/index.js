import Vue from "vue";
import VueRouter from "vue-router";


import Main from "@/components/views/Main.vue";
import MyPage from "@/components/member/MyPage.vue";
import FundingOpen from "@/components/funding/FundingOpen.vue";
import MainHeader from "@/components/common/MainHeader.vue";
import SubHeader from "@/components/common/SubHeader.vue";



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
      path: "/MyPage",
      name: "MyPage",
      components: {default: MyPage, header: SubHeader}
    },
    {
      path: "/FundingOpen",
      name: "FundingOpen",
      components: {default: FundingOpen, header: SubHeader}
    }
    
  ]
});
