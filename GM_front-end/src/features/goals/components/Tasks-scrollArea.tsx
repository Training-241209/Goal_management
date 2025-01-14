import { ScrollArea, ScrollBar } from "@/components/ui/scroll-area"
import { Task } from "../schemas/goalModels"
import { TaskDetailsDialog } from "./taskDetails-Dialog"
import { useEffect, useState } from "react";
 


interface TasksScrollAreaProps{
    tasks: Task[]
}
 
export function TasksScrollArea({tasks}:TasksScrollAreaProps) {
  const [open, setopen] = useState(false);
  const [selectedTask, setSelectedTask] = useState<Task>({ id:0,
    name: "",
    description: "",
    timeFrames: null
  });

  function handleOnClick(task: Task){
    console.log(task);
    setSelectedTask(task);
    setopen(true);
  }

  useEffect(()=>{
    if(selectedTask.id != 0){
      const task = tasks.find(t => t.id === selectedTask.id);
      if (task) {
        setSelectedTask(task);
      }
    }
  },[tasks])

  return (
    <ScrollArea className="h-72  rounded-md border">
      <div className="p-4">
        <h1 className="mb-4 text-sm leading-none font-bold">Tasks</h1>
        {tasks.map((task) => (
          <figure  key={task.id} onClick={()=>{handleOnClick(task)}} className="flex space-x-4 hover:bg-muted/50 p-2 rounded-lg transition-colors cursor-pointer">
            <div className="text-sm" >
              {task.name}:{task.description}
            </div>
          </figure>
        ))}
      </div>
      <ScrollBar orientation="vertical"/>
      <TaskDetailsDialog open={open} setOpen={setopen} task={selectedTask}/>
    </ScrollArea>
    
  )
}