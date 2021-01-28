<template>
    <header class="backgroundImg">
        <v-container class="col-8">
            <v-row>
                <div>
                    <router-link to="/">
                    <img
                        src="@/assets/images/main/logo.png"
                        alt="로고"
                        style="width: 150px; height: 70px;"
                    />
                    </router-link>
                </div>
                <v-spacer></v-spacer>
                <li class="width-120 centerText height-70">
                    <router-link to="/join">회원가입</router-link>
                </li> 
                <li class="width-120 centerText height-70">
                    <router-link to="/login">로그인</router-link>                         
                </li>
            </v-row>
            <div class="headerPadding">
                <div>
                <v-row>
                    
                    <div class="col-6">
                        <h1 class="headerH1">
                            "최고의 팀"
                            <br>꾸려보고 싶은가요?
                        </h1>
                    </div>
                    
                    <div class="searchBar col-6">
                        <input type="text" class="col-11">
                        <button class="col-1 searchIcon"><v-icon>mdi-magnify</v-icon></button>
                    </div>   
                            
                    </v-row>
                </div>
            
            </div>
        </v-container>
        <div class="headerPadding">
            <v-container class="col-9 headerMenu">
                <v-row>
                <v-spacer></v-spacer>
                <div class="col-2 mainCategory">
                    <v-icon class="viconColor">mdi-share-variant</v-icon><br>
                    <router-link to="/">디자인</router-link>
                </div>
                <div class="col-2 mainCategory">
                    <v-icon class="viconColor">mdi-share-variant</v-icon><br>
                    <router-link to="/">IT·프로그래밍</router-link>
                </div>
                <div class="col-2 mainCategory">
                    <v-icon class="viconColor">mdi-share-variant</v-icon><br>
                    <router-link to="/">번역·통역</router-link>
                </div>
                <div class="col-2 mainCategory">
                    <v-icon class="viconColor">mdi-share-variant</v-icon><br>
                    <router-link to="/">영상·사진·음향</router-link>
                </div>
                <div class="col-2 mainCategory">
                    <v-icon class="viconColor">mdi-share-variant</v-icon><br>
                    <router-link to="/">운세·상담</router-link>
                </div>
                <div class="col-2 mainCategory">
                    <v-icon class="viconColor">mdi-share-variant</v-icon><br>
                    <router-link to="/">마케팅</router-link>
                </div>
                <v-spacer></v-spacer>
                </v-row>
            </v-container>
        </div>
    </header>
</template>

<script>

</script>

<style>
    .button {
        background-color: white;
        color: black;
    }
    .searchBar {
        box-sizing:border-box;
        border-radius: 1.3rem;
        border: white 3px solid;
        background-color:rgba(255,255,255,0.5);
        /* background-color:rgba(255,255,255,1); */
        height:80px;
        margin:20px auto;
    }
    .searchBar input{
        padding:0; height:100%;
        margin:0 auto;
        color: #000;
        font-weight: bold;
        font-size: 25px;
        display:inline-block;
        
    }
    .searchBar .searchIcon{
        width:100%; height:100%;
        padding:0;
    }
    .searchBar .searchIcon i{
        color:white;
    }
    .headerH1 {
        color: white;
    }
    .backgroundImg {
        background-image: url('../../assets/images/main/mainBg.jpg');
        background-size: 100% 120%;
    }
    /* header .container .row .row div div .v-icon {
        color : white;
    } */
    .headerMenu {
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
        box-sizing: border-box;
        word-break: break-all;
        background-color: white;
        border-radius: 50px 50px 0px 0px;
        line-height: 1.8;
    }
    .mainCategory {
        text-align: center;
        width: 100px;
        height: 80px;
    }
    
    .headerPadding {
        padding-top: 150px;
    }

</style>
