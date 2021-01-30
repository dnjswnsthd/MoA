<template>
    <div class="cardBox">
        <div>
            <div>
                <v-spacer></v-spacer>
                <img src="@/assets/images/main/logo(Bg).png" alt="로고" class="memberFormLogo">
                <v-spacer></v-spacer>
                <h2 class="centerText">로그인</h2>
                <!-- <div class="lineBox"></div> -->
            </div>

            <div>

                <form class="col-12 formBox">
                
                    <v-text-field label="ID" type="text" class="col-12"></v-text-field>
                
                    <v-text-field label="PW" type="password" class="col-12"></v-text-field>  
                </form>
                <div class="fullWidth">
                    <button class="loginBtn col-4">Login</button>
                </div>
            </div>
        

        <v-card-actions class="cardActionBox">
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
            </v-card-actions>
        </div>
        </div>
</template>
<script>

</script>

<style scoped>
.cardBox {
  margin: 7.5rem auto;
}
</style>