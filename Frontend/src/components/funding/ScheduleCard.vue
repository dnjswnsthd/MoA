<template>
    <v-dialog v-model="dialog" persistent max-width="500">
        <template v-slot:activator="{ on, attrs }">
            <v-card class="width-350" outlined>
                <v-list-item
                    three-line
                    class="scheduleList"
                    v-bind="attrs"
                    v-on="on"
                >
                    <v-list-item-content>
                        <v-list-item-title class="headline mb-1">
                            {{ item }} {{ index }}
                        </v-list-item-title>
                        <div class="overline mb-4">
                            담당자 :
                        </div>
                        <v-list-item-subtitle>기간: </v-list-item-subtitle>
                    </v-list-item-content>
                    <v-card-actions>
                        <v-btn small outlined rounded text>
                            삭제하기
                        </v-btn>
                    </v-card-actions>
                </v-list-item>
            </v-card>
        </template>

        <v-card class="px-5">
            <v-list-item-title class="headline mb-1">
                {{ item }} {{ index }}
            </v-list-item-title>
            <v-select
                :items="items"
                v-if="modifyDialog"
                label="상 태 "
            ></v-select>
            <p v-if="!modifyDialog">할일</p>
            <v-list-item-subtitle>
                Greyhound divisely hello coldly fonwderfully
            </v-list-item-subtitle>
            <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="green darken-1" text @click="dialog = false">
                    취소
                </v-btn>
                <v-btn color="green darken-1" text @click="modifyDialog = true">
                    수정
                </v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script>
export default {
    name: "ScheduleCard",
    data() {
        return {
            dialog: false,
            items: ["할 일", "진행 중", "완료"],
            modifyDialog: false,
        };
    },
    props: {
        index: Number,
        item: String,
    },
    methods: {
        // showCardDetail() {
        //     console.log("dsfadf");
        // },
    },
};
</script>

<style></style>
