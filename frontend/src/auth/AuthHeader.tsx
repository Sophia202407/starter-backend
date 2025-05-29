import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from './AuthContext';

export const AuthHeader= () => {
  const { user, isAuthenticated, logout } = useAuth();
  const navigate = useNavigate();

  return (
    <header className="bg-white shadow-sm">
      <div className="max-w-7xl mx-auto px-4 py-3 flex justify-between items-center">
        <Link to="/" className="flex items-center">
          <span className="text-xl font-bold text-indigo-600">MyApp</span>
        </Link>

        <nav className="flex items-center space-x-4">
          {isAuthenticated ? (
            <>
              <span className="text-gray-700">Hi, {user?.name}</span>
              <button
                onClick={logout}
                className="px-3 py-1 text-sm text-red-600 hover:text-red-800"
              >
                Logout
              </button>
            </>
          ) : (
            <>
              <button
                onClick={() => navigate('/login')}
                className="px-3 py-1 text-sm text-gray-700 hover:text-indigo-600"
              >
                Login
              </button>
              <button
                onClick={() => navigate('/signup')}
                className="px-3 py-1 text-sm bg-indigo-600 text-white rounded hover:bg-indigo-700"
              >
                Sign Up
              </button>
            </>
          )}
        </nav>
      </div>
    </header>
  );
};