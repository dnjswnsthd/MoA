<template>
    <div class="width-1000 fundingMargin">
        <h1>펀딩목록</h1>
        <v-carousel
            cycle
            height="300"
            hide-delimiter-background
            show-arrows-on-hover
        >
            <v-carousel-item v-for="(slide, i) in slides" :key="i">
                <v-sheet :color="colors[i]" height="100%">
                    <v-row class="fill-height" align="center" justify="center">
                        <div class="display-3">
                            {{ slide }}
                        </div>
                    </v-row>
                </v-sheet>
            </v-carousel-item>
        </v-carousel>
        <ul class="fundingListPadding_2">
            <v-row>
                <v-col v-for="category in categories" :key="category">
                    <v-spacer></v-spacer>
                    <div class="mainCategory">
                        <router-link to="/fundinglist">
                            <div
                                :id="category.value"
                                @click="change(category.name)"
                            >
                                <img
                                    class="width-40 height-40 centerContent "
                                    :src="category.img"
                                    alt="디자인"
                                />
                                <img
                                    class="width-40 height-40 centerContent "
                                    :src="category.afterImg"
                                    alt="디자인"
                                />
                            </div>
                        </router-link>
                        <router-link to="/fundinglist">{{
                            category.name
                        }}</router-link>
                    </div>
                    <v-spacer></v-spacer>
                </v-col>
            </v-row>
        </ul>
        <v-divider></v-divider>
        <div class="pt-2">
            <h2>당신의 취향을 저격할 프로젝트</h2>
            <v-row class="pt-5">
                <v-spacer></v-spacer>
                <div class="width-250">
                    <img
                        src="@/assets/images/funding/fox.jpg"
                        class="width-250 height-200"
                        alt="예시"
                    />
                    <br />
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
                            <p>|</p>
                            <li>카테고리 : 마케팅</li>
                        </v-row>
                    </ul>
                    <ul class="pt-4">
                        <v-row>
                            <li>기간 : ~2/15</li>
                            <li>|</li>
                            <li>담당자 : 조준형</li>
                        </v-row>
                    </ul>
                </div>
                <v-spacer></v-spacer>
                <div class="width-250">
                    <img
                        src="@/assets/images/funding/tarocard.jpg"
                        class="width-250 height-200"
                        alt="예시"
                    />
                    <br />
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
                                <p>|</p>
                                <li>카테고리 : 운세·상담</li>
                            </v-row>
                        </ul>
                        <ul class="pt-4">
                            <v-row>
                                <li>기간 : ~2/18</li>
                                <li>|</li>
                                <li>담당자 : 조준형</li>
                            </v-row>
                        </ul>
                    </div>
                </div>
                <v-spacer></v-spacer>
                <div class="width-250">
                    <img
                        src="@/assets/images/funding/translate.jpg"
                        class="width-250 height-200"
                        alt="예시"
                    />
                    <br />
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
                                <p>|</p>
                                <li>카테고리 : 번역·통역</li>
                            </v-row>
                        </ul>
                        <ul class="pt-4">
                            <v-row>
                                <li>기간 : ~2/22</li>
                                <li>|</li>
                                <li>담당자 : 조준형</li>
                            </v-row>
                        </ul>
                    </div>
                </div>
                <v-spacer></v-spacer>
                <br /><br /><br /><br /><br />
            </v-row>
        </div>
        <br /><br />
    </div>
</template>

<script>
export default {
    name: 'FundingList',
    data() {
        return {
            categories: [
                {
                    img: require('@/assets/category/design.png'),
                    afterImg: require('@/assets/category/design(c).png'),
                    name: '디자인',
                    value: 'design',
                },
                {
                    img: require('@/assets/category/computer.png'),
                    afterImg: require('@/assets/category/computer(c).png'),
                    name: 'IT·프로그래밍',
                    value: 'computer',
                },
                {
                    img: require('@/assets/category/translate.png'),
                    afterImg: require('@/assets/category/translate(c).png'),
                    name: '번역·통역',
                    value: 'translate',
                },
                {
                    img: require('@/assets/category/video.png'),
                    afterImg: require('@/assets/category/video(c).png'),
                    name: '영상·사진·음향',
                    value: 'video',
                },
                {
                    img: require('@/assets/category/lucky.png'),
                    afterImg: require('@/assets/category/lucky(c).png'),
                    name: '운세·상담',
                    value: 'lucky',
                },
                {
                    img: require('@/assets/category/marketing.png'),
                    afterImg: require('@/assets/category/marketing(c).png'),
                    name: '마케팅',
                    value: 'marketing',
                },
            ],
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

            selectOpButton: false,
            selectMyButton: false,
            selectSgButton: false,
            selectLgButton: false,
        };
    },
    methods: {
        change(name) {
            for (var i = 0; i < this.categories.length; i++) {
                if (this.categories[i].name == name)
                    this.categories[
                        i
                    ].img = require(`@/assets/category/${this.categories[i].value}(c).png`);
                else
                    this.categories[
                        i
                    ].img = require(`@/assets/category/${this.categories[i].value}.png`);
            }
        },
    },
};
</script>

<style>
#design img:last-child {
    display: none;
}
#design:hover img:first-child {
    display: none;
}
#design:hover img:last-child {
    display: block;
}

#computer img:last-child {
    display: none;
}
#computer:hover img:first-child {
    display: none;
}
#computer:hover img:last-child {
    display: block;
}

#translate img:last-child {
    display: none;
}
#translate:hover img:first-child {
    display: none;
}
#translate:hover img:last-child {
    display: block;
}

#video img:last-child {
    display: none;
}
#video:hover img:first-child {
    display: none;
}
#video:hover img:last-child {
    display: block;
}

#lucky img:last-child {
    display: none;
}
#lucky:hover img:first-child {
    display: none;
}
#lucky:hover img:last-child {
    display: block;
}

#marketing img:last-child {
    display: none;
}
#marketing:hover img:first-child {
    display: none;
}
#marketing:hover img:last-child {
    display: block;
}
</style>
