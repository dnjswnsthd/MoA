<template>
    <div class="cardBox">
        <h1 class="width-1000 centerText">내 정보</h1>
        <p class="myPoint rightText">포인트: {{ memberInfo.point }}</p>

        <form class="width-1000">
            <v-row class="width-1000">
                <v-spacer></v-spacer>
                <v-text-field
                    label="ID"
                    type="text"
                    class="width-700"
                    v-model="memberInfo.id"
                    readonly
                ></v-text-field>
                <v-spacer></v-spacer>
            </v-row>
            <v-row class="width-1000">
                <v-spacer></v-spacer>
                <v-text-field
                    label="MoA Point"
                    type="text"
                    class="width-50"
                    v-model="memberInfo.point"
                ></v-text-field>
                <v-dialog v-model="plusPointDialog" max-width="290">
                    <template v-slot:activator="{ on, attrs }">
                        <v-btn class="ml-4" v-bind="attrs" v-on="on">
                            충전
                        </v-btn>
                    </template>
                    <v-card>
                        <v-card-title>
                            충전
                        </v-card-title>
                        <v-card-text>
                                <div>
                                    <p style="padding:0">충전 할 포인트</p>
                                    <v-text-field label="POINT" type="text" required v-model="changePoint"></v-text-field>
                                </div>
                        </v-card-text>
                        <v-card-actions>
                            <v-btn color="green darken-1" text @click="plusPoint">
                                충전하기
                            </v-btn>
                        </v-card-actions>
                    </v-card>
                </v-dialog>
                <v-dialog v-model="minusPointDialog" max-width="290">
                    <template v-slot:activator="{ on, attrs }">
                        <v-btn class="ml-4" v-bind="attrs" v-on="on">
                            전환
                        </v-btn>
                    </template>
                    <v-card>
                        <v-card-title>
                            전환
                        </v-card-title>
                        <v-card-text>
                                <div>
                                    <p style="padding:0">전환 할 포인트</p>
                                    <v-text-field label="POINT" type="text" required v-model="changePoint"></v-text-field>
                                </div>
                        </v-card-text>
                        <v-card-actions>
                            <v-btn color="green darken-1" text @click="minusPoint">
                                전환하기
                            </v-btn>
                        </v-card-actions>
                    </v-card>
                </v-dialog>
                <v-spacer></v-spacer>
                <v-spacer></v-spacer>
                <v-spacer></v-spacer>
                <v-spacer></v-spacer>
                <v-spacer></v-spacer>
                <v-spacer></v-spacer>
                <v-spacer></v-spacer>
            </v-row>
            <v-row class="width-1000">
                <v-spacer></v-spacer>
                <v-text-field
                    label="NAME"
                    type="text"
                    class="width-250"
                    v-model="memberInfo.name"
                    readonly
                ></v-text-field>
                <v-spacer></v-spacer>
                <v-text-field
                    label="AGE"
                    type="text"
                    class="width-250"
                    v-model="memberInfo.age"
                    readonly
                ></v-text-field>
                <v-spacer></v-spacer>
            </v-row>

            <v-row class="width-1000">
                <v-spacer></v-spacer>
                <v-text-field
                    label="MAJOR"
                    type="text"
                    class="width-700"
                    v-model="memberInfo.major"
                ></v-text-field>
                <v-spacer></v-spacer>
            </v-row>
            <v-row class="width-1000">
                <v-spacer></v-spacer>
                <v-text-field
                    label="favorite 1"
                    type="text"
                    class="width-100"
                    v-model="memberInfo.favorite_1"
                ></v-text-field>
                <v-spacer></v-spacer>
                <v-text-field
                    label="favorite 2"
                    type="text"
                    class="width-100"
                    v-model="memberInfo.favorite_2"
                ></v-text-field>
                <v-spacer></v-spacer>
                <v-text-field
                    label="favorite 3"
                    type="text"
                    class="width-100"
                    v-model="memberInfo.favorite_3"
                ></v-text-field>
                <v-spacer></v-spacer>
            </v-row>
        </form>

        <div>
            <v-row class="btnBox">
                <v-spacer></v-spacer>
                <div class="col-3">
                    <router-link to="/">
                        <p class="fundingBtn">
                            비밀번호 변경
                        </p>
                    </router-link>
                </div>
                <v-spacer></v-spacer>

                <!-- <v-btn
                color="primary"
                text
                >
                내 정보 변경
                </v-btn> -->
                <v-dialog v-model="modifyDialog" persistent max-width="290">
                    <template v-slot:activator="{ on, attrs }">
                        <div class="col-3">
                            <p class="fundingBtn" v-bind="attrs" v-on="on">
                                내 정보 변경
                            </p>
                        </div>
                    </template>
                    <v-card>
                        <v-card-title class="headline"> 비밀번호 확인 </v-card-title>
                        <v-card-text>
                            <v-text-field
                                label="Password"
                                type="password"
                                class="width-700"
                                v-model="password"
                            ></v-text-field>
                        </v-card-text>
                        <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn small outlined color="#bc6ff1" @click="modifyDialog = false">
                                취소
                            </v-btn>
                            <v-btn small outlined color="#bc6ff1" @click="modifyMember">
                                변경
                            </v-btn>
                        </v-card-actions>
                    </v-card>
                </v-dialog>
                <v-spacer></v-spacer>
                <v-dialog v-model="deleteDialog" persistent max-width="290">
                    <template v-slot:activator="{ on, attrs }">
                        <div class="col-3">
                            <p class="fundingBtn" v-bind="attrs" v-on="on">
                                탈퇴 하기
                            </p>
                        </div>
                    </template>
                    <v-card>
                        <v-card-title class="headline"> 비밀번호 확인 </v-card-title>
                        <v-card-text>
                            <v-text-field
                                label="Password"
                                type="password"
                                class="width-700"
                                v-model="password"
                            ></v-text-field>
                        </v-card-text>
                        <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn color="green darken-1" text @click="deleteDialog = false">
                                취소
                            </v-btn>
                            <v-btn color="green darken-1" text @click="deleteMember">
                                탈퇴
                            </v-btn>
                        </v-card-actions>
                    </v-card>
                </v-dialog>
                <v-spacer></v-spacer>
            </v-row>
        </div>

        <div class="projectBox">
            <v-tabs left slider-color="yellow">
                <v-toolbar color="#ab47bc" dark class="mb-10" style="border-radius:20px">
                    <v-tab style="font-size:18px; color:white" class="mb-2">진행중인 펀딩</v-tab>
                    <v-tab style="font-size:18px; color:white" class="mb-2"
                        >참여 대기 중 펀딩</v-tab
                    >
                    <v-tab style="font-size:18px; color:white" class="mb-2">종료된 펀딩</v-tab>
                    <v-tab style="font-size:18px; color:white" class="mb-2">관심중인 펀딩</v-tab>
                </v-toolbar>
                <v-tab-item v-for="n in 4" :key="n">
                    <v-container fluid>
                        <v-row v-if="n == 1">
                            <v-col
                                v-for="(proceed, index) in proceeding"
                                :key="index"
                                cols="12"
                                md="4"
                            >
                                <img
                                    src="@/assets/images/funding/fox.jpg"
                                    class="fullWidth height-200"
                                    alt="예시"
                                />

                                <div style="color: black;">
                                    <v-row>
                                        <p class="shorthand col-7">
                                            {{ proceed.project_name }}
                                        </p>
                                        <v-dialog
                                            v-model="proceedDialog"
                                            scrollable
                                            max-width="300px"
                                        >
                                            <template v-slot:activator="{ on, attrs }">
                                                <div class="mt-4 ml-9">
                                                    <v-btn
                                                        medium
                                                        class="ma-2"
                                                        outlined
                                                        color="#bc6ff1"
                                                        @click="projectState(proceed.project_num)"
                                                        v-bind="attrs"
                                                        v-on="on"
                                                    >
                                                        상태보기
                                                    </v-btn>
                                                </div>
                                            </template>
                                            <v-card>
                                                <v-card-title>참여 인원</v-card-title>
                                                <v-divider></v-divider>
                                                <v-card-text style="height: 300px;">
                                                    <div class="mt-3">
                                                        <v-row
                                                            v-for="(proceedMember,
                                                            index) in proceedingMember"
                                                            :key="index"
                                                        >
                                                            <p
                                                                class="shorthand col-7 ml-2"
                                                                style="color: black;"
                                                            >
                                                                {{ proceedMember.id }}
                                                            </p>
                                                            <v-btn
                                                                class="my-auto"
                                                                small
                                                                outlined
                                                                color="#bc6ff1"
                                                                @click="
                                                                    enjoyGroup(
                                                                        proceedMember.id,
                                                                        proceed.funding_cost,
                                                                        proceed.participants
                                                                    )
                                                                "
                                                                >수락</v-btn
                                                            >
                                                            <v-btn
                                                                class="my-auto"
                                                                small
                                                                outlined
                                                                color="#bc6ff1"
                                                                @click="
                                                                    refuseGroup(proceedMember.id)
                                                                "
                                                                >거절</v-btn
                                                            >
                                                        </v-row>
                                                    </div>
                                                </v-card-text>

                                                <v-divider></v-divider>
                                                <v-card-actions>
                                                    <v-btn
                                                        color="blue darken-1"
                                                        text
                                                        @click="proceedDialog = false"
                                                    >
                                                        Close
                                                    </v-btn>
                                                </v-card-actions>
                                            </v-card>
                                        </v-dialog>
                                    </v-row>
                                </div>
                            </v-col>
                        </v-row>
                        <v-row v-if="n == 2">
                            <v-col v-for="(wait, index) in waiting" :key="index" cols="12" md="4">
                                <img
                                    src="@/assets/images/funding/fox.jpg"
                                    class="fullWidth height-200"
                                    alt="예시"
                                />

                                <div>
                                    <v-row>
                                        <p class="shorthand col-7 ml-2" style="color: black;">
                                            {{ wait.project_name }}
                                        </p>
                                    </v-row>
                                </div>
                            </v-col>
                        </v-row>
                        <v-row v-if="n == 3">
                            <v-col
                                v-for="(completed, index) in complete"
                                :key="index"
                                cols="12"
                                md="4"
                            >
                                <img
                                    src="@/assets/images/funding/fox.jpg"
                                    class="fullWidth height-200"
                                    alt="예시"
                                />

                                <div>
                                    <v-row>
                                        <p class="shorthand col-7 ml-2" style="color: black;">
                                            {{ completed.project_name }}
                                        </p>
                                        <v-spacer></v-spacer>
                                        <div class="mt-4 ml-6">
                                            <v-btn
                                                medium
                                                class="ma-2"
                                                outlined
                                                color="#bc6ff1"
                                                v-if="completed.status == 0"
                                                @click="movePage(n, completed.project_num)"
                                            >
                                                평가하기
                                            </v-btn>
                                            <v-btn
                                                medium
                                                class="ma-2"
                                                outlined
                                                color="#bc6ff1"
                                                v-else
                                                disabled
                                            >
                                                평가완료
                                            </v-btn>
                                        </div>
                                    </v-row>
                                </div>
                            </v-col>
                        </v-row>
                        <v-row v-if="n == 4">
                            <v-col
                                v-for="(interest, index) in interesting"
                                :key="index"
                                cols="12"
                                md="4"
                            >
                                <img
                                    src="@/assets/images/funding/fox.jpg"
                                    class="fullWidth height-200"
                                    alt="예시"
                                />

                                <v-row>
                                    <p class="shorthand col-7 ml-2" style="color: black;">
                                        {{ interest.project_name }}
                                    </p>
                                    <v-dialog
                                        v-model="interestingDialog"
                                        scrollable
                                        persistent
                                        :retain-focus="false"
                                        max-width="300px"
                                    >
                                        <template v-slot:activator="{ on, attrs }">
                                            <div class="mt-4 ml-6">
                                                <v-btn
                                                    class="ma-2"
                                                    @click="projectState(interest.project_num)"
                                                    v-bind="attrs"
                                                    v-on="on"
                                                    outlined
                                                    color="#bc6ff1"
                                                    medium
                                                >
                                                    참여 인원
                                                </v-btn>
                                            </div>
                                        </template>
                                        <v-card>
                                            <v-card-title>참여 인원</v-card-title>
                                            <v-divider></v-divider>
                                            <v-card-text style="height: 300px;">
                                                <v-row
                                                    v-for="(proceedMember,
                                                    index) in proceedingMember"
                                                    :key="index"
                                                >
                                                    <p>{{ proceedMember.id }}</p>
                                                </v-row>
                                            </v-card-text>
                                            <v-divider></v-divider>
                                            <v-card-actions>
                                                <v-btn
                                                    color="blue darken-1"
                                                    text
                                                    @click="closeInterest"
                                                >
                                                    Close
                                                </v-btn>
                                            </v-card-actions>
                                        </v-card>
                                    </v-dialog>
                                </v-row>
                            </v-col>
                        </v-row>
                    </v-container>
                </v-tab-item>
            </v-tabs>
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
    data() {
        return {
            modifyDialog: false,
            deleteDialog: false,
            password: '',
            proceeding: {},
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
            complete: {},
            interesting: {},
            waiting: {},
            proceedingMember: {},
            project_num: '',
            proceedDialog: false,
            interestingDialog: false,
            plusPointDialog: false,
            minusPointDialog: false,
            changePoint:0,
        };
    },
    mounted() {
        console.log(`MyPage Mounted`);
        setTimeout(this.getMyFundingInfo, 200);
    },
    methods: {
        getMyFundingInfo() {
            http.get(`/member/mypage/${this.memberInfo.id}`)
                .then(({ data }) => {
                    this.member = data;
                })
                .catch(() => {
                    alert('에러가 발생하였습니다.');
                });

            http.get(`/project/proceeding/${this.memberInfo.id}`)
                .then((response) => {
                    if (response.data.message == 'success') {
                        this.proceeding = response.data.projectInfo;
                    } else {
                        alert('진행중플젝 가져오기 실패!');
                    }
                })
                .catch(() => {
                    alert('에러발생!');
                });
            // setTimeout(function() {}, 200);
            http.get(`/project/complete/${this.memberInfo.id}`)
                .then((response) => {
                    if (response.data.message == 'success') {
                        console.log(response.data.list);
                        this.complete = response.data.list;
                    } else {
                        alert('종료된플젝 가져오기 실패!');
                    }
                })
                .catch(() => {
                    alert('에러발생!');
                });
            http.get(`/project/interesting/${this.memberInfo.id}`)
                .then((response) => {
                    if (response.data.message == 'success') {
                        this.interesting = response.data.interestingProjectInfo;
                    } else {
                        alert('관심플젝 가져오기 실패!');
                    }
                })
                .catch(() => {
                    alert('에러발생!');
                });

            http.get(`/project/waiting/${this.memberInfo.id}`)
                .then((response) => {
                    if (response.data.message == 'success') {
                        this.waiting = response.data.waitingProjectInfo;
                    } else {
                        alert('진행중플젝 가져오기 실패!');
                    }
                })
                .catch(() => {});
        },
        movePage(n, pn) {
            if (n == 3) this.$router.push({ name: 'Evaluate', params: { pn: pn } });
        },
        deleteMember() {
            this.deleteDialog = false;
            http.post('/member/pwcheck', {
                id: this.memberInfo.id,
                pw: this.password,
            })
                .then((response) => {
                    if (response.data.message == 'success') {
                        http.delete(`/member/delete/${this.memberInfo.id}`)
                            .then(() => {
                                console.log('@@@');
                                this.$store
                                    .dispatch('LOGOUT')
                                    .then(() => {
                                        // this.$router.push({ name: "" });

                                        alert('삭제 성공!');
                                        if (this.$route.path !== '/') this.$router.replace('/');
                                    })
                                    .catch(() => {
                                        console.log('로그아웃 문제!!!');
                                    });
                            })
                            .catch(() => {
                                alert('삭제 실패!');
                            });
                    }
                })
                .catch(() => {
                    alert('에러 발생!');
                });
        },

        modifyMember() {
            this.modifyDialog = false;
            console.log('1');
            http.post('/member/pwcheck', {
                id: this.memberInfo.id,
                pw: this.password,
            })
                .then((response) => {
                    console.log('2');
                    console.log(response);
                    if (response.data.message == 'success') {
                        console.log('4');
                        http.put('/member/update', {
                            id: this.memberInfo.id,
                            name: this.memberInfo.name,
                            age: this.memberInfo.age,
                            major: this.memberInfo.major,
                            phone: this.memberInfo.phone,
                            favorite_1: this.memberInfo.favorite_1,
                            favorite_2: this.memberInfo.favorite_2,
                            favorite_3: this.memberInfo.favorite_3,
                            introduce: this.memberInfo.introduce,
                        })
                            .then(() => {
                                alert('수정 성공!');
                            })
                            .catch(() => {
                                alert('수정 실패!');
                            });
                    }
                })
                .catch(() => {
                    console.log('3');
                    alert('에러 발생!');
                });
        },
        projectState(project_num) {
            this.project_num = project_num;
            console.log(this.project_num);
            http.get(`/project/waitingList/${this.project_num}`)
                .then((response) => {
                    if (response.data.message == 'success') {
                        this.proceedingMember = response.data.member;
                    }
                })
                .catch(() => {
                    alert('에러발생!');
                });
        },
        enjoyGroup(id, funding_cost, participants) {
            let cost = funding_cost / participants;
            let updatePoint = this.memberInfo.point - cost;

            console.log('cost : ' + cost);
            console.log('updateP : ' + updatePoint);

            if (updatePoint < 0) {
                alert('포인트가 부족한 신청자 입니다.');
            } else {
                http.put('/project/permission', {
                    project_num: this.project_num,
                    id: id,
                    point: updatePoint,
                })
                    .then((response) => {
                        if (response.data.message == 'success') {
                            this.proceedDialog = false;
                            alert('수락성공');
                        } else {
                            alert('거절실패');
                        }
                    })
                    .catch(() => {
                        alert('에러발생!');
                    });
            }
        },
        refuseGroup(id) {
            console.log('id : ' + id);
            console.log(this.project_num);
            http.put('/project/denial', {
                project_num: this.project_num,
                id: id,
            })
                .then((response) => {
                    if (response.data.message == 'success') {
                        this.proceedDialog = false;
                        alert('거절 성공');
                    } else {
                        alert('거절 실패');
                    }
                })
                .catch(() => {
                    alert('에러발생!');
                });
        },
        closeInterest() {
            this.interestingDialog = false;
        },
        plusPoint(){
            this.changePoint = this.changePoint*1 + this.memberInfo.point ;
            http.put('/member/updatepoint',{
                id:this.memberInfo.id,
                point: this.changePoint
            }).then((response)=>{
                if(response.data.message == 'success'){
                    alert('충전성공!');
                    this.plusPointDialog = false;
                    location.href='/mypage';
                }else{
                    alert('충전실패!');
                    this.plusPointDialog = false;
                }
            }).catch(()=>{
                alert('에러발생!');
            })
        },
         minusPoint(){
            this.changePoint = this.memberInfo.point - this.changePoint*1;
            http.put('/member/updatepoint',{
                id:this.memberInfo.id,
                point: this.changePoint
            }).then((response)=>{
                if(response.data.message == 'success'){
                    alert('전환성공!');
                    this.minusPointDialog = false;
                    location.href='/mypage';
                }else{
                    alert('전환실패!');
                    this.minusPointDialog = false;
                }
            }).catch(()=>{
                alert('에러발생!');
            })
        }
    },
};
</script>

<style scoped>
.cardBox {
    position: relative;
    width: 1000px;
    margin: 50px auto;
}
p {
    padding: 10px 0 0 10px;
}

.fundingBtn {
    padding: 0;
}
</style>
