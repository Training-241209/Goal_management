import { useQuery } from '@tanstack/react-query';
import {jwtDecode} from 'jwt-decode';

interface DecodedToken {
  firstname: string;
  lastname: string;
  role: string;
}

const getUserFromToken = (): DecodedToken | null => {
  const token = localStorage.getItem('jwtToken');
  if (!token) return null;

  try {
    return jwtDecode<DecodedToken>(token);
  } catch (error) {
    console.error('Failed to decode token', error);
    return null;
  }
};

export const useUser = () => {
    return useQuery({
      queryKey: ['user'],
      queryFn: getUserFromToken,
      enabled: !!localStorage.getItem('jwtToken'),
    });
};