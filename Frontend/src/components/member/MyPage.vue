<template>
    <v-container class="col-lg-6 col-md-8 col-12 centerContent">
        <h1 class="centerText">내 정보</h1>
        <v-row>
            <div class="col-lg-6 col-md-6 col-12 centerContent">
                <RadarCanvas :id="`ability`" :labels="labels[memberInfo.status - 1]" :data="data" />
            </div>

            <form class="col-lg-6 col-md-6 col-12 centerContent">
                <v-row>
                    <v-text-field
                        label="ID"
                        type="text"
                        style="width: 60%"
                        v-model="memberInfo.id"
                        readonly
                    ></v-text-field>
                </v-row>
                <v-row>
                    <v-text-field
                        label="MoA Point"
                        type="text"
                        style="width: 15%"
                        v-model="memberInfo.point"
                    ></v-text-field>
                    <v-dialog v-model="plusPointDialog" max-width="290">
                        <template v-slot:activator="{ on, attrs }">
                            <v-btn
                                class="ml-4"
                                style="background-color: #ab47bc; color: white"
                                v-bind="attrs"
                                v-on="on"
                            >
                                충전
                            </v-btn>
                        </template>
                        <v-card>
                            <v-card-title> 충전 </v-card-title>
                            <v-card-text>
                                <div>
                                    <p style="padding: 0">충전 할 포인트</p>
                                    <v-text-field
                                        label="POINT"
                                        type="text"
                                        required
                                        v-model="changePoint"
                                    ></v-text-field>
                                </div>
                            </v-card-text>
                            <v-card-actions>
                                <v-spacer></v-spacer>
                                <v-btn color="#ab47bc" text @click="plusPoint"> 충전하기 </v-btn>
                            </v-card-actions>
                        </v-card>
                    </v-dialog>
                    <v-dialog v-model="minusPointDialog" max-width="290">
                        <template v-slot:activator="{ on, attrs }">
                            <v-btn
                                class="ml-4"
                                v-bind="attrs"
                                v-on="on"
                                style="background-color: #ab47bc; color: white"
                            >
                                전환
                            </v-btn>
                        </template>
                        <v-card>
                            <v-card-title> 전환 </v-card-title>
                            <v-card-text>
                                <div>
                                    <p style="padding: 0">전환 할 포인트</p>
                                    <v-text-field
                                        label="POINT"
                                        type="text"
                                        required
                                        v-model="changePoint"
                                    ></v-text-field>
                                </div>
                            </v-card-text>
                            <v-card-actions>
                                <v-spacer></v-spacer>
                                <v-btn color="#ab47bc" text @click="minusPoint"> 전환하기 </v-btn>
                            </v-card-actions>
                        </v-card>
                    </v-dialog>
                    <v-spacer></v-spacer>
                    <v-spacer></v-spacer>
                </v-row>
                <v-row>
                    <v-text-field
                        label="NAME"
                        type="text"
                        style="width: 30%"
                        v-model="memberInfo.name"
                        readonly
                    ></v-text-field>
                    <v-spacer></v-spacer>
                    <v-text-field
                        label="AGE"
                        type="text"
                        style="width: 30%"
                        v-model="memberInfo.age"
                        readonly
                    ></v-text-field>
                </v-row>

                <v-row>
                    <v-text-field
                        label="MAJOR"
                        type="text"
                        class="width:60%;"
                        v-model="memberInfo.major"
                    ></v-text-field>
                </v-row>
                <v-row>
                    <v-autocomplete
                        class="width-100 mr-2"
                        ref="member.favorite_1"
                        v-model="memberInfo.favorite_1"
                        :items="selectItems"
                        label="favorite_1"
                    >
                    </v-autocomplete>
                    <v-autocomplete
                        class="width-100 mr-2"
                        ref="member.favorite_2"
                        v-model="memberInfo.favorite_2"
                        :items="selectItems"
                        label="favorite_2"
                    >
                    </v-autocomplete>
                    <v-autocomplete
                        class="width-100"
                        ref="member.favorite_3"
                        v-model="memberInfo.favorite_3"
                        :items="selectItems"
                        label="favorite_3"
                    >
                    </v-autocomplete>
                </v-row>
            </form>
        </v-row>

        <v-row class="btnBox">
            <v-spacer></v-spacer>
            <v-dialog v-model="changePasswordDialog" max-width="290">
                <template v-slot:activator="{ on, attrs }">
                    <div class="col-3">
                        <p class="fundingBtn" v-bind="attrs" v-on="on">비밀번호 변경</p>
                    </div>
                </template>
                <v-card>
                    <v-card-title> 비밀번호 변경 </v-card-title>
                    <v-card-text>
                        <v-text-field
                            label="현재 비밀번호"
                            type="password"
                            class="width-700"
                            v-model="password"
                        ></v-text-field>
                        <v-text-field
                            label="변경 할 비밀번호"
                            type="password"
                            class="width-700"
                            v-model="modifyPassword"
                        ></v-text-field>
                        <v-text-field
                            label="변경 할 비밀번호 확인"
                            type="password"
                            class="width-700"
                            v-model="modifyPasswordChk"
                        ></v-text-field>
                    </v-card-text>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn small outlined color="#bc6ff1" @click="changePasswordDialog = false">
                            취소
                        </v-btn>
                        <v-btn small outlined color="#bc6ff1" @click="changePassword"> 변경 </v-btn>
                    </v-card-actions>
                </v-card>
            </v-dialog>
            <v-spacer></v-spacer>

            <v-dialog v-model="modifyDialog" max-width="290">
                <template v-slot:activator="{ on, attrs }">
                    <div class="col-3">
                        <p class="fundingBtn" v-bind="attrs" v-on="on">내 정보 변경</p>
                    </div>
                </template>
                <v-card>
                    <v-card-title> 비밀번호 확인 </v-card-title>
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
                        <v-btn small outlined color="#bc6ff1" @click="modifyMember"> 변경 </v-btn>
                    </v-card-actions>
                </v-card>
            </v-dialog>
            <v-spacer></v-spacer>

            <v-dialog v-model="deleteDialog" max-width="290">
                <template v-slot:activator="{ on, attrs }">
                    <div class="col-3">
                        <p class="fundingBtn" v-bind="attrs" v-on="on">탈퇴 하기</p>
                    </div>
                </template>
                <v-card>
                    <v-card-title> 비밀번호 확인 </v-card-title>
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
                        <v-btn color="green darken-1" text @click="deleteMember"> 탈퇴 </v-btn>
                    </v-card-actions>
                </v-card>
            </v-dialog>
            <v-spacer></v-spacer>
        </v-row>

        <div class="projectBox">
            <v-tabs left slider-color="yellow">
                <!--원상복구시 inline style로 color주기-->
                <v-toolbar color="#ab47bc" dark class="mb-10" style="border-radius: 20px">
                    <v-tab class="mb-2 mypageTabs">진행중인 펀딩</v-tab>
                    <v-tab class="mb-2 mypageTabs">참여 대기 중 펀딩</v-tab>
                    <v-tab class="mb-2 mypageTabs">종료된 펀딩</v-tab>
                    <v-tab class="mb-2 mypageTabs">관심중인 펀딩</v-tab>
                </v-toolbar>
                <v-tab-item v-for="n in 4" :key="n">
                    <v-container fluid>
                        <v-row v-if="n == 1">
                            <div
                                v-for="(proceed, index) in proceeding"
                                :key="index"
                                class="col-6 col-md-6 col-lg-4 mb-2"
                            >
                                <img
                                    :src="categoriesImg[proceed.category]"
                                    alt="예시"
                                    class="col-6 centerContent"
                                />

                                <v-row>
                                    <p class="shorthand col-12 col-md-6 col-lg-6 centerText">
                                        {{ proceed.project_name }}
                                    </p>
                                    <v-spacer></v-spacer>
                                    <v-dialog v-model="proceedDialog" scrollable max-width="300px">
                                        <template v-slot:activator="{ on, attrs }">
                                            <v-btn
                                                medium
                                                class="col-12 col-md-2 col-lg-2 centerContent"
                                                outlined
                                                color="#bc6ff1"
                                                @click="projectState(proceed.project_num)"
                                                v-bind="attrs"
                                                v-on="on"
                                            >
                                                상태보기
                                            </v-btn>
                                        </template>

                                        <v-card>
                                            <v-card-title>멤버현황</v-card-title>
                                            <v-divider></v-divider>
                                            <v-card-text style="height: 300px">
                                                <div class="mt-3">
                                                    <div v-if="proceedingMember.length == 0">
                                                        <p>신청 중인 멤버</p>
                                                        <p>신청중인 멤버가없습니다.</p>
                                                    </div>

                                                    <v-row
                                                        v-for="(proceedMember,
                                                        index) in proceedingMember"
                                                        :key="index"
                                                        v-else
                                                    >
                                                        <p class="shorthand col-7">
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
                                                            @click="refuseGroup(proceedMember.id)"
                                                            >거절</v-btn
                                                        >
                                                    </v-row>
                                                    <v-divider></v-divider>
                                                    <p>확정멤버</p>
                                                    <div
                                                        v-for="(participant, index) in participants"
                                                        :key="index"
                                                    >
                                                        <p>{{ participant.id }}</p>
                                                    </div>
                                                </div>
                                            </v-card-text>

                                            <v-divider></v-divider>
                                            <v-card-actions>
                                                <v-btn
                                                    color="blue darken-1"
                                                    text
                                                    @click="closeProceed"
                                                >
                                                    Close
                                                </v-btn>
                                            </v-card-actions>
                                        </v-card>
                                    </v-dialog>
                                </v-row>
                            </div>
                        </v-row>
                        <v-row v-if="n == 2">
                            <div
                                v-for="(wait, index) in waiting"
                                :key="index"
                                class="col-6 col-md-6 col-lg-4"
                            >
                                <img
                                    :src="categoriesImg[wait.category]"
                                    class="col-6 centerContent"
                                    alt="예시"
                                />

                                <div>
                                    <v-row>
                                        <p class="shorthand col-12 centerText">
                                            {{ wait.project_name }}
                                        </p>
                                    </v-row>
                                </div>
                            </div>
                        </v-row>
                        <v-row v-if="n == 3">
                            <div
                                v-for="(completed, index) in complete"
                                :key="index"
                                class="col-6 col-md-6 col-lg-4"
                            >
                                <img
                                    :src="categoriesImg[completed.category]"
                                    class="col-6 centerContent"
                                    alt="예시"
                                />

                                <v-row>
                                    <p class="shorthand col-12 col-md-6 col-lg-6 centerText">
                                        {{ completed.project_name }}
                                    </p>
                                    <v-spacer></v-spacer>

                                    <v-btn
                                        medium
                                        class="col-12 col-md-2 col-lg-2 centerContent"
                                        outlined
                                        color="#bc6ff1"
                                        v-if="completed.status == 0"
                                        @click="moveEvaluatePage(n, completed.project_num)"
                                    >
                                        평가하기
                                    </v-btn>
                                    <v-btn
                                        medium
                                        class="col-12 col-md-2 col-lg-2 centerContent"
                                        outlined
                                        color="#bc6ff1"
                                        v-else
                                        disabled
                                    >
                                        평가완료
                                    </v-btn>
                                </v-row>
                            </div>
                        </v-row>
                        <v-row v-if="n == 4">
                            <div
                                v-for="(interest, index) in interesting"
                                :key="index"
                                class="col-6 col-md-6 col-lg-4"
                            >
                                <img
                                    :src="categoriesImg[interest.category]"
                                    class="col-6 centerContent"
                                    alt="예시"
                                />

                                <v-row>
                                    <p class="shorthand col-12 col-md-6 col-lg-6 centerText">
                                        {{ interest.project_name }}
                                    </p>
                                    <v-spacer></v-spacer>
                                    <v-dialog
                                        v-model="interestingDialog"
                                        scrollable
                                        :retain-focus="false"
                                        max-width="300px"
                                    >
                                        <template v-slot:activator="{ on, attrs }">
                                            <v-btn
                                                class="col-12 col-md-2 col-lg-2 centerContent"
                                                @click="projectState(interest.project_num)"
                                                v-bind="attrs"
                                                v-on="on"
                                                outlined
                                                color="#bc6ff1"
                                                medium
                                            >
                                                참여 인원
                                            </v-btn>
                                        </template>
                                        <v-card>
                                            <v-card-title>참여 인원</v-card-title>
                                            <v-divider></v-divider>
                                            <v-card-text style="height: 300px">
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
                            </div>
                        </v-row>
                    </v-container>
                </v-tab-item>
            </v-tabs>
        </div>
    </v-container>
