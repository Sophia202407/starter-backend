import axios from 'axios';

const apiClient = axios.create({
  baseURL: 'http://localhost:8081/api',  // Spring Boot backend
  withCredentials: true,  // Enable cookies/JWT tokens
  headers: {
    'Content-Type': 'application/json',
  },
});

// Request interceptor for auth tokens
apiClient.interceptors.request.use((config) => {
  const token = localStorage.getItem('token');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

// Response interceptor for error handling
apiClient.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response?.status === 401) {
      // Handle token expiration (e.g., redirect to login)
      window.location.href = '/auth/login';
    }
    return Promise.reject(error);
  }
);

export default apiClient;