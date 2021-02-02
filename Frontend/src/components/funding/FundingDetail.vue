<template>
    <div class="">
        <div class="banner col-8 centerContent">
            <p class="pl-5">IT.프로그래밍</p>
        </div>

        <div class="col-8 centerContent px-10 py-10">
            <v-row style="background-color:#eee;">                
                    <div class="col-6" style="background-color:#fff;">
                        <img src="@/assets/images/main/sample01.jpg" alt="샘플이미지" class="projectImg">
                    </div>
                    <v-spacer></v-spacer>
                    <div class="col-6">
                        <v-col style="background-color:#fff;">
                            <v-spacer></v-spacer>
                            <p>가보자가보자</p>
                            <v-spacer></v-spacer>
                            <p>2021.01.18 ~ 2021.02.28</p>
                            <v-spacer></v-spacer>
                            <p>10000 <span>* 4명</span></p>
                            <v-spacer></v-spacer>
                            <p>Mentor : <span>MasterSIFU</span></p>
                            <v-spacer></v-spacer>
                        </v-col>
                        <div class="centerText">
                            펀딩하기
                        </div>
                        <v-row class="col-12 btnList" style="margin:0; padding:0;">
                            <div class="col-4">대기 목록</div>
                            <v-spacer></v-spacer>
                            <div class="col-4">공유 하기</div>
                            <v-spacer></v-spacer>
                            <div class="col-4">관심 등록</div>
                        </v-row>
                    </div>

            </v-row>
            <v-row class="explainBox my-10">
                <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
                <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
                <!-- <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p> -->
                <!-- <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p> -->
                <!-- <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p> -->
            </v-row>
        </div>
    </div>
</template>
<style scoped>
    .banner{
        height:100px;
        /* background-color: ; */
    }
    .banner p{
        display:block;
        color:rgba(148,0,211,1);
        font-size:36px;
        line-height:100px;
    }
.projectImg{
width:100%; height:100%;
}
.btnList{
    text-align:center;
}
.explainBox{
    
    background-color: lightgoldenrodyellow;
}
</style>