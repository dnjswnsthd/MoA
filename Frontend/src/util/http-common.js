import axios from "axios";
// axios 객체 생성
export default axios.create({
   //baseURL: 'http://localhost:8000/moa/',
  baseURL: "https://i4d111.p.ssafy.io/moa/",
  headers: {
    "Content-type": "application/json"
  }
});
