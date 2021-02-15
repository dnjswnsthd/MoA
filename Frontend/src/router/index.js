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
    routes: [
        {
            path: '/',
            name: 'Main',
            components: { default: Main, header: MainHeader },
        },
        {
            path: '/searchmain/:topic',
            name: 'SearchMain',
            components: { default: SearchMain, header: MainHeader },
        },
        {
            path: '/login',
            name: 'Login',
            components: { default: Login, header: SubHeader },
        },
        {
            path: '/join',
            name: 'Join',
            components: { default: Join, header: SubHeader },
        },
        {
            path: '/findpassword',
            name: 'FindPassword',
            components: { default: FindPassword, header: SubHeader },
        },
        {
            path: '/mypage',
            name: 'MyPage',
            components: { default: MyPage, header: SubHeader },
        },
        {
            path: '/fundingopen',
            name: 'FundingOpen',
            components: { default: FundingOpen, header: SubHeader },
        },
        {
            path: '/fundinglist',
            name: 'FundingList',
            components: { default: FundingList, header: SubHeader },
        },
        {
            path: '/fundingdetail/:pn',
            name: 'FundingDetail',
            components: { default: FundingDetail, header: SubHeader },
        },
        {
            path: '/schedule/:pn',
            name: 'Schedule',
            components: { default: Schedule, header: SubHeader },
        },
        {
            path: '/evaluate/:pn',
            name: 'Evaluate',
            components: { default: Evaluate, header: SubHeader },
        },
    ],
});
