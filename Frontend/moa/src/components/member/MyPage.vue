<template>
    <div class="width-1000 height-500 mypageMargin">
        <div>
        <v-row>
        <v-spacer></v-spacer>
        <h1>내 정보</h1>
        <v-spacer></v-spacer>
        <div class="width-500"></div>
        <h3 class="mypagePoint centerText">포인트: P</h3>
        <v-spacer></v-spacer>
        </v-row>
        </div>

        <form class="col-9 formBox width-700">
            <v-text-field label="ID" type="text" class="col-8 width-700"></v-text-field>
        <div>
            <v-row class="width-700" >
            <v-spacer></v-spacer>
            <v-text-field label="NAME" type="text" class="col-8 width-300"></v-text-field>
            <div class="width-100"></div>
            <v-text-field label="AGE" type="text" class="col-8 width-250"></v-text-field> 
            <v-spacer></v-spacer>   
            </v-row>
        </div>    
            <v-text-field label="MAJOR" type="text" class="col-8 width-700"></v-text-field>
        </form>
    <div>
    <v-row>
        <v-spacer></v-spacer>
        <v-btn
            color="primary"
            text
        >
        <router-link to="/">
        비밀번호 변경
        </router-link>
        </v-btn>
        <v-spacer></v-spacer>
        <v-spacer></v-spacer>
        <v-btn
        color="primary"
        text
        >
        <router-link to="/">
        내 정보 변경
        </router-link>
        </v-btn>
        <v-spacer></v-spacer>
        <v-spacer></v-spacer>
        <v-btn
        color="primary"
        text
        >
        <router-link to="/">
        탈퇴하기
        </router-link>
        </v-btn>
        <v-spacer></v-spacer>
    </v-row>
    </div>
    </div>     
</template>


<script>
export default {
    name: "MyPage"
}
</script>

<style>
    .mypageMargin {
        margin: 0 auto;
        padding: 50px;
    }
</style>