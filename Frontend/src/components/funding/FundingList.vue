<template>
<div class="width-1000 fundingMargin">
    <h1>펀딩목록</h1> 
    <v-carousel
    cycle
    height="300"
    hide-delimiter-background
    show-arrows-on-hover
    >
    <v-carousel-item
    v-for="(slide, i) in slides"
    :key="i"
    >
    <v-sheet
        :color="colors[i]"
        height="100%"
    >
        <v-row
        class="fill-height"
        align="center"
        justify="center"
        >
        <div class="display-3">
            {{ slide }} 
        </div>
        </v-row>
    </v-sheet>
    </v-carousel-item>
</v-carousel>
<ul class="fundingListPadding_2">
    <v-row>
    <v-spacer></v-spacer>
    <div class="fundingCategory fundingMargin">
    <li>
        <img class="width-40 height-40 fundingMargin" src="@/assets/category/design.png" alt="디자인">
        <router-link to="/">디자인</router-link>    
    </li>
    </div>
    <v-spacer></v-spacer>
    <div class="fundingCategory fundingMargin">
    <li>
        <img class="width-40 height-40 fundingMargin" src="@/assets/category/computer.png" alt="IT·프로그래밍">
        <router-link to="/">IT·프로그래밍</router-link>    
    </li>
    </div>
    <v-spacer></v-spacer>
    <div class="fundingCategory fundingMargin">
    <li>
        <img class="width-40 height-40 fundingMargin" src="@/assets/category/translate.png" alt="번역·통역">
        <router-link to="/">번역·통역</router-link>    
    </li>
    </div>
    <v-spacer></v-spacer>
    <div class="fundingCategory fundingMargin">
    <li>
            <img class="width-40 height-40 fundingMargin" src="@/assets/category/video.png" alt="영상·사진·음향">
            <router-link to="/">영상·사진·음향</router-link>    
    </li>
    </div>
    <v-spacer></v-spacer>
    <div class="fundingCategory fundingMargin">
    <li>      
            <img class="width-40 height-40 fundingMargin" src="@/assets/category/777.png" alt="운세·상담">
            <router-link to="/">운세·상담</router-link>            
    </li>
    </div> 
    <v-spacer></v-spacer>
    <div class="fundingCategory fundingMargin">
    <li>
            <img class="width-40 height-40 fundingMargin" src="@/assets/category/marketing.png" alt="마케팅">
            <router-link to="/">마케팅</router-link>    
    </li>
    </div>
    <v-spacer></v-spacer>
    </v-row>
</ul>
<v-divider></v-divider>
    <div class="pt-2">
        <h2>당신의 취향을 저격할 프로젝트</h2>
        <v-row class="pt-5"> 
        <v-spacer></v-spacer>
        <div class="width-250">
            <img src="@/assets/images/funding/fox.jpg" class="width-250 height-200" alt="예시">
            <br>
            <div>
                <v-progress-linear
                color="#CE93D8"
                buffer-value="0"
                value="80"
                stream
                ></v-progress-linear>
            </div>
            <ul class="pt-4">
                <v-row>
                    <li>사막여우 보호 프로젝트</li>
                    <p> | </p>
                    <li>카테고리 : 마케팅</li>
                </v-row>
            </ul>
            <ul class="pt-4">
                <v-row>
                    <li>기간 : ~2/15</li>
                    <li> | </li>
                    <li>담당자 : 조준형</li>
                </v-row>
            </ul>
        </div>
        <v-spacer></v-spacer>
        <div class="width-250">
            <img src="@/assets/images/funding/tarocard.jpg" class="width-250 height-200" alt="예시">
            <br>
            <div>
                <v-progress-linear
                color="#CE93D8"
                buffer-value="0"
                value="20"
                stream
                ></v-progress-linear>
            <ul class="pt-4">
                <v-row>
                    <li>좀 더 색다른 타로카드</li>
                    <p> | </p>
                    <li>카테고리 : 운세·상담</li>
                </v-row>
            </ul>
            <ul class="pt-4">
                <v-row>
                    <li>기간 : ~2/18</li>
                    <li> | </li>
                    <li>담당자 : 조준형</li>
                </v-row>
            </ul>
            </div>
        </div>
        <v-spacer></v-spacer>
        <div class="width-250">
            <img src="@/assets/images/funding/translate.jpg" class="width-250 height-200" alt="예시">
            <br>
            <div>
                <v-progress-linear
                color="#CE93D8"
                buffer-value="0"
                value="40"
                stream
                ></v-progress-linear>
            <ul class="pt-4">
                <v-row>
                    <li>쉽게 시작하는 통역</li>
                    <p> | </p>
                    <li>카테고리 : 번역·통역</li>
                </v-row>
            </ul>
            <ul class="pt-4">
                <v-row>
                    <li>기간 : ~2/22</li>
                    <li> | </li>
                    <li>담당자 : 조준형</li>
                </v-row>
            </ul>
            </div>
        </div>
        <v-spacer></v-spacer>
        <br><br><br><br><br>
        </v-row>
    </div>
    <br><br>
</div>
</template>

<script>
export default {
    name: "FundingList",
    data () {
    return {
        colors: [
        'indigo',
        'warning',
        'pink darken-2',
        'red lighten-1',
        'deep-purple accent-4',
        ],
        slides: [
        '반응형 웹 디자인',
        '오늘의 운세는?',
        '골목 시장 상권 살리기',
        '디자이너를 위한 프로젝트',
        '모바일 앱 제작',
        ],
    }
    }
}

</script>

<style>

</style>