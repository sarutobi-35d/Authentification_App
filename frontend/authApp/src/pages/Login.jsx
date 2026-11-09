import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { login } from "../services/authService";

function Login() {

  const navigate = useNavigate();
  const [form, setForm] = useState({ email: "", password: "" });
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {

    e.preventDefault();

    setError("");
    setLoading(true);

    try {
      await login(form);
      navigate("/welcome", { replace: true });
    } catch (err) {
      setError(
        err.response?.data?.message || "Une erreur est survenue. Réessayez."
      );
    } finally {
      setLoading(false);
    }

  };

  return (
    <div>
      <h1>Connexion</h1>

      <form onSubmit={handleSubmit}>
        
        <input name="email" type="email" placeholder="Email"
               value={form.email} onChange={handleChange} required />
        <input name="password" type="password" placeholder="Mot de passe"
               value={form.password} onChange={handleChange} required />

        {error && <p style={{ color: "red" }}>{error}</p>}

        <button type="submit" disabled={loading}>
          {loading ? "Connexion..." : "Se connecter"}
        </button>

      </form>

      <p>Pas encore de compte ? <Link to="/register">S'inscrire</Link></p>
    </div>
  );
}

export default Login;