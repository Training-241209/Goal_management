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
import { Task, TimeFrameRequest } from "../schemas/goalModels";
import { ScrollArea, ScrollBar } from "@/components/ui/scroll-area";
import { CalendarIcon, DeleteIcon, DiamondPlus } from "lucide-react";
import { DateRange } from "react-day-picker";
import { format } from "date-fns";
import { Popover, PopoverContent, PopoverTrigger } from "@/components/ui/popover";
import { cn } from "@/lib/utils";
import { Calendar } from "@/components/ui/calendar";
import { SingleInputTimeRangeField } from '@mui/x-date-pickers-pro/SingleInputTimeRangeField';
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider/LocalizationProvider";
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { useUptTask } from "../hooks/use-uptTask";
import dayjs, { Dayjs } from 'dayjs';
import { DateRange as muiDateRange } from '@mui/x-date-pickers-pro/models';
import { useAddTimeFrame } from "../hooks/use-addTimeFrame";
import { toast } from "sonner";
import { useDeleteTimeFrame } from "../hooks/use-deleteTimeFrame";
import { useDeleteTask } from "../hooks/use-deleteTask";
interface TaskDetailsDialogProps {
    task: Task,
    open: boolean
    setOpen: (value: boolean) => void
}

export function TaskDetailsDialog({ task, open, setOpen }: TaskDetailsDialogProps) {

    const { mutate: update, isPending, isSuccess } = useUptTask();
    const { mutate: addTimeFrame, isPending: addTFPending } = useAddTimeFrame();
    const { mutate: deleteTimeFrame, isPending: deleteTFPending } = useDeleteTimeFrame();
    const { mutate: deleteTask, isPending: deleteIsPending } = useDeleteTask()
    //const {data: goals} = useGoals();
    const [date, setDate] = useState<DateRange | undefined>({
        from: new Date(),
        to: new Date(),
    })
    const [value, setValue] = useState<muiDateRange<Dayjs>>(() => [
        dayjs(),
        dayjs().add(3, 'hour'),
    ]);

    function handleTimeFrameDelete(id: number) {
        deleteTimeFrame(id);
    }

    function handleDelete(id: number) {
        deleteTask(id, {
            onSuccess: () => {
            setOpen(false);
            }
        });
    }

    function handleTimeFrameCreation() {
        if (date?.from && value[0] && value[1]) {
            if (date.from.getDate() < new Date().getDate()) {
                toast.error("The selected date range must be in the future.");
                return;
            }
            if (value[0] && value[0].isBefore(dayjs(), 'hour')) {
                toast.error("The selected time range must be in the future.");
                return;
            }
            const newtf: TimeFrameRequest = {
                taskId: task.id,
                startDate: format(date.from, "yyyy-MM-dd"),
                endDate: format(date.to ? date.to : date.from, "yyyy-MM-dd"),
                startTime: value[0].format("HH:mm:ss"),
                endTime: value[1].format("HH:mm:ss"),
            };
            addTimeFrame(newtf);
        }
        else if (!date?.from) {
            toast.warning("Date is required.")
        }
        else {
            toast.warning("Time range is required.")
        }
    }

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
                                <Button type="button" variant="destructive"
                                    onClick={() => { handleDelete(task.id) }}
                                    disabled={form.formState.isDirty || isPending || deleteIsPending}>
                                    Delete Task
                                </Button>
                                <Button type="submit" disabled={!form.formState.isDirty || isPending}>
                                    update
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

                            <Button type="button" variant="secondary" onClick={handleTimeFrameCreation} disabled={addTFPending}>
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
                                    <Button type="button" onClick={() => { handleTimeFrameDelete(tf.id) }} disabled={deleteTFPending}>
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