import axios from "axios";
import { getToken, removeToken } from "../utils/tokenStorage";

const api = axios.create({
  baseURL: import.meta.env.VITE_API_URL,
  headers: { "Content-Type": "application/json" },
});

api.interceptors.request.use((config) => {

  const token = getToken();
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;

});

api.interceptors.response.use(

  (response) => response,
  (error) => {
    const isAuthRoute = error.config?.url?.startsWith("/auth/");
    if (error.response?.status === 401 && !isAuthRoute) {
      // console.log("401 recu", error.config.url);
      removeToken();
      window.location.href = "/login";
    }
    return Promise.reject(error);
  }

);

export default api;