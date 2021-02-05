<template>
  <v-container class="col-8">
    <div class="progressing mt-10">
      <h2 class="centerText mainTitle">진행중인 팀 펀딩</h2>
      <div class="mt-10">
        <v-row class="centerContent my-10 col-12 mx-4">
          <v-col
            v-for="(funding, index) in fundings"
            :key="index"
            class="funding funding01 col-3 height-300 my-5 mx-12"
            :id="funding"
          >
            <router-link to="/fundingdetail">
              <div class="invisibleBox">
                <p>{{ fundingDatas[index].project_name }}</p>
                <p>{{ fundingDatas[index].participants }}명</p>
                <p>팀장 : 조준형</p>
                <p>Mentor : {{ fundingDatas[index].mentorName }}</p>
                <p class="shorthand">
                  {{ fundingDatas[index].description }}
                </p>
              </div>
            </router-link>
          </v-col>
        </v-row>
      </div>
    </div>
    <div>
      <h2 class="centerText mainTitle">멘토 랭킹</h2>
      <div class="mt-10">
        <v-row class="fundingRow col-12 mx-1 ">
          <!-- <v-spacer></v-spacer> -->
          <div
            class="col-4 height-300"
            v-for="rankName in rankings"
            :key="rankName"
          >
            <div class="halfHeight">
              <p class="centerText" style="font-size:20px">{{ rankName }}</p>
            </div>
            <v-col
              class="rankingBox"
              v-for="(rankData, index) in rankDatas"
              :key="index"
            >
              <v-row class="halfHeight mx-2">
                <v-col class="centerText col-2"> {{ index + 1 }}위 </v-col>
                <v-col class="centerText col-7">
                  {{ rankData.id }}
                </v-col>
                <v-col class="rightText col-3">
                  {{ rankData.score }}
                </v-col>
              </v-row>
            </v-col>
          </div>
        </v-row>
      </div>
    </div>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      fundings: [
        "funding01",
        "funding02",
        "funding03",
        "funding03",
        "funding01",
        "funding02"
      ],
      fundingDatas: [
        {
          project_name: "가보자 가보자!",
          participants: 4,
          mentorName: "MasterSIFU",
          description:
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the"
        },
        {
          project_name: "가보자 가보자!!",
          participants: 4,
          mentorName: "MasterSIFU",
          description:
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the"
        },
        {
          project_name: "가보자 가보자!!!",
          participants: 4,
          mentorName: "MasterSIFU",
          description:
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the"
        },
        {
          project_name: "가보자 가보자!!!!",
          participants: 4,
          mentorName: "MasterSIFU",
          description:
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the"
        },
        {
          project_name: "가보자 가보자!!!!!",
          participants: 4,
          mentorName: "MasterSIFU",
          description:
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the"
        },
        {
          project_name: "가보자 가보자!!!!!!",
          participants: 4,
          mentorName: "MasterSIFU",
          description:
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the"
        }
      ],
      rankings: ["경험치", "도덕성", "적극성", "신뢰도", "전문성", "리더쉽"],
      rankDatas: [
        {
          id: "song@naver.com",
          score: 10000
        },
        {
          id: "junhyung@naver.com",
          score: 9000
        },
        {
          id: "minju1004@naver.com",
          score: 8000
        },
        {
          id: "junoh31@naver.com",
          score: 7000
        },
        {
          id: "seok29@naver.com",
          score: 6000
        }
      ]
    };
  }
};
</script>

<style scoped>
@import "../../assets/css/mainpage.css";
</style>
