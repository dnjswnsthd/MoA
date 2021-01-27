<template>
    <header>
        <v-container class="col-8">
            <v-row>
                <div><img src="@/assets/images/main/logo.png" alt="로고" style="width: 150px; height: 60px;"></div>
                <v-spacer></v-spacer>                
                    <li class="width-120 centerText height-70">
                        <div class="text-center">
                            <v-dialog
                            v-model="dialog"
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

                            <SignUp></SignUp>
                            </v-dialog>
                        </div>
                    </li>
                    <li class="width-120 centerText height-70">
                        <div class="text-center">
                            <v-dialog
                            v-model="dialog"
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
import SignUp from '@/components/member/SignUp.vue';

 export default {
    data () {
      return {
        dialog: false,
      }
    },
    components:{
        Login,
        SignUp
    }
  }
</script>

<style>
 .button{
     background-color:white;
     color:black;
 }
</style>