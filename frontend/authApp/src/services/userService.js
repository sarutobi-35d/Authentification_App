import api from "../api/axiosConfig";

export const getMe = () => api.get("/utilisateurs/me");