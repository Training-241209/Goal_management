import { useMutation, useQueryClient } from "@tanstack/react-query";
import { axiosInstance } from "@/lib/axios-config";
import { AddGoalSchema } from "../schemas/addGoal-schema";
import { toast } from "sonner";
import { format } from "date-fns";

export function useAddGoal() {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: async (values: AddGoalSchema) => {
        const formattedValues = {
            ...values,
            startDay: format(new Date(values.startDay), 'yyyy-MM-dd'),
            endDay: format(new Date(values.endDay), 'yyyy-MM-dd'),
          };
        const resp = await axiosInstance.post("/user/goal", formattedValues);
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