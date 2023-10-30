import axios from "axios";

// const baseURL = "https://ccbb.pro/api/"; //환경변수로 빼아하는가?
const baseURL = "http://localhost:8081/api/"; //환경변수로 빼아하는가?

export const ccbbApi = axios.create({
  withCredentials: true,
  baseURL: baseURL,
  headers: {
    "Content-Type": "application/json;charset=utf-8",
  },
});
