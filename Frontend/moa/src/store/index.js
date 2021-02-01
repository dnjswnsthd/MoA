import Vue from "vue";
import Vuex from "vuex";
// import jwt_decode from "jwt-decode";
// import { findById } from "@/api/user.js";
Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    isLogin: false, // 로그인 여부
    memberInfo: null,
  },
  mutations: {
    setIsLogined(state, isLogin) {
      state.isLogin = isLogin;
      console.log(state.isLogin);
    },
    setMemberInfo(state, memberInfo) {
      state.isLogin = true;
      state.memberInfo = memberInfo;
      
    },
    logout(state) {
      state.isLogin = false;
      state.memberInfo = null;
    }
  },
  actions: {
    
    LOGOUT({ commit }) {
      commit("logout");
      localStorage.removeItem("access-token");
      // axios.defaults.headers.common["auth-token"] = undefined;
    }
  },
  modules: {}
});
