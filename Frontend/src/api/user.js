import http from '@/util/http-common';

function login(member, success, fail) { // 로그인
    http.defaults.headers['access-token'] = window.localStorage.getItem('access-token'); 
    const body = {
        id: member.id, 
        pw: member.pw,
    };

    http.post('member/login', JSON.stringify(body))
        .then(success)
        .catch(fail);
}

async function findById(id, success, fail) { 
    http.defaults.headers['access-token'] = window.localStorage.getItem('access-token');
    await http
        .get(`/member/mypage/${id}`)
        .then(success)
        .catch(fail);
}

export { login, findById };
