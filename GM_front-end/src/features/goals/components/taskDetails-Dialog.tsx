import { useForm } from "react-hook-form";

import { Input } from "@/components/ui/input";
import { zodResolver } from "@hookform/resolvers/zod";
import { Button } from "@/components/ui/button";
import {
    Form,
    FormControl,
    FormField,
    FormItem,
    FormLabel,
    FormMessage,
} from "@/components/ui/form";
import { Textarea } from "@/components/ui/textarea";
import { useEffect, useState } from "react";
import { Dialog, DialogContent, DialogDescription, DialogHeader, DialogTitle } from "@/components/ui/dialog";
import { taskSchema, TaskSchema } from "../schemas/Task-schema";
import { Task } from "../schemas/goalModels";
import { ScrollArea, ScrollBar } from "@/components/ui/scroll-area";
import { CalendarIcon, DeleteIcon, DiamondPlus } from "lucide-react";
import { DateRange } from "react-day-picker";
import { addDays, format } from "date-fns";
import { Popover, PopoverContent, PopoverTrigger } from "@/components/ui/popover";
import { cn } from "@/lib/utils";
import { Calendar } from "@/components/ui/calendar";
import { SingleInputTimeRangeField } from '@mui/x-date-pickers-pro/SingleInputTimeRangeField';
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider/LocalizationProvider";
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { useUptTask } from "../hooks/use-uptTask";
import dayjs, { Dayjs } from 'dayjs';
import { DateRange as muiDateRange } from '@mui/x-date-pickers-pro/models';
interface TaskDetailsDialogProps {
    task: Task,
    open: boolean
    setOpen: (value: boolean) => void
}

export function TaskDetailsDialog({ task, open, setOpen }: TaskDetailsDialogProps) {

    const { mutate: update, isPending, isSuccess } = useUptTask();
    //const {data: goals} = useGoals();
    const [date, setDate] = useState<DateRange | undefined>({
        from: new Date(),
        to: new Date(),
    })
    const [value, setValue] = useState<muiDateRange<Dayjs>>(() => [
        dayjs(),
        dayjs().add(3, 'hour'),
    ]);

    // 1. Define your form.
    const form = useForm<TaskSchema>({
        resolver: zodResolver(taskSchema),
        defaultValues: {
            id: task.id,
            name: task.name,
            description: task.description
        }
    });

    useEffect(() => {
        if (isSuccess === true) {
            form.reset({
                id: task.id,
                name: task.name,
                description: task.description
            });
        }
    }, [isSuccess]);

    useEffect(() => {
        if (task) {
            console.log("useEffect triguered")
            form.reset({
                id: task.id,
                name: task.name,
                description: task.description
            });
        }
    }, [task]);

    function onSubmit(values: TaskSchema) {
        update(values);
    }

    return (
        <Dialog
            open={open}
            onOpenChange={() => {
                form.reset();
                setOpen(false);
            }}

        >
            <DialogContent className="flex min-w-fit">
                <div>
                    <DialogHeader>
                        <DialogTitle>Task</DialogTitle>
                    </DialogHeader>
                    <DialogDescription>
                        Make changes to your profile here. Click save when you're done.
                    </DialogDescription>
                    <Form {...form}>
                        <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-2 ">
                            <FormField
                                control={form.control}
                                name="name"
                                render={({ field }) => (
                                    <FormItem>
                                        <FormLabel>Name</FormLabel>
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

                            <div className="flex justify-between">
                                <Button type="submit" disabled={!form.formState.isDirty || isPending}>
                                    update Task
                                </Button>

                            </div>
                        </form>
                    </Form>
                </div>

                <div>
                    <h1 className="text-sm font-medium leading-none">TimeFrames</h1>
                    <div className="flex-col justify-between items-center">
                        <Popover>
                            <PopoverTrigger asChild>
                                <Button
                                    id="date"
                                    variant={"outline"}
                                    className={cn(
                                        "w-[300px] justify-start text-left font-normal",
                                        !date && "text-muted-foreground"
                                    )}
                                >
                                    <CalendarIcon />
                                    {date?.from ? (
                                        date.to ? (
                                            <>
                                                {format(date.from, "LLL dd, y")} -{" "}
                                                {format(date.to, "LLL dd, y")}
                                            </>
                                        ) : (
                                            format(date.from, "LLL dd, y")
                                        )
                                    ) : (
                                        <span>Pick a date</span>
                                    )}
                                </Button>
                            </PopoverTrigger>
                            <PopoverContent className="w-auto p-0" align="start">
                                <Calendar
                                    initialFocus
                                    mode="range"
                                    defaultMonth={date?.from}
                                    selected={date}
                                    onSelect={setDate}
                                    numberOfMonths={2}
                                />
                            </PopoverContent>
                        </Popover>
                        <div className="flex justify-between">
                            <LocalizationProvider dateAdapter={AdapterDayjs} >
                                <div>
                                    <SingleInputTimeRangeField label="From - To"
                                        value={value}
                                        onChange={(newValue) => setValue(newValue)} />
                                </div>
                            </LocalizationProvider>

                            <Button type="button" variant="secondary">
                                <DiamondPlus />
                                new
                            </Button>
                        </div>

                    </div>

                    <ScrollArea className="h-72  rounded-md border">
                        <div className="p-4">
                            {task.timeFrames?.map((tf) => (
                                <figure key={tf.id} className="flex space-x-4 hover:bg-muted/50 p-2 rounded-lg transition-colors cursor-pointer">
                                    <div className="text-sm" >
                                        {tf.date}|{tf.startTime}|{tf.endTime}
                                    </div>
                                    <Button type="button">
                                        <DeleteIcon />
                                    </Button>
                                </figure>
                            ))}
                        </div>
                        <ScrollBar orientation="vertical" />
                    </ScrollArea>
                    <div className="flex justify-end">
                        <Button type="button" onClick={() => { setOpen(false) }} >Close</Button>
                    </div>
                </div>

            </DialogContent>
        </Dialog>
    );
}