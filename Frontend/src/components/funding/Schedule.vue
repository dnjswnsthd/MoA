<template>
  <div class="col-8 centerContent">
    <div>
      <h1 class="height-50">name</h1>
      <h4 class="height-50">주제:</h4>
      <h4 class="height-50">일정:</h4>
    </div>
    <div v-for="item in items" :key="item">
      <div class="sceduleBorder"></div>
      <div class="pt-3">
        <div class="col-9">
          <v-row>
            <h2>{{ item }}</h2>
            <v-dialog v-model="dialog" persistent max-width="290">
              <template v-slot:activator="{ on, attrs }">
                <v-btn
                  class="mx-2"
                  dark
                  small
                  color="#AB47BC"
                  v-bind="attrs"
                  v-on="on"
                >
                  <v-icon dark>
                    mdi-plus
                  </v-icon>
                </v-btn>
              </template>
              <v-card>
                <v-card-title class="headline">
                  어떤 일정을 추가하시겠어요?
                </v-card-title>
                <v-text-field
                  id="제목"
                  type="text"
                  label="제목"
                  class="width-250 fundingMargin"
                ></v-text-field>
                <v-text-field
                  id="내용"
                  type="text"
                  label="내용"
                  class="width-250 fundingMargin"
                ></v-text-field>
                <v-text-field
                  id="담당자"
                  type="text"
                  label="담당자"
                  class="width-150 ml-6"
                ></v-text-field>
                <!-- <v-card-text>Let Google help apps determine location. This means sending anonymous location data to Google, even when no apps are running.</v-card-text> -->
                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn color="#845ec2" text @click="dialog = false">
                    취소
                  </v-btn>
                  <v-btn color="#845ec2" text @click="dialog = True">
                    추가
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </v-row>
        </div>
        <v-sheet class="mx-auto" max-width="1250">
          <v-slide-group
            v-model="model"
            class="pa-4"
            active-class="success"
            show-arrows
          >
          
            <draggable class="mr-5" v-for="index in 5" :key="index">
            
               <schedule-card :index="index" :item="item" ></schedule-card>
           
            </draggable>
          </v-slide-group>
        </v-sheet>
        <rawDisplayer class="mr-5" :value="index"/>
         <rawDisplayer class="mr-5" :value="index"/>
      </div>
    </div>
  
  </div>
</template>

<script>
import ScheduleCard from "./ScheduleCard.vue";
import draggable from "vuedraggable";

export default {
  components: {
    ScheduleCard,
    draggable
  },
  data() {
    return {
      dialog: false,
      items: ["할 일", "진행 중", "완료"]
    };
  }
};
</script>

<style></style>
