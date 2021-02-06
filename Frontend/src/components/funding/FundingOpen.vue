<template>
    <div class="width-1000 centerContent">
        <h1>펀딩오픈</h1>
        <div class="width-1000 centerContent pt-3">
            <h2>카테고리</h2>
            <ul class="pt-5">
                <v-row>
                    <div
                        class="fundingCategory fundingMargin"
                        v-for="category in categories"
                        :key="category.id"
                    >
                        <div :id="category.value" @click="categorySelect(category.name)">
                            <img
                                class="width-40 height-40 centerContent "
                                :src="category.img"
                                :alt="category.name"
                            />
                            <img
                                class="width-40 height-40 centerContent "
                                :src="category.afterImg"
                                :alt="category.name"
                            />
                        </div>
                        <p>{{ category.name }}</p>
                    </div>
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
                        v-model="project.project_name"
                    ></v-text-field>
                    <v-spacer></v-spacer>
                </v-row>
                <v-row class="width-1000">
                    <v-spacer></v-spacer>
                    <v-text-field
                        label="인원"
                        type="number"
                        class="width-700"
                        v-model="project.participants"
                    ></v-text-field>
                    <v-spacer></v-spacer>
                </v-row>
                <v-row class="width-1000">
                    <v-spacer></v-spacer>
                    <v-radio-group v-model="project.mentor_chk" row>
                        멘토
                        <v-radio value="필요있음" label="필요있음"></v-radio>
                        <v-radio value="필요없음" label="필요없음"></v-radio>
                    </v-radio-group>
                    <v-spacer></v-spacer>
                </v-row>
                <v-row class="width-1000">
                    <v-spacer></v-spacer>
                    <v-text-field
                        label="펀딩액"
                        type="number"
                        class="width-130"
                        v-model="project.funding_cost"
                    ></v-text-field>
                    <v-spacer></v-spacer>
                    <v-text-field
                        label="모집기간"
                        type="date"
                        class="width-100"
                        v-model="project.deadline"
                    ></v-text-field>
                    <v-spacer></v-spacer>
                </v-row>
                <v-row class="width-1000">
                    <v-spacer></v-spacer>
                    <v-text-field
                        label="프로젝트 Start"
                        type="date"
                        class="width-130"
                        v-model="project.start_date"
                    ></v-text-field>
                    <v-spacer></v-spacer>
                    <v-text-field
                        label="프로젝트 End"
                        type="date"
                        class="width-130"
                        v-model="project.end_date"
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
                <input type="text" class="fundingBox" v-model="project.description" />
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
                <v-btn color="primary" text @click="openFunding">
                    오픈하기
                </v-btn>
                <v-spacer></v-spacer>
            </v-row>
        </div>
    </div>
</template>

<script>
import http from '@/util/http-common';
import { mapState } from 'vuex';
export default {
    computed: {
        ...mapState(['memberInfo', 'isLogin']),
    },
    created() {
        this.project.leader = this.memberInfo.id;
    },
    data() {
        return {
            project: {
                category: '',
                project_name: '',
                participants: Number,
                mentor_chk: '',
                funding_cost: Number,
                deadline: '',
                start_date: '',
                end_date: '',
                description: '',
                leader: '',
            },
            categories: [
                {
                    id: 0,
                    img: require('@/assets/category/design.png'),
                    afterImg: require('@/assets/category/design(c).png'),
                    name: '디자인',
                    value: 'design',
                },
                {
                    id: 1,
                    img: require('@/assets/category/computer.png'),
                    afterImg: require('@/assets/category/computer(c).png'),
                    name: 'IT·프로그래밍',
                    value: 'computer',
                },
                {
                    id: 2,
                    img: require('@/assets/category/translate.png'),
                    afterImg: require('@/assets/category/translate(c).png'),
                    name: '번역·통역',
                    value: 'translate',
                },
                {
                    id: 3,
                    img: require('@/assets/category/video.png'),
                    afterImg: require('@/assets/category/video(c).png'),
                    name: '영상·사진·음향',
                    value: 'video',
                },
                {
                    id: 4,
                    img: require('@/assets/category/lucky.png'),
                    afterImg: require('@/assets/category/lucky(c).png'),
                    name: '운세·상담',
                    value: 'lucky',
                },
                {
                    id: 5,
                    img: require('@/assets/category/marketing.png'),
                    afterImg: require('@/assets/category/marketing(c).png'),
                    name: '마케팅',
                    value: 'marketing',
                },
            ],
        };
    },
    methods: {
        categorySelect(name) {
            for (var i = 0; i < this.categories.length; i++) {
                if (this.categories[i].name == name) {
                    this.categories[
                        i
                    ].img = require(`@/assets/category/${this.categories[i].value}(c).png`);
                    this.project.category = this.categories[i].name;
                } else {
                    this.categories[
                        i
                    ].img = require(`@/assets/category/${this.categories[i].value}.png`);
                }
            }
        },
        openFunding() {
            http.post('/project/create', this.project)
                .then((response) => {
                    console.log(response);
                    if (response.data.message == 'success') {
                        alert('추가 성공!');
                        this.$router.push({ name: 'Main' });
                    } else {
                        alert('추가실패!');
                    }
                })
                .catch(() => {
                    alert('추가 실패!');
                });
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
