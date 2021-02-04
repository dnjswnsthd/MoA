<template>
    <div class="col-8 centerContent">
        <div>
            <h1 class="height-50">name</h1>
            <h4 class="height-50">주제: </h4>
            <h4 class="height-50">일정: </h4>
        </div>
        <div class="sceduleBorder"></div>
        <div class="pt-3">
            <div class="col-9">
            <v-row>
            <h2>할 일</h2>
            <v-dialog
            v-model="dialog"
            persistent
            max-width="290"
            >
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
        <v-text-field id="제목" type="text" label="제목" class="width-250 fundingMargin"></v-text-field>
        <v-text-field id="내용" type="text" label="내용" class="width-250 fundingMargin"></v-text-field>
        <v-text-field id="담당자" type="text" label="담당자" class="width-150 ml-6"></v-text-field>
        <!-- <v-card-text>Let Google help apps determine location. This means sending anonymous location data to Google, even when no apps are running.</v-card-text> -->
        <v-card-actions>
        <v-spacer></v-spacer>
            <v-btn
                color="#845ec2"
                text
                @click="dialog = false"
            >
            취소
            </v-btn>
            <v-btn
                color="#845ec2"
                text
                @click="dialog = True"
            >
            추가
            </v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
            </v-row>
            </div>
        <v-sheet
        class="mx-auto"
        max-width="1250"
        >
        <v-slide-group
        v-model="model"
        class="pa-4"
        active-class="success"
        show-arrows
        >
        <v-slide-item class="mr-5">
            <v-dialog
            v-model="dialog"
            >
            <template v-slot:activator="{ on, attrs }">
                <v-btn
                v-bind="attrs"
                v-on="on"
                >
                <v-card
            max-width="330"
            outlined
            >
            <v-list-item three-line class="scheduleList">
                <v-list-item-content>
                    <div class="overline mb-4">
                    OVERLINE
                    </div>
                    <v-list-item-title class="headline mb-1">
                    Headline 5
                    </v-list-item-title>
                    <v-list-item-subtitle>Greyhound divisely hello coldly fonwderfully</v-list-item-subtitle>
                </v-list-item-content>
                <v-card-actions>
                <v-btn
                    small
                    outlined
                    rounded
                    text
                >
                    삭제하기
                </v-btn>
                </v-card-actions>
            </v-list-item>            
            </v-card>
                </v-btn>
            </template>
            </v-dialog>
        </v-slide-item>
        <v-slide-item class="mr-5">
            <v-card
            max-width="330"
            outlined
            >
            <v-list-item three-line class="scheduleList">
                <v-list-item-content>
                    <div class="overline mb-4">
                    OVERLINE
                    </div>
                    <v-list-item-title class="headline mb-1">
                    Headline 5
                    </v-list-item-title>
                    <v-list-item-subtitle>Greyhound divisely hello coldly fonwderfully</v-list-item-subtitle>
                </v-list-item-content>
                <v-card-actions>
                <v-btn
                    small
                    outlined
                    rounded
                    text
                >
                    삭제하기
                </v-btn>
                </v-card-actions>
            </v-list-item>
            </v-card>
        </v-slide-item>
        <v-slide-item class="mr-5">
            <v-card
            max-width="330"
            outlined
            >
            <v-list-item three-line class="scheduleList">
                <v-list-item-content>
                    <div class="overline mb-4">
                    OVERLINE
                    </div>
                    <v-list-item-title class="headline mb-1">
                    Headline 5
                    </v-list-item-title>
                    <v-list-item-subtitle>Greyhound divisely hello coldly fonwderfully</v-list-item-subtitle>
                </v-list-item-content>
                <v-card-actions>
                <v-btn
                    small
                    outlined
                    rounded
                    text
                >
                    삭제하기
                </v-btn>
                </v-card-actions>
            </v-list-item>
            </v-card>
        </v-slide-item>
        <v-slide-item class="mr-5">
            <v-card
            max-width="330"
            outlined
            >
            <v-list-item three-line class="scheduleList">
                <v-list-item-content>
                    <div class="overline mb-4">
                    OVERLINE
                    </div>
                    <v-list-item-title class="headline mb-1">
                    Headline 5
                    </v-list-item-title>
                    <v-list-item-subtitle>Greyhound divisely hello coldly fonwderfully</v-list-item-subtitle>
                </v-list-item-content>
                <v-card-actions>
                <v-btn
                    small
                    outlined
                    rounded
                    text
                >
                    삭제하기
                </v-btn>
                </v-card-actions>
            </v-list-item>
            </v-card>
        </v-slide-item>
        <v-slide-item class="mr-5">
            <v-card
            max-width="330"
            outlined
            >
            <v-list-item three-line class="scheduleList">
                <v-list-item-content>
                    <div class="overline mb-4">
                    OVERLINE
                    </div>
                    <v-list-item-title class="headline mb-1">
                    Headline 5
                    </v-list-item-title>
                    <v-list-item-subtitle>Greyhound divisely hello coldly fonwderfully</v-list-item-subtitle>
                </v-list-item-content>
                <v-card-actions>
                <v-btn
                    small
                    outlined
                    rounded
                    text
                >
                    삭제하기
                </v-btn>
                </v-card-actions>
            </v-list-item>
            </v-card>
        </v-slide-item>
        </v-slide-group>
    </v-sheet>           
    </div>
    <div class="sceduleBorder"></div>
        <div class="pt-3">
            <div class="col-9">
            <v-row>
            <h2>진행 중</h2>
            <v-dialog
            v-model="dialog"
            persistent
            max-width="290"
            >
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
        <v-text-field id="제목" type="text" label="제목" class="width-250 fundingMargin"></v-text-field>
        <v-text-field id="내용" type="text" label="내용" class="width-250 fundingMargin"></v-text-field>
        <v-text-field id="담당자" type="text" label="담당자" class="width-150 ml-6"></v-text-field>
        <!-- <v-card-text>Let Google help apps determine location. This means sending anonymous location data to Google, even when no apps are running.</v-card-text> -->
        <v-card-actions>
        <v-spacer></v-spacer>
            <v-btn
                color="#845ec2"
                text
                @click="dialog = false"
            >
            취소
            </v-btn>
            <v-btn
                color="#845ec2"
                text
                @click="dialog = True"
            >
            추가
            </v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
            </v-row>
            </div>
            <v-sheet
        class="mx-auto"
        max-width="1250"
    >
        <v-slide-group
        v-model="model"
        class="pa-4"
        active-class="success"
        show-arrows
        >
        <v-slide-item class="mr-5">
            <v-card
            max-width="330"
            outlined
            >
            <v-list-item three-line class="scheduleList">
                <v-list-item-content>
                    <div class="overline mb-4">
                    OVERLINE
                    </div>
                    <v-list-item-title class="headline mb-1">
                    Headline 5
                    </v-list-item-title>
                    <v-list-item-subtitle>Greyhound divisely hello coldly fonwderfully</v-list-item-subtitle>
                </v-list-item-content>
                <v-card-actions>
                <v-btn
                    small
                    outlined
                    rounded
                    text
                >
                    삭제하기
                </v-btn>
                </v-card-actions>
            </v-list-item>
            </v-card>
        </v-slide-item>
        <v-slide-item class="mr-5">
            <v-card
            max-width="330"
            outlined
            >
            <v-list-item three-line class="scheduleList">
                <v-list-item-content>
                    <div class="overline mb-4">
                    OVERLINE
                    </div>
                    <v-list-item-title class="headline mb-1">
                    Headline 5
                    </v-list-item-title>
                    <v-list-item-subtitle>Greyhound divisely hello coldly fonwderfully</v-list-item-subtitle>
                </v-list-item-content>
                <v-card-actions>
                <v-btn
                    small
                    outlined
                    rounded
                    text
                >
                    삭제하기
                </v-btn>
                </v-card-actions>
            </v-list-item>
            </v-card>
        </v-slide-item>
        <v-slide-item class="mr-5">
            <v-card
            max-width="330"
            outlined
            >
            <v-list-item three-line class="scheduleList">
                <v-list-item-content>
                    <div class="overline mb-4">
                    OVERLINE
                    </div>
                    <v-list-item-title class="headline mb-1">
                    Headline 5
                    </v-list-item-title>
                    <v-list-item-subtitle>Greyhound divisely hello coldly fonwderfully</v-list-item-subtitle>
                </v-list-item-content>
                <v-card-actions>
                <v-btn
                    small
                    outlined
                    rounded
                    text
                >
                    삭제하기
                </v-btn>
                </v-card-actions>
            </v-list-item>
            </v-card>
        </v-slide-item>
        <v-slide-item class="mr-5">
            <v-card
            max-width="330"
            outlined
            >
            <v-list-item three-line class="scheduleList">
                <v-list-item-content>
                    <div class="overline mb-4">
                    OVERLINE
                    </div>
                    <v-list-item-title class="headline mb-1">
                    Headline 5
                    </v-list-item-title>
                    <v-list-item-subtitle>Greyhound divisely hello coldly fonwderfully</v-list-item-subtitle>
                </v-list-item-content>
                <v-card-actions>
                <v-btn
                    small
                    outlined
                    rounded
                    text
                >
                    삭제하기
                </v-btn>
                </v-card-actions>
            </v-list-item>
            </v-card>
        </v-slide-item>
        <v-slide-item class="mr-5">
            <v-card
            max-width="330"
            outlined
            >
            <v-list-item three-line class="scheduleList">
                <v-list-item-content>
                    <div class="overline mb-4">
                    OVERLINE
                    </div>
                    <v-list-item-title class="headline mb-1">
                    Headline 5
                    </v-list-item-title>
                    <v-list-item-subtitle>Greyhound divisely hello coldly fonwderfully</v-list-item-subtitle>
                </v-list-item-content>
                <v-card-actions>
                <v-btn
                    small
                    outlined
                    rounded
                    text
                >
                    삭제하기
                </v-btn>
                </v-card-actions>
            </v-list-item>
            </v-card>
        </v-slide-item>
        </v-slide-group>
    </v-sheet>           
    </div>
    <div class="sceduleBorder"></div>
        <div class="pt-3">
            <div class="col-9">
            <v-row>
            <h2>완료</h2>
            <v-dialog
            v-model="dialog"
            persistent
            max-width="290"
            >
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
        <v-text-field id="제목" type="text" label="제목" class="width-250 fundingMargin"></v-text-field>
        <v-text-field id="내용" type="text" label="내용" class="width-250 fundingMargin"></v-text-field>
        <v-text-field id="담당자" type="text" label="담당자" class="width-150 ml-6"></v-text-field>
        <!-- <v-card-text>Let Google help apps determine location. This means sending anonymous location data to Google, even when no apps are running.</v-card-text> -->
        <v-card-actions>
        <v-spacer></v-spacer>
            <v-btn
                color="#845ec2"
                text
                @click="dialog = false"
            >
            취소
            </v-btn>
            <v-btn
                color="#845ec2"
                text
                @click="dialog = True"
            >
            추가
            </v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
            </v-row>
            </div>
            <v-sheet
        class="mx-auto"
        max-width="1250"
    >
        <v-slide-group
        v-model="model"
        class="pa-4"
        active-class="success"
        show-arrows
        >
        <v-slide-item class="mr-5">
            <v-card
            max-width="330"
            outlined
            >
            <v-list-item three-line class="scheduleList">
                <v-list-item-content>
                    <div class="overline mb-4">
                    OVERLINE
                    </div>
                    <v-list-item-title class="headline mb-1">
                    Headline 5
                    </v-list-item-title>
                    <v-list-item-subtitle>Greyhound divisely hello coldly fonwderfully</v-list-item-subtitle>
                </v-list-item-content>
                <v-card-actions>
                <v-btn
                    small
                    outlined
                    rounded
                    text
                >
                    삭제하기
                </v-btn>
                </v-card-actions>
            </v-list-item>
            </v-card>
        </v-slide-item>
        <v-slide-item class="mr-5">
            <v-card
            max-width="330"
            outlined
            >
            <v-list-item three-line class="scheduleList">
                <v-list-item-content>
                    <div class="overline mb-4">
                    OVERLINE
                    </div>
                    <v-list-item-title class="headline mb-1">
                    Headline 5
                    </v-list-item-title>
                    <v-list-item-subtitle>Greyhound divisely hello coldly fonwderfully</v-list-item-subtitle>
                </v-list-item-content>
                <v-card-actions>
                <v-btn
                    small
                    outlined
                    rounded
                    text
                >
                    삭제하기
                </v-btn>
                </v-card-actions>
            </v-list-item>
            </v-card>
        </v-slide-item>
        <v-slide-item class="mr-5">
            <v-card
            max-width="330"
            outlined
            >
            <v-list-item three-line class="scheduleList">
                <v-list-item-content>
                    <div class="overline mb-4">
                    OVERLINE
                    </div>
                    <v-list-item-title class="headline mb-1">
                    Headline 5
                    </v-list-item-title>
                    <v-list-item-subtitle>Greyhound divisely hello coldly fonwderfully</v-list-item-subtitle>
                </v-list-item-content>
                <v-card-actions>
                <v-btn
                    small
                    outlined
                    rounded
                    text
                >
                    삭제하기
                </v-btn>
                </v-card-actions>
            </v-list-item>
            </v-card>
        </v-slide-item>
        <v-slide-item class="mr-5">
            <v-card
            max-width="330"
            outlined
            >
            <v-list-item three-line class="scheduleList">
                <v-list-item-content>
                    <div class="overline mb-4">
                    OVERLINE
                    </div>
                    <v-list-item-title class="headline mb-1">
                    Headline 5
                    </v-list-item-title>
                    <v-list-item-subtitle>Greyhound divisely hello coldly fonwderfully</v-list-item-subtitle>
                </v-list-item-content>
                <v-card-actions>
                <v-btn
                    small
                    outlined
                    rounded
                    text
                >
                    삭제하기
                </v-btn>
                </v-card-actions>
            </v-list-item>
            </v-card>
        </v-slide-item>
        <v-slide-item class="mr-5">
            <v-card
            max-width="330"
            outlined
            >
            <v-list-item three-line class="scheduleList">
                <v-list-item-content>
                    <div class="overline mb-4">
                    OVERLINE
                    </div>
                    <v-list-item-title class="headline mb-1">
                    Headline 5
                    </v-list-item-title>
                    <v-list-item-subtitle>Greyhound divisely hello coldly fonwderfully</v-list-item-subtitle>
                </v-list-item-content>
                <v-card-actions>
                <v-btn
                    small
                    outlined
                    rounded
                    text
                >
                    삭제하기
                </v-btn>
                </v-card-actions>
            </v-list-item>
            </v-card>
        </v-slide-item>
        </v-slide-group>
    </v-sheet>           
    </div>
    </div>
</template>

<script>
export default {
    
}
</script>

<style>

</style>