<template>
    <v-container class="col-lg-6 col-md-7 com-sm-8 col-12">
        <p class="pageTitle">펀딩오픈</p>
        <div class="col-12 centerContent pt-3">
            <h2>카테고리</h2>

            <v-row>
                <div
                    class="category col-lg-2 col-md-4 col-4"
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
        </div>

        <form class="col-12">
            <v-row>
                <v-spacer></v-spacer>
                <v-text-field
                    label="제목"
                    type="text"
                    v-model="project.project_name"
                    style="width:60%;"
                ></v-text-field>
                <v-spacer></v-spacer>
            </v-row>

            <v-row>
                <v-spacer></v-spacer>
                <v-text-field
                    label="인원"
                    type="number"
                    v-model="project.participants"
                    suffix="명"
                    style="width:40px;"
                    class="col-2"
                >
                </v-text-field>

                <v-text-field
                    label="펀딩액"
                    v-model="project.funding_cost"
                    suffix="원"
                    class="mx-10 col-2"
                    style="width:40px;"
                ></v-text-field>

                <v-spacer></v-spacer>
                <v-radio-group v-model="project.mentor_chk" class="centerContent" row>
                    멘토
                    <v-radio class="ml-5" value="필요있음" label="필요있음"></v-radio>
                    <v-radio value="필요없음" label="필요없음"></v-radio>
                </v-radio-group>
                <v-spacer></v-spacer>
                <v-sapcer></v-sapcer>
            </v-row>

            <v-row>
                <v-spacer></v-spacer>
                <v-text-field
                    label="모집기간"
                    type="date"
                    class="width-100"
                    v-model="project.deadline"
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

        <div class="py-5 centerContent ">
            <!-- <v-text-area type="text" class="fundingBox" v-model="project.description" /> -->
            <div class="editor px-2">
                <editor-menu-bar :editor="editor" v-slot="{ commands, isActive }">
                    <div class="menubar">
                        <button
                            class="menubar__button"
                            :class="{ 'is-active': isActive.bold() }"
                            @click="commands.bold"
                        >
                            <img class="icon" src="@/assets/images/textIcons/bold.svg" alt="B" />
                        </button>

                        <button
                            class="menubar__button"
                            :class="{ 'is-active': isActive.italic() }"
                            @click="commands.italic"
                        >
                            <img class="icon" src="@/assets/images/textIcons/italic.svg" alt="" />
                        </button>

                        <button
                            class="menubar__button"
                            :class="{ 'is-active': isActive.strike() }"
                            @click="commands.strike"
                        >
                            <img class="icon" src="@/assets/images/textIcons/strike.svg" alt="" />
                        </button>

                        <button
                            class="menubar__button"
                            :class="{ 'is-active': isActive.underline() }"
                            @click="commands.underline"
                        >
                            <img
                                class="icon"
                                src="@/assets/images/textIcons/underline.svg"
                                alt=""
                            />
                        </button>

                        <button
                            class="menubar__button"
                            :class="{ 'is-active': isActive.code() }"
                            @click="commands.code"
                        >
                            <img class="icon" src="@/assets/images/textIcons/code.svg" alt="" />
                        </button>

                        <button
                            class="menubar__button"
                            :class="{ 'is-active': isActive.paragraph() }"
                            @click="commands.paragraph"
                        >
                            <img
                                class="icon"
                                src="@/assets/images/textIcons/paragraph.svg"
                                alt=""
                            />
                        </button>

                        <button
                            class="menubar__button"
                            :class="{ 'is-active': isActive.heading({ level: 1 }) }"
                            @click="commands.heading({ level: 1 })"
                        >
                            H1
                        </button>

                        <button
                            class="menubar__button"
                            :class="{ 'is-active': isActive.heading({ level: 2 }) }"
                            @click="commands.heading({ level: 2 })"
                        >
                            H2
                        </button>

                        <button
                            class="menubar__button"
                            :class="{ 'is-active': isActive.heading({ level: 3 }) }"
                            @click="commands.heading({ level: 3 })"
                        >
                            H3
                        </button>

                        <button
                            class="menubar__button"
                            :class="{ 'is-active': isActive.bullet_list() }"
                            @click="commands.bullet_list"
                        >
                            <img class="icon" src="@/assets/images/textIcons/ul.svg" alt="" />
                        </button>

                        <button
                            class="menubar__button"
                            :class="{ 'is-active': isActive.ordered_list() }"
                            @click="commands.ordered_list"
                        >
                            <img class="icon" src="@/assets/images/textIcons/ol.svg" alt="" />
                        </button>

                        <button
                            class="menubar__button"
                            :class="{ 'is-active': isActive.blockquote() }"
                            @click="commands.blockquote"
                        >
                            <img class="icon" src="@/assets/images/textIcons/quote.svg" alt="" />
                        </button>

                        <button
                            class="menubar__button"
                            :class="{ 'is-active': isActive.code_block() }"
                            @click="commands.code_block"
                        >
                            <img class="icon" src="@/assets/images/textIcons/code.svg" alt="" />
                        </button>

                        <button class="menubar__button" @click="commands.horizontal_rule">
                            <img class="icon" src="@/assets/images/textIcons/hr.svg" alt="" />
                        </button>

                        <button class="menubar__button" @click="commands.undo">
                            <img class="icon" src="@/assets/images/textIcons/undo.svg" alt="" />
                        </button>

                        <button class="menubar__button" @click="commands.redo">
                            <img class="icon" src="@/assets/images/textIcons/redo.svg" alt="" />
                        </button>
                    </div>
                </editor-menu-bar>
                <div @click="focusEditor">
                    <editor-content class="editor__content px-2" :editor="editor" />
                </div>
            </div>
        </div>

        <v-row class="mt-3 btnBox">
            <v-spacer></v-spacer>
            <div class="fundingBtn width-100" @click="reset">
                초기화
            </div>
            <v-spacer></v-spacer>
            <div @click="openFunding" class="fundingBtn width-100">
                오픈하기
            </div>
            <v-spacer></v-spacer>
        </v-row>
    </v-container>
