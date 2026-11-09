import { Navigate } from "react-router-dom";
import { getCurrentUser } from "../services/authService";

function ProtectedRoute({ children }) {

  if (!getCurrentUser()) {
    return <Navigate to="/login" replace />;
  }

  return children;
  
}

export default ProtectedRoute;