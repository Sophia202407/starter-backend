import { BrowserRouter as Router, Routes, Route, Navigate} from 'react-router-dom'
import AuthLayout from './auth/AuthLayout'
import AuthGuard from './auth/AuthGuard'
import LoginPage from './pages/LoginPage'
import RegisterPage from './pages/RegisterPage'
import DashboardPage from './pages/DashboardPage'
import { AuthProvider } from "./auth/AuthContext"; 

export default function App() {
  return (
  <AuthProvider>
  <Router>
    <Routes>
      {/* Public routes */}
      <Route path="/auth" element={<AuthLayout />}>
        <Route path="login" element={<LoginPage />} />
        <Route path="register" element={<RegisterPage />} />
      </Route>

      {/* Private routes */}
      <Route element={<AuthGuard />}>
        <Route path="/dashboard" element={<DashboardPage />} />
      </Route>

      {/* Redirects */}
      <Route path="/" element={<Navigate to="/auth/login" />} />
    </Routes>
  </Router>
</AuthProvider>
  );
}
