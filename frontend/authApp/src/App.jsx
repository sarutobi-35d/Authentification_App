import { Routes, Route, Navigate } from 'react-router-dom';
import Register from './pages/Register';
import Login from "./pages/Login";
import Welcome from "./pages/Welcome";
import ProtectedRoute from "./components/ProtectedRoute";

import './App.css'

function App() {
  

  return (
    <>
      <Routes>
        <Route path="/" element={<Navigate to="/register" replace />} />
        <Route path="/register" element={<Register />} />
        <Route path="/login" element={<Login />} />
        <Route
          path="/welcome"
          element={
            <ProtectedRoute>
              <Welcome />
            </ProtectedRoute>
          }
        />
        <Route path="*" element={<Navigate to="/login" replace />} />
      </Routes>
    </>
  )
}

export default App
