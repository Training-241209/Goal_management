import { format } from "date-fns";
import { z } from "zod";

export const addGoalSchema = z.object({
    objective: z
        .string({
            message: "Required",
        })
        .min(1, "Required"),
    description: z
        .string({
            message: "Password is required"
        })
        .min(1, "Password is required"),
    type: z
        .union([z.literal('Routine'), z.literal('Target')])
    ,
    startDay: z
        .date().min(new Date(), {
            message: 'Date must be in the future',
        }).transform((date) => format(date, 'yyyy-MM-dd')),
    endDay: z
        .date().min(new Date(), {
            message: 'Date must be in the future',
        }).transform((date) => format(date, 'yyyy-MM-dd')),
}).refine(data => data.endDay > data.startDay, {
    message: 'End date must be after start date',
    path: ['endDate'],  // You can specify the path to the error field
});

export type AddGoalSchema = z.infer<typeof addGoalSchema>;