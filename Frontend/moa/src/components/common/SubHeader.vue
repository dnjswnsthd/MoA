<template>
    <header >
        <!-- <v-container class="col-8">
            <v-row>
                <div><img src="@/assets/images/main/logo.png" alt="로고" style="width: 150px; height: 60px;"></div>
                <v-spacer></v-spacer>                
                    <li class="width-120 centerText height-70">
                    <router-link to="/FundingOpen">펀딩 오픈</router-link>
                    </li>
                    <li class="width-120 centerText height-70">
                    <router-link to="/MyPage">My Page</router-link>
                    </li>
                    <li class="width-120 centerText height-70">
                    <router-link to="/foo">로그아웃</router-link>
                    </li>
            </v-row>          
        </v-container> -->

        <v-container class="col-8">
            <v-row>
                <div><img src="@/assets/images/main/logo.png" alt="로고" style="width: 150px; height: 60px;"></div>
                <v-spacer></v-spacer>               
                    <li class="width-120 centerText height-70">
                    <router-link to="/foo">회원가입</router-link>
                    </li>
                    <li class="width-120 centerText height-70">
                    <router-link to="/foo">로그인</router-link>
                    </li>
            </v-row>          
        </v-container>
    </header>
</template>

<script>


export default {
    name: "Header"
}
</script>

<style>
    .width-120 {
        width: 120px;
    }
    .height-70 {
        height: 70px;
    }
    .centerText {
        text-align: center;
    }
    header .container .row li {
        line-height: 70px;
    }
    header .container .row li a {
        color: black;
    }
</style>