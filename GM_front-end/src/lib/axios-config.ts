import axios from 'axios';

export const axiosInstance = axios.create({
    baseURL: import.meta.env.VITE_API_URL,
    withCredentials: true,  
    headers: {
        "Content-Type": "application/json",
    },

})

axiosInstance.interceptors.request.use((config) => {
    const token = localStorage.getItem('jwtToken'); // Retrieve the token from storage
    if (token) {
      config.headers.Authorization = `${token}`;
    }
    return config;
  }, (error) => Promise.reject(error));