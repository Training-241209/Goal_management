
import { useMutation, useQueryClient } from "@tanstack/react-query";
import { useRouter } from "@tanstack/react-router";
import { LoginSchema } from "../schemas/login-schema";

export function useLogin() {
  const queryClient = useQueryClient();
  const router = useRouter();

  return useMutation({
    mutationFn: async (values: LoginSchema) => {
      return {
        accessToken: 'mockAccessToken123',
        user: {
          id: 1,
          name: 'John Doe',
          email: values.email,
        },
      };
    },
    onSuccess: (data) => {
      queryClient.invalidateQueries();
      console.log("Logged in successfully.");
      localStorage.setItem('jwtToken', data.accessToken); 
      router.navigate({ to: "/dashboard" });
    },
    onError: () => {
      
      console.error("Failed to login.");
    },
  });
}