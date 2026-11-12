import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { logout } from "../services/authService";
import { getMe } from "../services/userService";

function Welcome() {
  
  const navigate = useNavigate();
  const [user, setUser] = useState(null);
  const [error, setError] = useState("");

  useEffect(() => {
    getMe()
      .then((response) => setUser(response.data))
      .catch(() => setError("Impossible de charger votre profil."));
  }, []);

  const handleLogout = () => {
    logout();
    navigate("/login", { replace: true });
  };

  if (error) {
    return (
      <div>
        <p style={{ color: "red" }}>{error}</p>
        <button onClick={handleLogout}>Retour à la connexion</button>
      </div>
    );
  }

  if (!user) {
    return <p>Chargement...</p>;
  }

  return (
    <div>
      <h1>Bienvenue, {user.prenom} {user.nom} !</h1>
      <p>Connecté avec : {user.email}</p>
      <button onClick={handleLogout}>Se déconnecter</button>
    </div>
  );
}

export default Welcome;