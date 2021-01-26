<template>
    <header >
        <v-container class="col-8">
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
            <div class="col-12">
                <v-row>
                    <div class="col-6">
                        <h1>"최고의 팀" <br> 꾸려보고 싶으신가요?</h1>
                    </div>
                    
                    <div class="col-6">
                        <div class="searchBox col-6">
                            <input type="text"><v-icon>mdi-magnify</v-icon>
                        </div>
                        
                    </div>
               </v-row>
            </div>
                     
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
    .searchBox {
        border: 1px solid black;
        margin: 0 auto;
        border-radius: 10px;
    }
</style>