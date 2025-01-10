
import { useMutation, useQueryClient } from "@tanstack/react-query";
import { useRouter } from "@tanstack/react-router";
import { LoginSchema } from "../schemas/login-schema";
import { axiosInstance } from "@/lib/axios-config";

export function useLogin() {
  const queryClient = useQueryClient();
  const router = useRouter();

  return useMutation({
    mutationFn: async (values: LoginSchema) => {
        const resp = await axiosInstance.post("/user/login", values);
        return resp.data;
    },
    onSuccess: (data) => {
      queryClient.invalidateQueries();
      console.log("Logged in successfully.");
      console.log(data)
      router.navigate({ to: "/dashboard" });
    },
    onError: () => {
      
      console.error("Failed to login.");
    },
  });
}