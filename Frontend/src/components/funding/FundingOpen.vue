<template>
    <div class="width-1000 centerContent">
        <h1>펀딩오픈</h1>
        <div class="width-1000 centerContent pt-3">
            <h2>카테고리</h2>
            <ul class="pt-5">
                <v-row>
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
        </div>
        <div class="cardBox">
            <form class="width-1000">
                <v-row class="width-1000">
                    <v-spacer></v-spacer>
                    <v-text-field
                        label="제목"
                        type="text"
                        class="width-700"
                        v-bind="project.project_name"
                    ></v-text-field>
                    <v-spacer></v-spacer>
                </v-row>
                <v-row class="width-1000">
                    <v-spacer></v-spacer>
                    <v-text-field
                        label="인원"
                        type="number"
                        class="width-700"
                         v-bind="project.participants"
                    ></v-text-field>
                    <v-spacer></v-spacer>
                </v-row>
                <v-row class="width-1000">
                    <v-spacer></v-spacer>
                    <v-text-field
                        label="멘토"
                        type="text"
                        class="width-700"
                         v-bind="project.mentor_chk"
                    ></v-text-field>
                    <v-spacer></v-spacer>
                </v-row>
                <v-row class="width-1000">
                    <v-spacer></v-spacer>
                    <v-text-field
                        label="펀딩액"
                        type="text"
                        class="width-130"
                        v-bind="project.funding_cost"
                    ></v-text-field>
                    <v-spacer></v-spacer>
                    <v-text-field
                        label="모집기간"
                        type="date"
                        class="width-100"
                        v-bind="project.deadline"
                    ></v-text-field>
                    <v-spacer></v-spacer>
                    <v-text-field
                        label="진행기간(단위: 주)"
                        type="number"
                        class="width-100"
                        v-bind="project.end_date"
                    ></v-text-field>
                    <v-spacer></v-spacer>
                </v-row>
            </form>
        </div>
        <div class="width-700 fundingContent">
            <v-spacer></v-spacer>
            <h3>세부내용</h3>
            <v-spacer></v-spacer>
            <div class="py-10 fundingMargin">
                <input type="text" class="fundingBox"  v-bind="project.description"/>
            </div>
            <v-spacer></v-spacer>
        </div>
        <div class="height-50">
            <v-row>
                <v-spacer></v-spacer>
                <v-btn color="primary" text class="width-100">
                    취소하기
                </v-btn>
                <v-spacer></v-spacer>
                <v-btn color="primary" text>
                    오픈하기
                </v-btn>
                <v-spacer></v-spacer>
            </v-row>
        </div>
    </div>
</template>

<script>
export default {
    project:{
        category:'',
        project_name:'',
        participants:'',
        mentor_chk:'',
        funding_cost:'',
        deadline:'',
        start_date:'',
        end_date:'',
        description:'',

    }
};
</script>

<style></style>
