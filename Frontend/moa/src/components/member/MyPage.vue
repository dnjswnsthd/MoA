<template>
    <div class="cardBox">
            <h1 class="width-1000 centerText">내 정보</h1>
            <p class="myPoint rightText">포인트: 100,000</p>

        <form class="width-1000">
            <v-row class="width-1000" >
                <v-spacer></v-spacer>
                <v-text-field label="ID" type="text" class="width-700"></v-text-field>
                <v-spacer></v-spacer>
            </v-row>
        
            <v-row class="width-1000" >
            <v-spacer></v-spacer>
            <v-text-field label="NAME" type="text" class="width-250"></v-text-field>
            <v-spacer></v-spacer>
            <v-text-field label="AGE" type="text" class="width-250"></v-text-field> 
            <v-spacer></v-spacer>
            </v-row>
        
            <v-row class="width-1000">
                <v-spacer></v-spacer>
                <v-text-field label="MAJOR" type="text" class="width-700"></v-text-field>
                <v-spacer></v-spacer>
            </v-row>
            <v-row class="width-1000">
                <v-spacer></v-spacer>
                <v-text-field label="favorite 1" type="text" class="width-100"></v-text-field>
                <v-spacer></v-spacer>
                <v-text-field label="favorite 2" type="text" class="width-100"></v-text-field>
                <v-spacer></v-spacer>
                <v-text-field label="favorite 3" type="text" class="width-100"></v-text-field>
                <v-spacer></v-spacer>
            </v-row>
        </form>

        <div>
            <v-row class="btnBox">
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
                
                <v-btn
                color="primary"
                text
                >
                <router-link to="/">
                내 정보 변경
                </router-link>
                </v-btn>
                
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


        <div class="projectBox">
                <v-tabs
      color="deep-purple accent-4"
      left
    >
      <v-tab>관심중인 펀딩</v-tab>
      <v-tab>진행중인 펀딩</v-tab>
      <v-tab>종료된 펀딩</v-tab>
      <v-tab>참여 대기 중 펀딩</v-tab>

      <v-tab-item
        v-for="n in 4"
        :key="n"
      >
        <v-container fluid>
          <v-row>
            <v-col
              v-for="i in 6"
              :key="i"
              cols="12"
              md="4"
            >
              <v-img
                :src="`https://picsum.photos/500/300?image=${i * n * 5 + 10}`"
                :lazy-src="`https://picsum.photos/10/6?image=${i * n * 5 + 10}`"
                aspect-ratio="1"
              ></v-img>
            </v-col>
          </v-row>
        </v-container>
      </v-tab-item>
    </v-tabs>

            </div>
    </div>     
</template>


<script>
export default {
    name: "MyPage"
}
</script>

<style scoped>
.cardBox{
    width:1000px;
    margin: 50px auto;
}

</style>