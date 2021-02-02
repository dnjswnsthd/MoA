<template>
    <div class="cardBox">
        <div>
                <v-spacer></v-spacer>
                <img src="@/assets/images/main/logo(Bg).png" alt="로고" class="memberFormLogo">
                <v-spacer></v-spacer>
                <h2 class="centerText loginTitle">회원가입</h2>
                <!-- <div class="lineBox"></div> -->
            </div>
        
        
            <v-tabs>
                <v-tab @click="openMentor">멘토</v-tab>
                <v-tab @click="openMentee">멘티</v-tab>
            </v-tabs>

            <form class="formBox">
                <v-row>
                    <v-text-field label="ID" type="email" required v-model="member.id"></v-text-field>
                    <button class="checkBtn">중복체크</button>
                </v-row>
                <div class="error-text" v-if="error.email">{{error.email}}</div>
                <v-row>                  
                    <v-text-field label="Password" type="password" required v-model="member.pw"></v-text-field>
                    <div class="checkBtn"></div>
                </v-row>
                <v-row>
                    <v-text-field label="Password Confirm" type="password" required></v-text-field>
                    <div class="checkBtn"></div>
                </v-row>
                <v-row>
                    <v-text-field label="Name" type="name" required v-model="member.name"></v-text-field>
                    <v-spacer></v-spacer>
                    <v-text-field label="Age" type="number" required v-model="member.age"></v-text-field>
                </v-row>
                
                <v-row>
                    <v-text-field label="major" type="text" required v-model="member.major"></v-text-field>
                    <div class="checkBtn"></div>
                </v-row>
                <v-row>
                    <v-text-field label="phone" type="tel" required v-model="member.phone"></v-text-field>
                    <div class="checkBtn"></div>
                </v-row>
                <v-row>
                    <v-text-field
                        label="선호 분야"
                        dense
                        solo
                        class="width-50"
                        v-model="member.favorite_1"
                    ></v-text-field>
                    <v-text-field
                        label="선호 분야"
                        dense
                        solo
                        class="width-50 mx-10"
                        v-model="member.favorite_2"
                    ></v-text-field>
                    <v-text-field
                        label="선호 분야"
                        dense
                        solo
                        class="width-50"
                        v-model="member.favorite_3"
                    ></v-text-field>
                </v-row>

                <v-row>
                    <v-textarea label="introduce" solo v-model="member.introduce"></v-textarea>
                    
                </v-row>
               
                <v-row v-if="mentorForm">
                    <v-textarea label="career" solo v-model="member.career"></v-textarea>
                    
                </v-row>
                <v-row v-else-if="menteeForm">
                    <v-textarea label="skill" solo v-model="member.skill"></v-textarea>
                    
                </v-row>
                
            </form>
            
            <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                color="primary"
                text
                >
                초기화
            </v-btn>
            <v-btn
                color="primary"
                text
                @click="join"
                >
                가입하기
            </v-btn>
        </v-card-actions>
    </div>
    
</template>
<script>
import http from "@/util/http-common";
import PV from "password-validator";
import * as EmailValidator from "email-validator";
export default {
    data(){
        return{
            passwordSchema: new PV(),
            mentorForm: true,
            menteeForm: false,
            // selectItems: ['디자인', 'IT·프로그래밍', '번역·통역', '영상·사진·음향', '운세·상담', '마케팅'],
            member: {
                id: '',
                pw: '',
                name: '',
                age: Number,
                major:'',
                status:1,
                phone:'',
                favorite_1:'',
                favorite_2:'',
                favorite_3:'',
                career:'',
                skill:'',
                introduce:'',
            },
            error: {
                email: false,
                passowrd: false
            },
            email:"",
            password:"",
        }
    },
    created(){
        this.component = this;

        this.passwordSchema
        .is()
        .min(8)
        .is()
        .max(100)
        .has()
        .digits()
        .has()
        .letters();
    },
    watch:{
        password: function(){
            this.checkForm();
        },
        email: function(){
            this.checkForm();
        }
    },
    methods:{
        checkForm() {
            if (this.email.length >= 0 && !EmailValidator.validate(this.email))
                this.error.email = "이메일 형식이 아닙니다.";
            else this.error.email = false;
            if (
                this.password.length >= 0 &&
                !this.passwordSchema.validate(this.password)
            )
                this.error.password = "영문,숫자 포함 8 자리이상이어야 합니다.";
            else this.error.password = false;
            let isSubmit = true;
            Object.values(this.error).map(v => {
                if (v) isSubmit = false;
            });
            this.isSubmit = isSubmit;
            },
        openMentor(){
            this.mentorForm= true,
            this.menteeForm= false,
            this.member.status=1
        },
        openMentee(){
            this.mentorForm= false,
            this.menteeForm= true,
            this.member.status=2
        },
        join(){
            let data = this.member;
            console.log(data);
                http.post(`member/join`,data)
                .then(()=>{
                    alert('회원가입 성공!');
                    location.href = '/';
                }).catch(()=>{
                    alert('회원가입 실패!');
                });
            }

        }
}
</script>
<style scoped>
.cardBox{
    margin: 0 auto;
}
    .formBox{
        padding:0;
    }
    .checkBtn{
    
    line-height: 20px;
    padding: 8px 0 8px;
    max-width: 100%;
    min-width: 0px;
    width: 80px;
    }

</style>