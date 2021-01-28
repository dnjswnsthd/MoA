<template>
    <v-card >
        <div class="loginSize">
            <div>               
            <v-spacer></v-spacer>
            <img src="@/assets/images/main/logo(Bg).png" alt="로고" class="loginImg">
            <v-spacer></v-spacer>

            <h2 class="centerText">로그인</h2>
            <div class="loginLineBox"></div>
            </div>
            <div class="loginWidth">
                <v-row>
                <form class="col-10 formBox">
                <v-spacer></v-spacer>
                    <v-text-field label="ID" type="text" class="col-8"></v-text-field>
                <v-spacer></v-spacer>
                    <v-text-field label="PW" type="password" class="col-8"></v-text-field>  
                <v-spacer></v-spacer>      
                </form>
                    <v-btn class="col-4 loginButton">Login</v-btn>
                </v-row>
            </div>
        </div>
        <v-card-actions>
            <div class="loginMargin_2">
            <v-spacer></v-spacer>
                <v-btn
                    color="primary"
                    text
                >
                <router-link to="/findpassword">
                비밀번호 찾기
                </router-link>
                </v-btn>
                <v-btn
                    color="primary"
                    text
                >
                <router-link to="/join">
                회원가입
                </router-link>
                </v-btn>
            </div>
        </v-card-actions>
        
    </v-card>
</template>
<script>

</script>

<style scoped>
    .loginImg {
        width: 450px;
        height: 100x;
        margin: 0 auto;
    }
    .loginWidth {
        width: 450px;
        
        margin: 0 auto;
    }
    .loginSize {
        width: 800px;
        margin: 0 auto;
    }
    .loginMargin {
        margin: 0 auto;
    }
    .loginMargin_2 {
        margin: 0 auto;
        padding: 10px;
    }
    .loginLineBox{
        width: 350px;
        height:10px;
        background-color:#ddd;
        margin:0 auto;
        border-radius: 30px;
    }
    .loginButton {
        margin: 0 auto;
        padding-bottom: 10px;
    }
</style>