<template>
    <v-container class="col-lg-3 col-md-5 col-sm-7 col-10 mb-15">
        <div>
            <v-spacer></v-spacer>
            <img src="@/assets/images/main/logo(Bg).png" alt="로고" class="col-12" />
            <v-spacer></v-spacer>
            <h2 class="centerText">회원가입</h2>
        </div>

        <v-tabs>
            <v-tab @click="openMentor">멘토</v-tab>
            <v-tab @click="openMentee">멘티</v-tab>
        </v-tabs>

        <form class="formBox" novalidate="true">
            <v-row>
                <v-text-field label="ID" type="email" v-model="member.id"></v-text-field>
                <v-card-actions>
                    <v-btn outlined @click="checkId">중복체크</v-btn>
                </v-card-actions>
            </v-row>
            <v-row>
                <v-text-field label="Password" type="password" v-model="member.pw"></v-text-field>
            </v-row>
            <v-row>
                <v-text-field
                    label="Password Confirm"
                    type="password"
                    v-model="pwChecked"
                ></v-text-field>
            </v-row>
            <v-row>
                <v-text-field
                    label="Name"
                    type="name"
                    v-model="member.name"
                    style="width:40%;"
                ></v-text-field>
                <v-spacer></v-spacer>
                <v-text-field
                    label="Age"
                    type="number"
                    v-model="member.age"
                    style="width:40%;"
                ></v-text-field>
            </v-row>

            <v-row>
                <v-text-field label="major" type="text" v-model="member.major"></v-text-field>
            </v-row>
            <v-row class="col-12">
                <v-text-field class="width-50 mr-2" type="tel" label="phone" v-model="phone1">
                </v-text-field>
                <v-spacer></v-spacer>
                <span style="line-height: 80px">-</span>
                <v-spacer></v-spacer>
                <v-text-field class="width-50 mr-2" type="tel" v-model="phone2"> </v-text-field>
                <v-spacer></v-spacer>
                <span style="line-height: 80px">-</span>
                <v-spacer></v-spacer>
                <v-text-field class="width-50" type="tel" v-model="phone3"> </v-text-field>
            </v-row>

            <v-row max-width:450>
                <v-autocomplete
                    class="width-50 mr-2"
                    ref="member.favorite_1"
                    v-model="member.favorite_1"
                    :items="selectItems"
                    label="선호 분야 1"
                    required
                >
                </v-autocomplete>
                <v-autocomplete
                    class="width-50 mr-2"
                    ref="member.favorite_2"
                    v-model="member.favorite_2"
                    :items="selectItems"
                    label="선호 분야 2"
                    required
                >
                </v-autocomplete>
                <v-autocomplete
                    class="width-50"
                    ref="member.favorite_3"
                    v-model="member.favorite_3"
                    :items="selectItems"
                    label="선호 분야 3"
                    required
                >
                </v-autocomplete>
            </v-row>

            <v-row>
                <v-textarea label="introduce" solo v-model="member.introduce"></v-textarea>
            </v-row>

            <v-row v-if="mentorForm">
                <v-textarea label="career" solo v-model="member.career"></v-textarea>
            </v-row>
            <v-row v-else-if="menteeForm">
                <v-textarea label="skill" solo v-model="member.skill"></v-textarea>
            </v-row>
        </form>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="primary" text @click="reset">
                초기화
            </v-btn>
            <v-btn color="primary" text @click="join">
                가입하기
            </v-btn>
        </v-card-actions>
    </v-container>
</template>
<script>
import http from '@/util/http-common';
import PV from 'password-validator';
import swal from 'sweetalert';

