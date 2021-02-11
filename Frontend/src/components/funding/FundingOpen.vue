<template>
    <div class="width-1000 centerContent">
        <p class="pageTitle">펀딩오픈</p>
        <div class="width-1000 centerContent pt-3">
            <h2>카테고리</h2>
            <ul class="pt-5">
                <v-row>
                    <div
                        class="fundingCategory centerContent"
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
                <v-row class="col-12">
                    <v-spacer></v-spacer>
                    <v-text-field
                        label="제목"
                        type="text"
                        v-model="project.project_name"
                        class="width-500"
                    ></v-text-field>
                    <v-spacer></v-spacer>
                </v-row>

                <v-row class="width-1000">
                    <v-spacer></v-spacer>
                    <v-text-field
                        label="인원"
                        type="number"
                        v-model="project.participants"
                        suffix="명"
                        style="width:5px;"
                    >
                    </v-text-field>

                    <v-text-field
                        label="펀딩액"
                        v-model="project.funding_cost"
                        suffix="원"
                        class="mx-10"
                        style="width:5px;"
                    ></v-text-field>

                    <v-radio-group v-model="project.mentor_chk" row>
                        멘토
                        <v-radio value="필요있음" label="필요있음"></v-radio>
                        <v-radio value="필요없음" label="필요없음"></v-radio>
                    </v-radio-group>
                    <v-spacer></v-spacer>
                </v-row>

                <v-row class="col-12">
                    <v-spacer></v-spacer>
                    <v-text-field
                        label="모집기간"
                        type="date"
                        class="width-100"
                        v-model="project.deadline"
                        style="width:80px;"
                    ></v-text-field>

                    <v-text-field
                        label="프로젝트 End"
                        type="date"
                        class="width-130 ml-15"
                        v-model="project.end_date"
                    ></v-text-field>
                    <v-spacer></v-spacer>
                </v-row>
            </form>
        </div>
        <div class="col-10 fundingContent">
            <div class="py-5 fundingMargin">
                <!-- <v-text-area type="text" class="fundingBox" v-model="project.description" /> -->
                <v-textarea
                    class="width-650 mx-auto"
                    outlined
                    name="input-12-4"
                    label="세부내용"
                    v-model="project.description"
                ></v-textarea>
            </div>
            <v-spacer></v-spacer>
        </div>
        <div class="height-50 mb-15">
            <v-row>
                <v-spacer></v-spacer>
                <div class="fundingBtn" @click="reset">
                    초기화
                </div>
                <v-spacer></v-spacer>
                <div @click="openFunding" class="fundingBtn">
                    오픈하기
                </div>
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

        let today = new Date();

        let year = today.getFullYear();
        let month = today.getMonth() + 1;
        let date = today.getDate();

        this.project.start_date = year + '-' + month + '-' + date;
        console.log(this.project.start_date);
    },
    data() {
        return {
            project: {
                category: '',
                project_name: '',
                participants: Number,
                mentor_chk: '',
                funding_cost: '',
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
        reset() {
            (this.project.project_name = ''),
                (this.project.participants = 0),
                (this.project.funding_cost = ''),
                (this.project.mentor_chk = ''),
                (this.project.deadline = ''),
                (this.project.end_date = ''),
                (this.proejct.description = '');
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
