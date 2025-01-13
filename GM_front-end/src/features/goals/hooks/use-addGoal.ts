import { useMutation, useQueryClient } from "@tanstack/react-query";
import { axiosInstance } from "@/lib/axios-config";
import { AddGoalSchema } from "../schemas/addGoal-schema";
import { toast } from "sonner";

export function useAddGoal() {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: async (values: AddGoalSchema) => {
        const resp = await axiosInstance.post("/user/goal", values);
        return resp.data;
    },
    onSuccess: () => {
      queryClient.invalidateQueries();
      toast.success("Goal added.")
    },
    onError: () => {
      
      console.error("Failed to add Goal.");
    },
  });
}