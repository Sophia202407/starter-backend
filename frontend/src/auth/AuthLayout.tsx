import { Outlet, Navigate } from 'react-router-dom';
import { AuthHeader } from './AuthHeader';
import { AuthFooter } from './AuthFooter';
import { useAuth } from './AuthContext';

const AuthLayout = () => {
  const { user, loading } = useAuth();

  if (loading) return <div className="flex justify-center items-center h-screen">Loading...</div>;
  if (user) return <Navigate to="/" replace />;

  return (
    <div className="min-h-screen flex flex-col">
      <AuthHeader />
      <main className="flex-grow flex items-center justify-center p-4">
        <div className="w-full max-w-md">
          <Outlet />
        </div>
      </main>
      <AuthFooter />
    </div>
  );
};

export default AuthLayout;