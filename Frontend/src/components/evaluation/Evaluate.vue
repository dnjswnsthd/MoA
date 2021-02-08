<template>
    <div class="col-8 centerContent height-1000">
        <p class="evaluateTitle">평가</p>
        <v-toolbar color="#c1a1d3" dark>
            <v-tabs v-model="tab" align-with-title>
                <v-tabs-slider color="yellow"></v-tabs-slider>
                <v-tab>
                    <p class="evaluateFont">멘토</p>
                </v-tab>
                <v-tab v-for="item in items" :key="item">
                    <p class="evaluateFont">{{ item }}</p>
                </v-tab>
            </v-tabs>
        </v-toolbar>
        <v-tabs-items v-model="tab">
            <v-tab-item>
                <v-row>
                    <v-spacer></v-spacer>
                    <v-col style="margin: auto;">
                        <div>
                            <v-card class="mx-auto" max-width="344">
                                <v-img
                                    src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg"
                                    height="200px"
                                ></v-img>

                                <v-card-title>
                                    Top western road trips
                                </v-card-title>

                                <v-card-subtitle>
                                    1,000 miles of wonder
                                </v-card-subtitle>
                            </v-card>
                        </div>
                    </v-col>
                    <v-spacer></v-spacer>
                    <v-col>
                        <v-card flat>
                            <v-card-text>
                                <h2 class="pb-5 pt-5">멘토 을(를) 평가해주세요!</h2>
                                <p v-for="mento in mentos" :key="mento" class="evaluateFont">
                                    {{ mento.title }}&nbsp;은/는 어땠나요?
                                    <v-rating
                                        v-model="mento.score"
                                        background-color="purple lighten-3"
                                        color="#bc6ff1"
                                        large
                                    ></v-rating>
                                </p>
                                <div class="pt-8" style="margin-bottom: 30px;">
                                    <v-row>
                                        <v-spacer></v-spacer>
                                        <v-btn>초기화</v-btn>
                                        <v-spacer></v-spacer>
                                        <v-btn>제출하기</v-btn>
                                    </v-row>
                                </div>
                            </v-card-text>
                        </v-card>
                    </v-col>
                    <v-spacer></v-spacer>
                </v-row>
            </v-tab-item>
            <v-tab-item v-for="item in items" :key="item">
                <v-row>
                    <v-spacer></v-spacer>
                    <v-col style="margin: auto;">
                        <div>
                            <v-card class="mx-auto" max-width="344">
                                <v-img
                                    src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg"
                                    height="200px"
                                ></v-img>

                                <v-card-title>
                                    Top western road trips
                                </v-card-title>

                                <v-card-subtitle>
                                    1,000 miles of wonder
                                </v-card-subtitle>
                            </v-card>
                        </div>
                    </v-col>
                    <v-spacer></v-spacer>
                    <v-card flat>
                        <v-card-text>
                            <h2 class="pb-5 pt-5">{{ item }}&nbsp;을(를) 평가해주세요!</h2>
                            <p v-for="evalute in evalutes" :key="evalute" class="evaluateFont">
                                {{ evalute }}&nbsp;은/는 어땠나요?
                                <v-rating
                                    v-model="rating"
                                    background-color="purple lighten-3"
                                    color="#bc6ff1"
                                    large
                                ></v-rating>
                            </p>
                            <div class="pt-8" style="margin-bottom: 30px;">
                                <v-row>
                                    <v-spacer></v-spacer>
                                    <v-btn>초기화</v-btn>
                                    <v-spacer></v-spacer>
                                    <v-btn>제출하기</v-btn>
                                </v-row>
                            </div>
                        </v-card-text>
                    </v-card>

                    <v-spacer></v-spacer>
                </v-row>
            </v-tab-item>
        </v-tabs-items>
    </div>
</template>

<script>
export default {
    name: 'Evaluate',
    data() {
        return {
            tab: null,
            items: ['멘티', '멘티2', '멘티3', '멘티4', '멘티5'],
            evalutes: ['의사소통', '책임감', '리더십', '수행능력', '수행자세'],
            mentos: [
                {
                    title: '도덕성',
                    score: 0,
                },
                {
                    title: '적극성',
                    score: 0,
                },
                {
                    title: '신뢰성',
                    score: 0,
                },
                {
                    title: '전문성',
                    score: 0,
                },
                {
                    title: '리더십',
                    score: 0,
                },
            ],
        };
    },
};
</script>

<style scoped>
.evaluateFont {
    font-size: large;
}

.evaluateTitle {
    font-size: xx-large;
}
</style>
