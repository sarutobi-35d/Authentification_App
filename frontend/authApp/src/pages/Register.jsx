
import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { register } from "../services/authService";

function Register() {

  const navigate = useNavigate();

  const [form, setForm] = useState({
    nom: "",
    prenom: "",
    email: "",
    password: "",
  });

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
      await register(form);
      navigate("/login");
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
      <h1>Inscription</h1>

      <form onSubmit={handleSubmit}>

        <input name="nom" placeholder="Nom" value={form.nom}
               onChange={handleChange} required />
        <input name="prenom" placeholder="Prénom" value={form.prenom}
               onChange={handleChange} required />
        <input name="email" type="email" placeholder="Email" value={form.email}
               onChange={handleChange} required />
        <input name="password" type="password" placeholder="Mot de passe"
               value={form.password} onChange={handleChange}
               minLength={8} required />

        {error && <p style={{ color: "red" }}>{error}</p>}

        <button type="submit" disabled={loading}>
          {loading ? "Création..." : "S'inscrire"}
        </button>
        
      </form>

      <p>Déjà un compte ? <Link to="/login">Se connecter</Link></p>
    </div>

  );
}

export default Register;