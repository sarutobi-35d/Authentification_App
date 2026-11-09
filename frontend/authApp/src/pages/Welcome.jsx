import { useNavigate } from "react-router-dom";
import { getCurrentUser, logout } from "../services/authService";

function Welcome() {
    
  const navigate = useNavigate();
  const user = getCurrentUser();

  const handleLogout = () => {
    logout();
    navigate("/login", { replace: true });
  };

  return (
    <div>
      <h1>Bienvenue, {user.prenom} {user.nom} !</h1>
      <p>Connecté avec : {user.email}</p>
      <button onClick={handleLogout}>Se déconnecter</button>
    </div>
  );
}

export default Welcome;