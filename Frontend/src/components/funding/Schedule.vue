<template>
    <div class="col-8 centerContent">
        <div>
            <h1 class="height-50">name</h1>
            <h4 class="height-50">주제:</h4>
            <h4 class="height-50">일정:</h4>
        </div>

        <div v-for="(list, i) in lists" :key="i">
            <drop class="drop list width-1200" @drop="handleDrop(list, ...arguments)">
                <div class="col-9">
                    <v-row>
                        <h2>{{ items[i] }}</h2>
                        <v-dialog v-model="dialog" persistent max-width="290">
                            <template v-slot:activator="{ on, attrs }">
                                <v-btn
                                    class="mx-2"
                                    dark
                                    small
                                    color="#AB47BC"
                                    v-bind="attrs"
                                    v-on="on"
                                >
                                    <v-icon dark>
                                        mdi-plus
                                    </v-icon>
                                </v-btn>
                            </template>
                            <v-card>
                                <v-card-title class="headline">
                                    어떤 일정을 추가하시겠어요?
                                </v-card-title>
                                <v-text-field
                                    id="제목"
                                    type="text"
                                    label="제목"
                                    class="width-250 fundingMargin"
                                ></v-text-field>
                                <v-text-field
                                    id="내용"
                                    type="text"
                                    label="내용"
                                    class="width-250 fundingMargin"
                                ></v-text-field>
                                <v-text-field
                                    id="담당자"
                                    type="text"
                                    label="담당자"
                                    class="width-150 ml-6"
                                ></v-text-field>
                                <!-- <v-card-text>Let Google help apps determine location. This means sending anonymous location data to Google, even when no apps are running.</v-card-text> -->
                                <v-card-actions>
                                    <v-spacer></v-spacer>
                                    <v-btn color="#845ec2" text @click="dialog = false">
                                        취소
                                    </v-btn>
                                    <v-btn color="#845ec2" text @click="dialog = True">
                                        추가
                                    </v-btn>
                                </v-card-actions>
                            </v-card>
                        </v-dialog>
                    </v-row>
                </div>
                <v-sheet class="mx-auto" max-width="1250">
                    <v-slide-group v-model="model" class="pa-4" active-class="success" show-arrows>
                        <drag
                            v-for="item in list"
                            class="drag"
                            :key="item"
                            :class="{ [item]: true }"
                            :transfer-data="{ item: item, list: list, example: 'lists' }"
                        >
                            <v-slide-item>
                                <schedule-card :index="i" :item="item.title"></schedule-card>
                            </v-slide-item>
                        </drag>
                    </v-slide-group>
                </v-sheet>
            </drop>
        </div>
    </div>
</template>

<script>
import ScheduleCard from './ScheduleCard.vue';
// import draggable from 'vuedraggable';

import { Drag, Drop } from 'vue-drag-drop';

export default {
    components: {
        ScheduleCard,
        // draggable,
        Drag,
        Drop,
    },
    data() {
        return {
            dialog: false,
            items: ['할 일', '진행 중', '완료'],

            lists: [
                [
                    {
                        title: '뛰기',
                    },
                    { title: '걷기' },
                    { title: '앉기' },
                    {
                        title: '제티먹기',
                    },
                    { title: 'Egg in the Hell' },
                    { title: 'Pasta' },
                ],
                [],
                [],
            ],
        };
    },
    methods: {
        handleDrop(toList, data) {
            const fromList = data.list;
            console.log(data);
            if (fromList) {
                toList.push(data.item);
                fromList.splice(fromList.indexOf(data.item), 1);
                toList.sort((a, b) => a > b);
            }
        },
    },
};
</script>

<style scoped>
.drag {
    display: inline-block;
}
.drop {
    display: inline-block;
    vertical-align: top;
    padding: 15px;
    margin-bottom: 20px;
    width: 1200px;
    height: 220px;
}
</style>
