import { axiosInstance } from "@/lib/axios-config";
import { useQuery, UseQueryResult } from "@tanstack/react-query";
import { toast } from "sonner";
import { Goal } from "../schemas/goalModels";

export function useGoals(): UseQueryResult<Goal[]> {
  

  return useQuery({
    queryKey: ["goal"],
    queryFn: async () => {
      try {
        const resp = await axiosInstance.get("/user/goals");
        return resp.data;
      } catch (e) {
        console.error(e);
        toast.error("Failed to fetch ticket data.");
        return null;
      }
    },
  });
}