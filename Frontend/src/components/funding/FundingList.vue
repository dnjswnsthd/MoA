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
            {{ slide }} Slide
        </div>
        </v-row>
    </v-sheet>
    </v-carousel-item>
</v-carousel>
<ul class="fundingPadding_2">
    <v-row>
    <v-spacer></v-spacer>
    <div class="fundingCategory fundingMargin">
    <li>
        <img class="width-80 height-80 fundingMargin" src="@/assets/category/design.png" alt="디자인">
        <router-link to="/">디자인</router-link>    
    </li>
    </div>
    <v-spacer></v-spacer>
    <div class="fundingCategory fundingMargin">
    <li>
        <img class="width-80 height-80 fundingMargin" src="@/assets/category/computer.png" alt="IT·프로그래밍">
        <router-link to="/">IT·프로그래밍</router-link>    
    </li>
    </div>
    <v-spacer></v-spacer>
    <div class="fundingCategory fundingMargin">
    <li>
        <img class="width-80 height-80 fundingMargin" src="@/assets/category/translate.png" alt="번역·통역">
        <router-link to="/">번역·통역</router-link>    
    </li>
    </div>
    <v-spacer></v-spacer>
    <div class="fundingCategory fundingMargin">
    <li>
            <img class="width-80 height-80 fundingMargin" src="@/assets/category/video.png" alt="영상·사진·음향">
            <router-link to="/">영상·사진·음향</router-link>    
    </li>
    </div>
    <v-spacer></v-spacer>
    <div class="fundingCategory fundingMargin">
    <li>      
            <img class="width-80 height-80 fundingMargin" src="@/assets/category/777.png" alt="운세·상담">
            <router-link to="/">운세·상담</router-link>            
    </li>
    </div> 
    <v-spacer></v-spacer>
    <div class="fundingCategory fundingMargin">
    <li>
            <img class="width-80 height-80 fundingMargin" src="@/assets/category/marketing.png" alt="마케팅">
            <router-link to="/">마케팅</router-link>    
    </li>
    </div>
    <v-spacer></v-spacer>
    </v-row>
</ul>
<br><br>
<v-divider></v-divider>
    <div class="pt-8">
        <h2>당신의 취향을 저격할 프로젝트</h2>
        <br>
        <v-row>
        <v-spacer></v-spacer>
        <div class="width-250">
            <img src="" alt="예시">
            <div>
                <v-progress-linear
                color="#CE93D8"
                buffer-value="0"
                value="20"
                stream
                ></v-progress-linear>
            </div>
            <ul class="pt-4">
                <v-row>
                    <li>펀딩 제목</li>
                    <li>|</li>
                    <li>펀딩 카테고리</li>
                </v-row>
            </ul>
        </div>
        <v-spacer></v-spacer>
        <div class="width-250">
            <img src="" alt="예시">
            <div>
                <v-progress-linear
                color="#CE93D8"
                buffer-value="0"
                value="20"
                stream
                ></v-progress-linear>
            <ul class="pt-4">
                <v-row>
                    <li>펀딩 제목</li>
                    <li>|</li>
                    <li>펀딩 카테고리</li>
                </v-row>
            </ul>
            </div>
        </div>
        <v-spacer></v-spacer>
        <div class="width-250">
            <img src="" alt="예시">
            <div>
                <v-progress-linear
                color="#CE93D8"
                buffer-value="0"
                value="20"
                stream
                ></v-progress-linear>
                <ul class="pt-4">
                <v-row>
                    <li>펀딩 제목</li>
                    <li>|</li>
                    <li>펀딩 카테고리</li>
                </v-row>
            </ul>
            </div>
        </div>
        <v-spacer></v-spacer>
        <br><br><br><br><br>
        </v-row>
    </div>
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
        'First',
        'Second',
        'Third',
        'Fourth',
        'Fifth',
        ],
    }
    }
}

</script>

<style>

</style>