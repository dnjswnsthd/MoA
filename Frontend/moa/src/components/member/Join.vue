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

            <form class="formBox" v-if="mentorForm">
                <v-row>
                    <v-text-field label="멘토ID" type="text" required></v-text-field>
                    <button class="checkBtn">중복체크</button>
                </v-row>

                <v-row>                  
                    <v-text-field label="PW" type="password" required></v-text-field>
                    <div class="checkBtn"></div>
                </v-row>
                <v-row>
                    <v-text-field label="PW" type="password" required></v-text-field>
                    <div class="checkBtn"></div>
                </v-row>
                <v-row>
                    <v-text-field label="Name" type="name" required></v-text-field>
                    <v-spacer></v-spacer>
                    <v-text-field label="Age" type="number" required></v-text-field>
                </v-row>
                
                <v-row>
                    <v-text-field label="major" type="text" required></v-text-field>
                    <div class="checkBtn"></div>
                </v-row>
                <v-row>
                    <v-text-field label="phone" type="tel" required></v-text-field>
                    <div class="checkBtn"></div>
                </v-row>
                <v-row>
                    <v-select
                        :items="selectItems"
                        label="선호 분야"
                        dense
                        solo
                        class="width-50"
                    ></v-select>
                    <v-select
                        :items="selectItems"
                        label="선호 분야"
                        dense
                        solo
                        class="width-50 mx-10"
                    ></v-select>
                    <v-select
                        :items="selectItems"
                        label="선호 분야"
                        dense
                        solo
                        class="width-50"
                    ></v-select>
                </v-row>
               
                <v-row>
                    <v-textarea label="career" solo></v-textarea>
                    
                </v-row>
                <v-row>
                    <v-textarea label="introduce" solo></v-textarea>
                    
                </v-row>
                
                
            </form>
            
            <form class="formBox" v-else-if="menteeForm">
                <v-row>
                    <v-text-field label="멘티ID" type="text" required></v-text-field>
                    <button class="checkBtn">중복체크</button>
                </v-row>

                <v-row>                  
                    <v-text-field label="PW" type="password" required></v-text-field>
                    <div class="checkBtn"></div>
                </v-row>
                <v-row>
                    <v-text-field label="PW" type="password" required></v-text-field>
                    <div class="checkBtn"></div>
                </v-row>
                <v-row>
                    <v-text-field label="Name" type="name" required></v-text-field>
                    <v-spacer></v-spacer>
                    <v-text-field label="Age" type="number" required></v-text-field>
                </v-row>
                
                <v-row>
                    <v-text-field label="major" type="text" required></v-text-field>
                    <div class="checkBtn"></div>
                </v-row>
                <v-row>
                    <v-text-field label="phone" type="tel" required></v-text-field>
                    <div class="checkBtn"></div>
                </v-row>
                <v-row>
                    <v-select
                        :items="selectItems"
                        label="선호 분야"
                        dense
                        solo
                        class="width-50"
                    ></v-select>
                    <v-select
                        :items="selectItems"
                        label="선호 분야"
                        dense
                        solo
                        class="width-50 mx-10"
                    ></v-select>
                    <v-select
                        :items="selectItems"
                        label="선호 분야"
                        dense
                        solo
                        class="width-50"
                    ></v-select>
                </v-row>
                <v-row>
                    <v-textarea label="skill" solo></v-textarea>
                    
                </v-row>
                <v-row>
                    <v-textarea label="introduce" solo></v-textarea>
                    
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
                >
                가입하기
            </v-btn>
        </v-card-actions>
    </div>
    
</template>
 <script>
 export default {
     data(){
         return{
             mentorForm: true,
             menteeForm: false,
             selectItems: ['디자인', 'IT·프로그래밍', '번역·통역', '영상·사진·음향', '운세·상담', '마케팅'],
         }
     },
     methods:{
         openMentor(){
             this.mentorForm= true,
             this.menteeForm= false
         },
         openMentee(){
             this.mentorForm= false,
             this.menteeForm= true
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