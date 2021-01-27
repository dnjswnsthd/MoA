<template>
    <header>
        <v-container class="col-8">
            <v-row>
                <div><img src="@/assets/images/main/logo.png" alt="로고" style="width: 150px; height: 60px;"></div>
                <v-spacer></v-spacer>                
                    <li class="width-120 centerText height-70">
                        <div class="text-center">
                            <v-dialog
                            v-model="dialogJoin"
                            width="500"
                            >
                            <template v-slot:activator="{ on, attrs }">
                                <v-btn class="button"
                                v-bind="attrs"
                                v-on="on"
                                >
                                회원가입
                                </v-btn>
                            </template>
                            
                            <join></join>
                            </v-dialog>
                        </div>
                    </li>
                    <li class="width-120 centerText height-70">
                        <div class="text-center">
                            <v-dialog
                            v-model="dialogLogin"
                            width="500"
                            >
                            <template v-slot:activator="{ on, attrs }">
                                <v-btn class="button"
                                v-bind="attrs"
                                v-on="on"
                                >
                                Login
                                </v-btn>
                            </template>

                            <login></login>
                            </v-dialog>
                        </div>
                    </li>
            </v-row>          
        </v-container>

        
    </header>
</template>

<script>
import Login from '@/components/member/Login.vue';
import Join from '@/components/member/Join.vue';

 export default {
    data () {
      return {
        dialogLogin: false,
        dialogJoin: false,
        dialogFindPassword: false,
      }
    },
    components:{
        Login,
        Join
    }
  }
</script>

<style>
 .button{
     background-color:white;
     color:black;
 }
</style>