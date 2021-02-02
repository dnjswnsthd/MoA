<template>
    <footer class="height-70">
        <div class="height-70 col-8 centerContent">
            <v-row class="height-70">
                <div>
                    <img src='@/assets/images/main/logo.png' alt="푸터로고" style="width:50%;  height:70px;">
                </div>
                <div class="contactBox width-1000 py-5">
                    <p class="col-4"><v-icon style="color:#fff;">mdi-home</v-icon> 구미시 진평동 초록빌라 102호</p>
                    <p class="col-4"> <v-icon style="color:#fff;">mdi-phone</v-icon> : 010-1234-5678</p>
                    <p class="col-4"> <v-icon style="color:#fff;">mdi-email</v-icon> : ssafy@ssafy.com</p>
                </div>
                
            </v-row>
        </div>
    </footer>
</template>
<style scoped>

footer{
    /* background-color:#401B7B; */
    background:linear-gradient(to right, #401B7B, #d68ddf);
    color:#fff;
}

footer .row div{
    height:100%;
}
    footer .row div:first-child img{
        margin:0 auto;
    }
    .contactBox{
        color:#fff;
        text-align:center;
        font-weight: 900;
        font-size:18px;
    }
    .contactBox p{
        padding:0; margin:0;
        display:inline-block;
    }
    
    
</style>