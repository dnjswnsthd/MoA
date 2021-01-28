<template>
    <v-card>
        <div class="joinMargin">
            <img src="@/assets/images/main/logo(Bg).png">
        </div>
        <div class="centerText">
            <v-card-title >                
                <h1 class="joinMargin">회원 가입</h1>
            </v-card-title>
        </div>
        <div class="lineBox"></div>
            <v-tabs>
                <v-tab>멘토</v-tab>
                <v-tab>멘티</v-tab>
            </v-tabs>
            <form class="col-12 formBox">
                <v-row>
                <v-text-field label="ID" type="text"></v-text-field>
                <button class="idCheckBtn">중복체크</button>
                </v-row>
                <v-text-field label="PW" type="password"></v-text-field>
                <v-text-field label="PW" type="password"></v-text-field>
                <v-text-field label="Name" type="text"></v-text-field>
                <v-text-field label="Age" type="number"></v-text-field>
                <v-text-field label="major" type="text"></v-text-field>
                <v-text-field label="phone" type="tel"></v-text-field>
                <v-text-field label="favorite 1" type="text"></v-text-field>
                <v-text-field label="favorite 2" type="text"></v-text-field>
                <v-text-field label="favorite 3" type="text"></v-text-field>
                <v-textarea label="introduce" solo></v-textarea>
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
    </v-card>
    
</template>
<script>
</script>
<style>
    .formBox{
        margin:0 auto;
    }
    .idCheckBtn{
    
    line-height: 20px;
    padding: 8px 0 8px;
    max-width: 100%;
    min-width: 0px;
    width: 80px;
    }
    .joinMargin {
        margin: 0 auto;
    }
    .joinLinebox {
        width: 350px;
        height:10px;
        background-color:#ddd;
        margin:0 auto;
        border-radius: 30px;
    }

</style>