</template>

<script>
import http from '@/util/http-common';
import { mapState } from 'vuex';
import swal from 'sweetalert';

import RadarCanvas from '../evaluation/RadarCanvas.vue';

export default {
    components: {
        RadarCanvas, // 능력치 그래프를 그리는 RadarCavas.vue를 가져옴.
    },
    computed: {
        ...mapState(['memberInfo', 'isLogin']),
    },
    data() {
        return {
            modifyDialog: false,
            deleteDialog: false,
            password: '',
            proceeding: {},
            participants: {},
            categoriesImg: {
                디자인: require('@/assets/images/category/design(c).png'),
                'IT·프로그래밍': require('@/assets/images/category/computer(c).png'),
                '번역·통역': require('@/assets/images/category/translate(c).png'),
                '영상·사진·음향': require('@/assets/images/category/video(c).png'),
                '운세·상담': require('@/assets/images/category/lucky(c).png'),
                마케팅: require('@/assets/images/category/marketing(c).png'),
            },
            participatns: {},
            complete: {},
            interesting: {},
            waiting: {},
            proceedingMember: {},
            project_num: '',
            proceedDialog: false,
            interestingDialog: false,
            plusPointDialog: false,
            minusPointDialog: false,
            changePasswordDialog: false,
            modifyPassword: '',
            modifyPasswordChk: '',
            changePoint: 0,
            selectItems: [
                '디자인',
                'IT·프로그래밍',
                '번역·통역',
                '영상·사진·음향',
                '운세·상담',
                '마케팅',
            ],
            labels: [
                ['도덕성', '적극성', '신뢰성', '전문성', '리더십'],
                ['의사소통', '책임감', '수행능력', '수행자세', '리더십'],
            ],
            data: [],
        };
    },
    mounted() {
        console.log(`MyPage Mounted`);
        setTimeout(this.getMyFundingInfo, 200);
    },
    methods: {
        getMyFundingInfo() {
            console.log(this.memberInfo);

            if (this.memberInfo.status == 1) {
                // member의 status가 1( 멘토 )인 경우
                this.data.push(this.memberInfo.morality);
                this.data.push(this.memberInfo.positiveness);
                this.data.push(this.memberInfo.reliability);
                this.data.push(this.memberInfo.professional);
                this.data.push(this.memberInfo.leadership);
            } else {
                // member의 status가 2( 멘티 )인 경우
                this.data.push(this.memberInfo.communication);
                this.data.push(this.memberInfo.responsibility);
                this.data.push(this.memberInfo.performance);
                this.data.push(this.memberInfo.positiveness);
                this.data.push(this.memberInfo.leadership);
            }

            // id를 가지고 통신을 해서 진행중인 펀딩의 목록을 가져옴.
            http.get(`/project/proceeding/${this.memberInfo.id}`)
                .then((response) => {
                    if (response.data.message == 'success') {
                        this.proceeding = response.data.projectInfo; // proceeding에 프로젝트를 담음.
                    } else {
                        alert('진행중플젝 가져오기 실패!');
                    }
                })
                .catch(() => {
                    alert('에러발생!');
                });
            // setTimeout(function() {}, 200);
            // id를 가지고, 종료된 프로젝트를 가져옴.
            http.get(`/project/complete/${this.memberInfo.id}`)
                .then((response) => {
                    if (response.data.message == 'success') {
                        console.log(response.data.list);
                        this.complete = response.data.list; // complete에 리스트를 담음.
                    } else {
                        alert('종료된플젝 가져오기 실패!');
                    }
                })
                .catch(() => {
                    alert('에러발생!');
                });
            // id를 가지고, 관심있는 프로젝트를 가져옴.
            http.get(`/project/interesting/${this.memberInfo.id}`)
                .then((response) => {
                    if (response.data.message == 'success') {
                        this.interesting = response.data.interestingProjectInfo; // interesting에 관심프로젝트목록을 담음.
                    } else {
                        alert('관심플젝 가져오기 실패!');
                    }
                })
                .catch(() => {
                    alert('에러발생!');
                });

            // id를 가지고, 대기중인 프로젝트 목록을 가져옴,.
            http.get(`/project/waiting/${this.memberInfo.id}`)
                .then((response) => {
                    if (response.data.message == 'success') {
                        this.waiting = response.data.waitingProjectInfo; // waiting에 프로젝트 목록을 가져옴.
                    } else {
                        alert('대기중플젝 가져오기 실패!');
                    }
                })
                .catch(() => {});
        },
        // 평가페이지로 이동함.
        moveEvaluatePage(n, pn) {
            if (n == 3) this.$router.push({ name: 'Evaluate', params: { pn: pn } }); // pn을 가지고, 평가페이지 이동.
        },
        // 회원탈퇴
        deleteMember() {
            this.deleteDialog = false;
            http.post('/member/pwcheck', {
                // 비밀번호 체크.
                id: this.memberInfo.id,
                pw: this.password,
            })
                .then((response) => {
                    if (response.data.message == 'success') {
                        // message가 success로 들어오면,
                        http.delete(`/member/delete/${this.memberInfo.id}`) // id를 가지고 해당 회원id의 ㅣ탈퇴를 진행
                            .then(() => {
                                console.log('@@@');
                                this.$store
                                    .dispatch('LOGOUT')
                                    .then(() => {
                                        // store에 있는 logout을 동작함.
                                        // this.$router.push({ name: "" });
                                        swal('삭제 성공!', { icon: 'success' });
                                        if (this.$route.path !== '/') this.$router.replace('/'); // 로그아웃한 지점이 main이 아니면 main으로 이동함.
                                    })
                                    .catch(() => {
                                        console.log('로그아웃 문제!!!');
                                    });
                            })
                            .catch(() => {
                                swal('삭제 실패!', {
                                    icon: 'error',
                                });
                            });
                    }
                })
                .catch(() => {
                    alert('에러 발생!');
                });
        },

        modifyMember() {
            // 회원정보 수정
            this.modifyDialog = false;
            console.log('1');
            http.post('/member/pwcheck', {
                // 비밀번호 체크
                id: this.memberInfo.id,
                pw: this.password,
            })
                .then((response) => {
                    console.log('2');
                    console.log(response);
                    if (response.data.message == 'success') {
                        // 체크에 성공하면,
                        console.log('4');
                        http.put('/member/update', {
                            // 지금 입력된정보로 회원정보를 수정.
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
                                swal('수정 성공!', { icon: 'success' });
                            })
                            .catch(() => {
                                swal('수정 실패!', {
                                    icon: 'error',
                                });
                            });
                    }
                })
                .catch(() => {
                    console.log('3');
                    alert('에러 발생!');
                });
        },
        projectState(project_num) {
            // pn을 가지고, 진행중인 프로젝트의 멤버를 가져옴.
            this.project_num = project_num;
            console.log(this.project_num);
            http.get(`/project/memberchk/${project_num}`).then((response) => {
                // 해당 프로젝트의 멤버를 가져옴.
                if (response.data.message == 'success') {
                    this.participants = response.data.member;
                } else {
                    swal('확인 실패.', {
                        icon: 'error',
                    });
                }
            });
            http.get(`/project/waitingList/${this.project_num}`)
                .then((response) => {
                    if (response.data.message == 'success') {
                        this.proceedingMember = response.data.member; // proceedingMember에 멤버목록을 담음.
                    }
                })
                .catch(() => {
                    alert('에러발생!');
                });
        },
        enjoyGroup(id, funding_cost, participants) {
            let cost = funding_cost / participants; // cost는 펀딩액에서 참가인원을 나눈 값.
            let updatePoint = this.memberInfo.point - cost;

            console.log('cost : ' + cost);
            console.log('updateP : ' + updatePoint);

            if (updatePoint < 0) {
                // 만약 멤버포인트에서 cost를 뺀 값이 0보다 작으면
                swal('포인트가 부족합니다.', {
                    // 포인트가 부족합니다를 알려줌.
                    icon: 'error',
                });
            } else {
                // 포인트가 충분하다면,
                http.put('/project/permission', {
                    // pn, id, point를 가지고 동작.
                    project_num: this.project_num,
                    id: id,
                    point: updatePoint,
                })
                    .then((response) => {
                        if (response.data.message == 'success') {
                            // 성공 시
                            swal('수락 완료!', {
                                // 수락완료
                                icon: 'success',
                            });
                            this.proceedDialog = false;
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
            // 거절할 시
            console.log('id : ' + id);
            console.log(this.project_num);
            http.put('/project/denial', {
                project_num: this.project_num,
                id: id,
            })
                .then((response) => {
                    // 거절함.
                    if (response.data.message == 'success') {
                        swal('거절 완료!', {
                            icon: 'success',
                        });
                        this.proceedDialog = false;
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
        closeProceed() {
            this.proceedDialog = false;
        },
        // 포인트 충전.
        plusPoint() {
            this.changePoint = this.changePoint * 1 + this.memberInfo.point; // 입력한 포인트에 현재 회원의 포인트를 더함.
            http.put('/member/updatepoint', {
                id: this.memberInfo.id,
                point: this.changePoint,
            })
                .then((response) => {
                    if (response.data.message == 'success') {
                        swal('충전 완료!', {
                            icon: 'success',
                        });
                        this.plusPointDialog = false;
                        location.href = '/mypage';
                    } else {
                        swal('충전 실패!', {
                            icon: 'error',
                        });
                        this.plusPointDialog = false;
                    }
                })
                .catch(() => {
                    alert('에러발생!');
                });
        },
        // 포인트 전환
        minusPoint() {
            if (this.changePoint > this.memberInfo.point) {
                // 보유한 포인트 보다 전환하려는 포인트가 더 크다면
                swal('보유 포인트보다 작게 입력해주세요!', {
                    // 메시지 출력.
                    icon: 'error',
                });
                alert('');
                this.changePoint = 0; // 입력값을 0으로 초기화.
            } else {
                // 아니면
                this.changePoint = this.memberInfo.point - this.changePoint * 1; // 멤버포인트에서 작성한 포인트를 뺌.
                http.put('/member/updatepoint', {
                    id: this.memberInfo.id,
                    point: this.changePoint,
                })
                    .then((response) => {
                        if (response.data.message == 'success') {
                            // 성공시
                            swal('전환 완료!', {
                                icon: 'success',
                            });
                            this.minusPointDialog = false;
                            location.href = '/mypage'; // 마이페이지로 이동.
                        } else {
                            alert('전환실패!');
                            this.minusPointDialog = false;
                        }
                    })
                    .catch(() => {
                        alert('에러발생!');
                    });
            }
        },
        // 비밀번호 변경
        changePassword() {
            http.post(`/member/pwcheck`, {
                // 입력한 비밀번호 체크
                id: this.memberInfo.id,
                pw: this.password,
            }).then((response) => {
                if (response.data.message == 'success') {
                    // 성공 시
                    if (this.modifyPassword != this.modifyPasswordChk) {
                        // 만약 수정하려는 비밀번호와 체크하는 비밀번호가 틀리면,
                        swal('비밀번호, 비밀번호 확인이 틀립니다!', {
                            // 알림
                            icon: 'error',
                        });
                    } else if (this.modifyPassword == '' || this.modifyPasswordChk == '') {
                        // 둘 중한개가 값이 비어있다면
                        swal('변경 할 비밀번호를 입력해주세요!!', {
                            // 알림
                            icon: 'error',
                        });
                    } else {
                        http.put(`/member/changepw`, {
                            // 모든 조건을 만족한다면, id와 pw를 가지고 해당 id의 비밀번호를 수정.
                            id: this.memberInfo.id,
                            pw: this.modifyPassword,
                        });
                        swal('비밀번호 변경 완료!', {
                            icon: 'success',
                        });
                        this.changePasswordDialog = false;
                    }
                } else {
                    swal('비밀번호가 틀립니다!', {
                        icon: 'error',
                    });
                }
            });
        },
        // 페이지이동.
        movePage(name) {
            this.$router.push({ name: name });
        },
    },
};
</script>

<style scoped>
@import '../../assets/css/member.css';

p {
    padding: 10px 0 0 10px;
}

.fundingBtn {
    padding: 0;
}
.row {
    margin: 0;
}
@media screen and (min-width: 360px) and (max-width: 600px) {
    .fundingBtn {
        font-size: 14px;
    }
}
@media screen and (min-width: 600px) and (max-width: 960px) {
    .fundingBtn {
        font-size: 16px;
    }
}
@media screen and (min-width: 960px) and (max-width: 1264px) {
    .fundingBtn {
        font-size: 18px;
    }
}
</style>
