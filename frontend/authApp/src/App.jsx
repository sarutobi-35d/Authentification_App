import { Routes, Route, Navigate } from 'react-router-dom';
import Register from './pages/Register';

import './App.css'

function App() {
  

  return (
    <>
      <Routes>
        <Route path="/" element={<Navigate to="/register" replace />} />
        <Route path="/register" element={<Register />} />
      </Routes>
    </>
  )
}

export default App
