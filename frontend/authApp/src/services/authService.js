import api from "../api/axiosConfig";

const USER_KEY = "user";

export const register = (data) => api.post("/auth/register", data);

export const login = async (data) => {

  const response = await api.post("/auth/login", data);

  localStorage.setItem(USER_KEY, JSON.stringify(response.data));
  
  return response.data;

};

export const logout = () => {
  localStorage.removeItem(USER_KEY);
};

export const getCurrentUser = () => {

  try {
    const stored = localStorage.getItem(USER_KEY);
    return stored ? JSON.parse(stored) : null;
  } catch {
    return null;
  }

};

// Les composants n’appellent jamais axios directement : ils passent par ces fonctions. 
// Si une URL change, la modification se fait à un seul endroit.

// mémoriser l’utilisateur connecté
// Le login renvoie les infos de l’utilisateur, laissés dans une variable, 
// elles disparaissent au rechargement de la page. On les garde dans le localStorage du navigateur.