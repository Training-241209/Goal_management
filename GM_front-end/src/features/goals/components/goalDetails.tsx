import { useForm } from "react-hook-form";
import { goalSchema, GoalSchema } from "../schemas/Goal-schema";
import { zodResolver } from "@hookform/resolvers/zod";
import { Form, FormControl, FormField, FormItem, FormLabel, FormMessage } from "@/components/ui/form";
import { Input } from "@/components/ui/input";
import { Textarea } from "@/components/ui/textarea";
import { Popover, PopoverContent, PopoverTrigger } from "@/components/ui/popover";
import { Button } from "@/components/ui/button";
import { cn } from "@/lib/utils";
import { format } from "date-fns";
import { CalendarIcon } from "lucide-react";
import { Calendar } from "@/components/ui/calendar";
import { Goal } from "../schemas/goalModels";
import { useUptGoal } from "../hooks/use-uptGoal";
import { useEffect } from "react";
interface GoalDetailsProps {
    goal: Goal
}
export function GoalDetails({ goal }: GoalDetailsProps) {
    const { mutate: update, isPending } = useUptGoal();

    // 1. Define your form.
    const form = useForm<GoalSchema>({
        resolver: zodResolver(goalSchema),
        defaultValues: {
            id: goal.id,
            objective: goal.objective,
            description: goal.description,
            type: goal.type,
            startDay: goal.startDate ? new Date(goal.startDate) : undefined,
            endDay: goal.endDate ? new Date(goal.endDate) : undefined,
        }

    });
    useEffect(() => {
        if (goal) {
            form.reset({
                id: goal.id,
                objective: goal.objective,
                description: goal.description,
                type: goal.type,
                startDay: goal.startDate ? new Date(goal.startDate) : undefined,
                endDay: goal.endDate ? new Date(goal.endDate) : undefined,
            });
        }

    }, [goal]);

    useEffect(() => {
        console.log(form);
    }, [form])



    function onSubmit(values: GoalSchema) {
        update(values);
    }

    return (

        <Form {...form}>
            <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-2 ">

                <FormField
                    control={form.control}
                    name="objective"
                    render={({ field }) => (
                        <FormItem>
                            <FormLabel>Objective</FormLabel>
                            <FormControl>
                                <Input {...field} min="1" />
                            </FormControl>
                            <FormMessage />
                        </FormItem>
                    )}
                />

                <FormField
                    control={form.control}
                    name="description"
                    render={({ field }) => (
                        <FormItem>
                            <FormLabel>Description</FormLabel>
                            <FormControl>
                                <Textarea placeholder="Description" {...field} maxLength={255} className="max-h-52" />
                            </FormControl>
                            <FormMessage />
                        </FormItem>
                    )}
                />
                {/* <FormField
                    control={form.control}
                    name="type"
                    render={({ field }) => (
                        <FormItem>
                            <FormLabel>Type</FormLabel>
                            <FormControl>
                                <Input {...field} disabled={true}/>
                            </FormControl>
                            <FormMessage />
                        </FormItem>
                    )}
                /> */}
                <div className="flex justify-between gap-2">
                    <FormField
                        control={form.control}
                        name="startDay"
                        render={({ field }) => (
                            <FormItem className="flex flex-col">
                                <FormLabel>Start day</FormLabel>
                                <Popover>
                                    <PopoverTrigger asChild>
                                        <FormControl>
                                            <Button
                                                variant={"outline"}
                                                className={cn(
                                                    "w-[200px] pl-3 text-left font-normal",
                                                    !field.value && "text-muted-foreground"
                                                )}
                                            >
                                                {field.value ? (
                                                    format(field.value, "PPP")
                                                ) : (
                                                    <span>Pick a date</span>
                                                )}
                                                <CalendarIcon className="ml-auto h-4 w-4 opacity-50" />
                                            </Button>
                                        </FormControl>
                                    </PopoverTrigger>
                                    <PopoverContent className="w-auto p-0" align="start">
                                        <Calendar
                                            mode="single"
                                            selected={field.value}
                                            onSelect={field.onChange}
                                            disabled={(date) =>
                                                date < new Date() || date > form.getValues("endDay")
                                            }
                                            initialFocus
                                        />
                                    </PopoverContent>
                                </Popover>
                                <FormMessage />
                            </FormItem>
                        )}
                    />
                    <FormField
                        control={form.control}
                        name="endDay"
                        render={({ field }) => (
                            <FormItem className="flex flex-col">
                                <FormLabel>End day</FormLabel>
                                <Popover>
                                    <PopoverTrigger asChild>
                                        <FormControl>
                                            <Button
                                                variant={"outline"}
                                                className={cn(
                                                    "w-[200px] pl-3 text-left font-normal",
                                                    !field.value && "text-muted-foreground"
                                                )}
                                            >
                                                {field.value ? (
                                                    format(field.value, "PPP")
                                                ) : (
                                                    <span>Pick a date</span>
                                                )}
                                                <CalendarIcon className="ml-auto h-4 w-4 opacity-50" />
                                            </Button>
                                        </FormControl>
                                    </PopoverTrigger>
                                    <PopoverContent className="w-auto p-0" align="start">
                                        <Calendar
                                            mode="single"
                                            selected={field.value}
                                            onSelect={field.onChange}
                                            disabled={(date) =>
                                                date < new Date() || date < form.getValues("startDay")
                                            }
                                            initialFocus
                                        />
                                    </PopoverContent>
                                </Popover>
                                <FormMessage />
                            </FormItem>
                        )}
                    />
                </div>
                <div className="flex justify-end">
                    <Button type="submit" disabled={!form.formState.isDirty || isPending}>
                        Save
                    </Button>
                </div>
            </form>
        </Form>

    );
}