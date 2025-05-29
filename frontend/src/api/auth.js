import axios from 'axios'

// Base URL for auth-related endpoints
const API_URL = 'http://localhost:8081/api/auth'

// Create an Axios instance for API requests
const apiClient = axios.create({
  baseURL: API_URL,
  headers: {
    'Content-Type': 'application/json',
  },
})

// Attach JWT token automatically to each request (if available)
apiClient.interceptors.request.use((config) => {
  const token = localStorage.getItem('token')
  if (token) {
    config.headers.Authorization = `Bearer ${token}`
  }
  return config
})

// Auth functions
export const login = async (credentials) => {
  const response = await apiClient.post('/signin', credentials)
  return response.data
}

export const register = async (userData) => {
  const response = await apiClient.post('/signup', userData)
  return response.data
}

export const fetchProtectedData = async () => {
  const response = await apiClient.get('/books/protected')
  return response.data
}

