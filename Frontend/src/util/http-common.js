import axios from 'axios';
// axios 객체 생성
export default axios.create({
    
    baseURL: 'https://i4d111.p.ssafy.io/moa/', // baseUrl
    headers: {
        'Content-type': 'application/json',
    },
});
