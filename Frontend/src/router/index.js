import Vue from 'vue';
import VueRouter from 'vue-router';

import Main from '@/components/views/Main.vue';
import SearchMain from '@/components/views/SearchMain.vue';
import MainHeader from '@/components/common/MainHeader.vue';
import SubHeader from '@/components/common/SubHeader.vue';

import Login from '@/components/member/Login.vue';
import Join from '@/components/member/Join.vue';
import FindPassword from '@/components/member/FindPassword.vue';
import MyPage from '@/components/member/MyPage.vue';

import FundingOpen from '@/components/funding/FundingOpen.vue';
import FundingDetail from '@/components/funding/FundingDetail.vue';
import FundingList from '@/components/funding/FundingList.vue';
import Schedule from '@/components/funding/Schedule.vue';
import Evaluate from '@/components/evaluation/Evaluate.vue';

// import Sub from "@/views/Sub.vue";

Vue.use(VueRouter);

export default new VueRouter({
    mode: 'history',
    // components:{default : , header: } >> default는 기본 컴포넌트, 컴포넌트를 들고갈 시 header라는 이름으로 들고갈 컴포넌트 지정.
    // 메인화면이동시 메인헤더를 들고가고, 다른화면이동 시 서브헤더를 들고감.
    routes: [
        {
            // 메인화면으로 이동
            path: '/',
            name: 'Main',
            components: { default: Main, header: MainHeader },
        },
        {
            // 검색 후 화면으로 이동
            path: '/searchmain/:topic',
            name: 'SearchMain',
            components: { default: SearchMain, header: MainHeader },
        },
        {
            // 로그인화면으로 이동
            path: '/login',
            name: 'Login',
            components: { default: Login, header: SubHeader },
        },
        {
            // 회원가입 화면으로 이동
            path: '/join',
            name: 'Join',
            components: { default: Join, header: SubHeader },
        },
        {
            // 비밀번호찾기 화면으로 이동
            path: '/findpassword',
            name: 'FindPassword',
            components: { default: FindPassword, header: SubHeader },
        },
        {
            // 마이페이지 화면으로 이동
            path: '/mypage',
            name: 'MyPage',
            components: { default: MyPage, header: SubHeader },
        },
        {
            // 펀딩오픈 화면으로 이동
            path: '/fundingopen',
            name: 'FundingOpen',
            components: { default: FundingOpen, header: SubHeader },
        },
        {
            // 펀딩목록 화면으로 이동
            path: '/fundinglist',
            name: 'FundingList',
            components: { default: FundingList, header: SubHeader },
        },
        {
            // 펀딩상세화면으로 이동, 프로젝트번호를 들고 감.
            path: '/fundingdetail/:pn',
            name: 'FundingDetail',
            components: { default: FundingDetail, header: SubHeader },
        },
        {
            // 펀딩상세화면으로 이동, 프로젝트번호를 들고 감.
            path: '/schedule/:pn',
            name: 'Schedule',
            components: { default: Schedule, header: SubHeader },
        },
        {
            // 마이페이지에서 평가화면으로 이동.
            path: '/evaluate/:pn',
            name: 'Evaluate',
            components: { default: Evaluate, header: SubHeader },
        },
    ],
});