</template>

<script>
import http from '@/util/http-common';
import { mapState } from 'vuex';
import swal from 'sweetalert';

import { Editor, EditorContent, EditorMenuBar } from 'tiptap';
import {
    Blockquote,
    CodeBlock,
    HardBreak,
    Heading,
    HorizontalRule,
    OrderedList,
    BulletList,
    ListItem,
    TodoItem,
    TodoList,
    Bold,
    Code,
    Italic,
    Link,
    Strike,
    Underline,
    History,
} from 'tiptap-extensions';

export default {
    components: {
        EditorContent,
        EditorMenuBar,
    },
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
                    img: require('@/assets/images/category/design.png'),
                    afterImg: require('@/assets/images/category/design(c).png'),
                    name: '디자인',
                    value: 'design',
                },
                {
                    id: 1,
                    img: require('@/assets/images/category/computer.png'),
                    afterImg: require('@/assets/images/category/computer(c).png'),
                    name: 'IT·프로그래밍',
                    value: 'computer',
                },
                {
                    id: 2,
                    img: require('@/assets/images/category/translate.png'),
                    afterImg: require('@/assets/images/category/translate(c).png'),
                    name: '번역·통역',
                    value: 'translate',
                },
                {
                    id: 3,
                    img: require('@/assets/images/category/video.png'),
                    afterImg: require('@/assets/images/category/video(c).png'),
                    name: '영상·사진·음향',
                    value: 'video',
                },
                {
                    id: 4,
                    img: require('@/assets/images/category/lucky.png'),
                    afterImg: require('@/assets/images/category/lucky(c).png'),
                    name: '운세·상담',
                    value: 'lucky',
                },
                {
                    id: 5,
                    img: require('@/assets/images/category/marketing.png'),
                    afterImg: require('@/assets/images/category/marketing(c).png'),
                    name: '마케팅',
                    value: 'marketing',
                },
            ],
            editor: new Editor({
                extensions: [
                    new Blockquote(),
                    new BulletList(),
                    new CodeBlock(),
                    new HardBreak(),
                    new Heading({ levels: [1, 2, 3] }),
                    new HorizontalRule(),
                    new ListItem(),
                    new OrderedList(),
                    new TodoItem(),
                    new TodoList(),
                    new Link(),
                    new Bold(),
                    new Code(),
                    new Italic(),
                    new Strike(),
                    new Underline(),
                    new History(),
                ],
                content: ``,
            }),
        };
    },
    methods: {
        //클릭 한 카테고리의 이미지변경.
        categorySelect(name) {
            for (var i = 0; i < this.categories.length; i++) {
                if (this.categories[i].name == name) {
                    this.categories[
                        i
                    ].img = require(`@/assets/images/category/${this.categories[i].value}(c).png`);
                    this.project.category = this.categories[i].name;
                } else {
                    this.categories[
                        i
                    ].img = require(`@/assets/images/category/${this.categories[i].value}.png`);
                }
            }
        },
        // 펀딩오픈
        openFunding() {
            this.project.description = this.editor.getHTML();
            // 멘토체크를 필요없으로 체크시 cost를 0으로 set
            if (this.project.mentor_chk == '필요없음') {
                this.project.funding_cost = 0;
            }
            if (this.project.category == '') {
                swal('카테고리를 선택해주세요!!', {
                    icon: 'error',
                });
            } else {
                // project에 담긴, 정보를 가지고 통신.
                http.post('/project/create', this.project)
                    .then((response) => {
                        console.log(response);
                        if (response.data.message == 'success') {
                            swal('추가 완료!', {
                                icon: 'success',
                            });
                            this.$router.push({ name: 'Main' });
                        } else {
                            swal('추가 실패!', {
                                icon: 'error',
                            });
                        }
                    })
                    .catch(() => {
                        alert('추가 실패!');
                    });
            }
        },
        // 초기화 클릭 시 내용 초기화
        reset() {
            (this.project.project_name = ''),
                (this.project.participants = 0),
                (this.project.funding_cost = ''),
                (this.project.mentor_chk = ''),
                (this.project.deadline = ''),
                (this.project.end_date = ''),
                (this.proejct.description = '');
        },
        focusEditor() {
            this.editor.focus();
        },
    },
    beforeDestroy() {
        this.editor.destroy();
    },
};
</script>

<style scoped>
@import '../../assets/css/fundingOpen.css';

.editor {
    margin: 0 auto;
}

.editor__content {
    border: 1px solid gray;
    min-height: 10rem;
}

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
.fundingBtn {
    font-size: 14px;
}
</style>

<style lang="scss" scoped>
.icon {
    position: relative;
    display: inline-block;
    vertical-align: middle;
    width: 0.7rem;
    height: 0.8rem;
    margin: 0 0.3rem;
    top: -0.05rem;
    fill: currentColor;
    // &.has-align-fix {
    // 	top: -.1rem;
    // }
    &__svg {
        display: inline-block;
        vertical-align: top;
        width: 100%;
        height: 100%;
    }
    &:first-child {
        margin-left: 0;
    }
    &:last-child {
        margin-right: 0;
    }
}
// svg sprite
body > svg,
.icon use > svg,
symbol {
    path,
    rect,
    circle,
    g {
        fill: currentColor;
        stroke: none;
    }
    *[d='M0 0h24v24H0z'] {
        display: none;
    }
}
</style>
