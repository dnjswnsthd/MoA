<template>
    <div class="width-1000 centerContent">
        <h1>펀딩오픈</h1>
        <div class="width-1000 fundingMargin fundingPadding">
            <h2>카테고리</h2> 
            <ul class="fundingPadding_2">
                <v-row>
                <v-spacer></v-spacer>
                <li>
                    <div class="fundingCategory">
                        <v-icon class="viconColor">mdi-share-variant</v-icon><br>
                        <p class="fundingMargin">디자인</p>
                    </div>      
                </li>
                <v-spacer></v-spacer>
                <li>
                    <div class="fundingCategory">
                        <v-icon class="viconColor">mdi-share-variant</v-icon><br>
                        <p class="fundingMargin">IT·프로그래밍</p>
                    </div>      
                </li>
                <v-spacer></v-spacer>
                <li>
                    <div class="fundingCategory">
                        <v-icon class="viconColor">mdi-share-variant</v-icon><br>
                        <p class="fundingMargin">번역·통역</p>
                    </div>      
                </li>
                <v-spacer></v-spacer>
                <li>
                    <div class="fundingCategory">
                        <v-icon class="viconColor">mdi-share-variant</v-icon><br>
                        <p class="fundingMargin">영상·사진·음향</p>
                    </div>      
                </li>
                <v-spacer></v-spacer>
                <li>
                    <div class="fundingCategory">
                        <v-icon class="viconColor">mdi-share-variant</v-icon><br>
                        <p class="fundingMargin">운세·상담</p>
                    </div>      
                </li>
                <v-spacer></v-spacer>
                <li>
                    <div class="fundingCategory">
                        <v-icon class="viconColor">mdi-share-variant</v-icon><br>
                        <p class="fundingMargin">마케팅</p>
                    </div>      
                </li>
                <v-spacer></v-spacer>
                </v-row>
            </ul>
        </div>
        <div class="cardBox">
            <form class="width-1000">
            <v-row class="width-1000" >
                <v-spacer></v-spacer>
                <v-text-field label="제목" type="text" class="width-700"></v-text-field>
                <v-spacer></v-spacer>
            </v-row>
            <v-row class="width-1000">
            <v-spacer></v-spacer>
            <v-text-field label="인원" type="number" class="width-700"></v-text-field>
            <v-spacer></v-spacer>
            </v-row>
            <v-row class="width-1000" >
                <v-spacer></v-spacer>
                <v-text-field label="멘토" type="text" class="width-700"></v-text-field>
                <v-spacer></v-spacer>
            </v-row>
            <v-row class="width-1000" >
            <v-spacer></v-spacer>
            <v-text-field label="펀딩액" type="text" class="width-130"></v-text-field>
            <v-spacer></v-spacer>
            <v-text-field label="모집기간" type="date" class="width-100"></v-text-field>
            <v-spacer></v-spacer>
            <v-text-field label="진행기간(단위: 주)" type="number" class="width-100"></v-text-field>
            <v-spacer></v-spacer>          
            </v-row>
            </form>
        </div>
        <div class="width-700 fundingContent">
            <v-spacer></v-spacer>
                <h3>세부내용</h3> 
                <v-spacer></v-spacer>
                <div class="fundingPadding fundingMargin">
                <input type="text" class="fundingBox">
                </div>
                <v-spacer></v-spacer>
        </div>
        <div class="height-50">
            <v-row>
            <v-spacer></v-spacer>
            <v-btn 
            color="primary"
            text
            class="width-100">
                취소하기
            </v-btn>
            <v-spacer></v-spacer>
            <v-btn 
            color="primary"
            text>
                오픈하기
            </v-btn>
            <v-spacer></v-spacer>
            </v-row>
        </div>
    </div>
        


</template>

<script>
export default {
    name: "FundingOpen"
}
</script>

<style>
    .fundingPadding {
        padding: 10px;
    }
    .fundingPadding_2 {
        padding: 20px;
    }
</style> 