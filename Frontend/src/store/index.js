import Vue from 'vue';
import Vuex from 'vuex';
import jwt_decode from 'jwt-decode';
import { findById } from '@/api/user.js';
Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        isLogin: false, // 로그인 여부
        memberInfo: null, // 멤버 정보.
    },
    getters: {
        getMemberInfo(state) {
            return state.memberInfo; // state의 memberInfo를 리턴
        },
    },
    mutations: {
        setIsLogined(state, isLogin) {
            state.isLogin = isLogin; // state의 isLogin set
            console.log(state.isLogin); // 확인용 console
        },
        setMemberInfo(state, memberInfo) {
            state.isLogin = true; // 로그인여부를 true로 set
            state.memberInfo = memberInfo; // memberInfo를 set
        },
        logout(state) { // 로그아웃시
            state.isLogin = false; // 로그인여부를 false로 set
            state.memberInfo = null; // memberInfo를 비움.
        },
    },
    actions: {
        async GET_MEMBER_INFO({ commit }, token) {
            let decode = jwt_decode(token); // 가져온 token을 decode함.

            await findById( // user.js에 있는 findByid
                decode.id, // decode한 아이디.
                (response) => {
                    if (response.data.message === 'success') { // succeess가 돌아오면
                        commit('setMemberInfo', response.data.memberInfo); // 가져온 memberInfo를 set함.
                    } else { // 아니면 유저정보가없음.
                        console.log('유저 정보 없음!!');
                    }
                },
                (error) => {
                    console.log(error);
                }
            );
        },
        LOGOUT({ commit }) {
            commit('logout'); // 로그아웃 시
            localStorage.removeItem('access-token'); // 로그아웃시 localStorage의 access-token을 지움.
            
        },
    },
    modules: {},
});
