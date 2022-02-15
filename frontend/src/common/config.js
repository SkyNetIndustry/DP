import axios from "axios";

export const API_URL = "http://localhost:8080/api";

export const AxiosApi = {
    getAxiosSettings() {
        return axios.create({
            timeout: 1000,
            baseURL: API_URL,
            });
    }
}