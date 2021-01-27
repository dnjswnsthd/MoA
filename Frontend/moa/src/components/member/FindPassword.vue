<template>
<v-card>
        <v-card-title class="headline grey lighten-2">
            비밀번호 찾기
        </v-card-title>

        <v-row>
        <form class="col-11 formBox">
               <v-text-field label="ID" type="text" class="col-8"></v-text-field>
        </form>
        </v-row>

        <div>
            <v-btn>이메일로 찾기</v-btn>
            <v-btn>휴대폰번호로 찾기</v-btn>
        </div>
        <v-card-actions>
        <v-spacer></v-spacer>
            <v-btn
                color="primary"
                text
                @click="dialogLogin = false"
            >
            회원가입
            </v-btn>
            <v-btn
                color="primary"
                text
                @click="dialogLogin = false"
            >
            로그인
            </v-btn>
            </v-card-actions>
        </v-card>
</template>