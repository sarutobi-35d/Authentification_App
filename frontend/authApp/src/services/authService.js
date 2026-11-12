import api from "../api/axiosConfig";
import { getToken, setToken, removeToken } from "../utils/tokenStorage";

export const register = (data) => api.post("/auth/register", data);

export const login = async (data) => {

  const response = await api.post("/auth/login", data);
  setToken(response.data.token);
  return response.data;
  
};

export const logout = () => removeToken();

export const isAuthenticated = () => getToken() !== null;

// Les composants n’appellent jamais axios directement : ils passent par ces fonctions. 
// Si une URL change, la modification se fait à un seul endroit.

// mémoriser l’utilisateur connecté
// Le login renvoie les infos de l’utilisateur, laissés dans une variable, 
// elles disparaissent au rechargement de la page. On les garde dans le localStorage du navigateur.