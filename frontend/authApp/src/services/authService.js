import api from "../api/axiosConfig";

export const register = (data) => api.post("/auth/register", data);

export const login = (data) => api.post("/auth/login", data);

// Les composants n’appellent jamais axios directement : ils passent par ces fonctions. 
// Si une URL change, la modification se fait à un seul endroit.