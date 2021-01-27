<template>
    <header class="backgroundImg">
        <v-container class="col-8">
            <v-row>
                <div>
                    <img
                        src="@/assets/images/main/logo.png"
                        alt="로고"
                        style="width: 150px; height: 60px;"
                    />
                </div>
                <v-spacer></v-spacer>
                <li class="width-120 centerText height-70">
                <div class="text-center">
                    <v-dialog v-model="dialog" width="500">
                    <template v-slot:activator="{ on, attrs }">
                        <v-btn class="button" v-bind="attrs" v-on="on">회원가입</v-btn>
                    </template>

                    <SignUp></SignUp>
                </v-dialog>
                </div>
                </li> 
                <li class="width-120 centerText height-70">
                <div class="text-center">
                    <v-dialog v-model="dialog" width="500">
                    <template v-slot:activator="{ on, attrs }">
                <v-btn class="button" v-bind="attrs" v-on="on">Login</v-btn>
                    </template>
                    <login></login>
                    </v-dialog>
                </div>                                                   
                </li>
            </v-row>
            <div class="headerPadding">
                <div style="margin: 0 auto;">
                <v-row>
                    <div class="searchBarDiv">
                        <h1 class="headerH1">
                            "최고의 팀"
                            <br>꾸려보고 싶은가요?
                        </h1>
                    </div>
                    <v-spacer></v-spacer>
                    <div class="searchBarDiv">
                        <input type="text" class="searchBar"><v-icon id="viconColor">mdi-magnify</v-icon>
                    </div>               
                    </v-row>
                </div>
            <v-spacer></v-spacer>
            </div>
        </v-container>
        <div class="headerPadding">
            <v-container class="col-9 headerMenu">
                <v-row>
                <v-spacer></v-spacer>
                <div class="col-2 mainCategory">
                    <v-icon class="viconColor">mdi-share-variant</v-icon><br>
                    <router-link to="/">디자인</router-link>
                </div>
                <div class="col-2 mainCategory">
                    <v-icon class="viconColor">mdi-share-variant</v-icon><br>
                    <router-link to="/">IT·프로그래밍</router-link>
                </div>
                <div class="col-2 mainCategory">
                    <v-icon class="viconColor">mdi-share-variant</v-icon><br>
                    <router-link to="/">번역·통역</router-link>
                </div>
                <div class="col-2 mainCategory">
                    <v-icon class="viconColor">mdi-share-variant</v-icon><br>
                    <router-link to="/">영상·사진·음향</router-link>
                </div>
                <div class="col-2 mainCategory">
                    <v-icon class="viconColor">mdi-share-variant</v-icon><br>
                    <router-link to="/">운세·상담</router-link>
                </div>
                <div class="col-2 mainCategory">
                    <v-icon class="viconColor">mdi-share-variant</v-icon><br>
                    <router-link to="/">마케팅</router-link>
                </div>
                <v-spacer></v-spacer>
                </v-row>
            </v-container>
        </div>
    </header>
</template>

<script>
import Login from "@/components/member/Login.vue";
import SignUp from "@/components/member/SignUp.vue";

export default {
    data() {
        return {
            dialog: false,
        };
    },
    components: {
        Login,
        SignUp,
    },
};
</script>

<style>
    .button {
        background-color: white;
        color: black;
    }
    .searchBar {
        box-sizing:border-box;
        border-radius: 5px;
        border: white 3px solid;
        width: 350px;
        height: 45px;
        margin: 0 auto;
        color: white;
        font-weight: bold;
        font-size: 25px;
        display: flex;
    }
    .headerH1 {
        color: white;
    }

    .backgroundImg {
        background-image: url('../../assets/images/main/mainBg.jpg');
        background-size: 100% 100%;
    }
    /* header .container .row .row div div .v-icon {
        color : white;
    } */
    .headerMenu {
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
        box-sizing: border-box;
        word-break: break-all;
        background-color: white;
        border-top-left-radius: 50px;
        border-top-right-radius: 50px;
        line-height: 1.8;
    }
    .mainCategory {
        text-align: center;
        width: 100px;
        height: 80px;
    }
    #viconColor {
        color: white;
    }
    .headerPadding {
        padding-top: 150px;
    }
    .searchBarDiv {
        width:400px;
        display: flex;   
    }
    .searchBarDiv{}
</style>
