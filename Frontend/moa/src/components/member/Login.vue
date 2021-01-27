<template>
    <v-card>
        <v-card-title class="headline grey lighten-2">
                로그인
        </v-card-title>

        <v-row>
        <form class="col-11 formBox">
               <v-text-field label="ID" type="text" class="col-8"></v-text-field>
               <v-text-field label="PW" type="password" class="col-8"></v-text-field>
               <v-btn class="col-4">Login</v-btn>
        </form>
        </v-row>

        <v-card-actions>
        <v-spacer></v-spacer>
        
            <v-btn
                color="primary"
                text
                @click="openFindPassword"
            >
            비밀번호 찾기
            </v-btn>
            <v-btn
                color="primary"
                text
                @click="openJoin"
            >
            회원가입
            </v-btn>
            </v-card-actions>

            <v-card v-if="dialogFindPassword">
                <find-password></find-password>
            </v-card>
            <v-card v-else-if="dialogJoin">
                <join></join>
            </v-card>
        </v-card>
</template>
<script>
import FindPassword from "@/components/member/FindPassword.vue";
import Join from "@/components/member/Join.vue";
export default {
    data(){
        return{
            dialogLogin:true,
            dialogFindPassword:false,
            dialogJoin:false,
        }
    },
    methods:{
        openFindPassword(){
            this.dialogLogin="false";
            this.dialogFindPassword="true";
            this.dialogJoin="false";
        },
        openJoin(){
            this.dialogLogin="false";
            this.dialogJoin="true";
            this.dialogFindPassword="false";
        }
    },
    components:{
        FindPassword,
        Join,
    }
}
</script>