export default {
    data() {
        return {
            passwordSchema: new PV(),
            mentorForm: true,
            menteeForm: false,
            selectItems: [
                '디자인',
                'IT·프로그래밍',
                '번역·통역',
                '영상·사진·음향',
                '운세·상담',
                '마케팅',
            ],
            member: {
                id: '',
                pw: '',
                name: '',
                age: Number,
                major: '',
                status: 1,
                phone: '',
                favorite_1: '',
                favorite_2: '',
                favorite_3: '',
                career: '',
                skill: '',
                introduce: '',
            },
            phone1: '',
            phone2: '',
            phone3: '',
            idChecked: false,
            pwChecked: '',
        };
    },
    created() {
        this.passwordSchema
            .is()
            .min(8)
            .is()
            .max(100)
            .has()
            .digits()
            .has()
            .letters();
    },
    methods: {
        validEmail(email) {
            return /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i.test(
                email
            );
        },
        validPassword(password) {
            return /^.*(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[$@$!%*#?&]).*$/.test(password);
        },

        // 가입시 mentorForm을 열고, 가입하려는 status를 1로 set
        openMentor() {
            (this.mentorForm = true), (this.menteeForm = false), (this.member.status = 1);
        },
        // 가입시 menteeForm을 열고, 가입하려는 status를 2로 set
        openMentee() {
            (this.mentorForm = false), (this.menteeForm = true), (this.member.status = 2);
        },
        // 회원가입.
        join() {
            // 만약 id가 비어있다면,
            if (this.member.id == '') {
                console.log(this.member.id);
                swal('아이디를 입력해 주세요!', {
                    icon: 'error',
                });
            }
            // id중복체크를 하지 않았을 때
            else if (!this.idChecked) {
                swal('아이디 중복 검사를 해 주세요!', {
                    icon: 'error',
                });
            }
            // pw를 조건에 맞지 않게 입력했을 시
            else if (!this.validPassword(this.member.pw)) {
                swal('숫자와 문자, 특수문자 포함 형태의 8자리 이상으로 입력해주세요.', {
                    icon: 'error',
                });
            }
            // pw를 입력하지 않았을 때
            else if (this.member.pw == '') {
                swal('비밀번호를 입력해 주세요!', {
                    icon: 'error',
                });
            }
            // 확인 비밀번호를 입력하지 않았을 때
            else if (this.pwChecked == '') {
                swal('비밀번호 확인을 입력해 주세요!', {
                    icon: 'error',
                });
            }
            // 비밀번호와 확인 비밀번호가 일치하지 않을 때
            else if (this.member.pw != this.pwChecked) {
                swal('비밀번호가 일치하지 않습니다!', {
                    icon: 'error',
                });
            }
            // 이름을 입력하지 않았을 때
            else if (this.member.name == '') {
                swal('이름을 입력해 주세요!', {
                    icon: 'error',
                });
            }
            // 나이를 입력하지 않았을 때
            else if (this.member.age == Number) {
                swal('나이를 입력해 주세요!', {
                    icon: 'error',
                });
            }
            // 전공을 입력하지 않았을 떄
            else if (this.member.major == '') {
                swal('전공을 입력해 주세요!', {
                    icon: 'error',
                });
            }
            // phone을 입력하지 않았을 때
            else if (this.phone1 == '' || this.phone2 == '' || this.phone3 == '') {
                swal('전화번호를 입력해 주세요!', {
                    icon: 'error',
                });
            }
            // 흥미분야1를 입력하지 않았을 때
            else if (this.member.favorite_1 == '') {
                swal('관심분야 1 을 입력해 주세요!', {
                    icon: 'error',
                });
            }
            // 흥미분야2를 입력하지 않았을 때
            else if (this.member.favorite_2 == '') {
                swal('관심분야 2 을 입력해 주세요!', {
                    icon: 'error',
                });
            }
            // 흥미분야3를 입력하지 않았을 때
            else if (this.member.favorite_3 == '') {
                swal('관심분야 3 을 입력해 주세요!', {
                    icon: 'error',
                });
            }
            // 소개말을 입력하지 않았을 때
            else if (this.member.introduce == '') {
                swal('소개를 입력해 주세요!', {
                    icon: 'error',
                });
            }
            // 모든게 입력했을 때
            else {
                // 휴대폰번호를 000-0000-0000형식으로 설정.
                this.member.phone = this.phone1 + '-' + this.phone2 + '-' + this.phone3;
                let data = this.member;
                console.log(data);
                // 회원가입
                http.post(`/member/join`, data)
                    .then(() => {
                        swal('회원가입 성공!', {
                            icon: 'success',
                        });
                        location.href = '/'; // 가입 후 메인으로 이동.
                    })
                    .catch(() => {
                        swal('회원가입 실패!', {
                            icon: 'error',
                        });
                    });
            }
        },
        // id형식 및 중복 체크.
        checkId() {
            if (!this.validEmail(this.member.id)) {
                // 이메일 형식이 아닐 시
                swal('이메일 양식을 맞춰주세요!', {
                    icon: 'error',
                });
            } else {
                // 중복체크시 id를 가지고 체크.
                http.get(`/member/join/${this.member.id}`)
                    .then((response) => {
                        console.log(response);
                        if (response.data.message == 'fail') {
                            // fail이 오면 이미 존재하는 아이디.
                            swal('이미 존재하는 아이디 입니다!!', {
                                icon: 'error',
                            });
                            this.isChecked = false;
                        } else if (response.data.message == 'success') {
                            // success면 사용가능한 아이디.
                            swal('사용가능한 아이디 입니다!!', {
                                icon: 'success',
                            });
                            this.idChecked = true;
                        }
                    })
                    .catch();
            }
        },
        reset() {
            this.member.id = '';
            this.member.pw = '';
            this.pwChecked = '';
            this.member.name = '';
            this.member.age = Number;
            this.member.major = '';
            this.member.phone = '';
            this.member.favorite_1 = '';
            this.member.favorite_2 = '';
            this.member.favorite_3 = '';
            this.member.introduce = '';
        },
    },
};
</script>
<style scoped>
@import '../../assets/css/member.css';
.row + .row {
    margin-top: 10px;
}
</style>
