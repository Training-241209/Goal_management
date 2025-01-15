import { DataGrid, GridColDef, GridRowsProp } from '@mui/x-data-grid';
import { Task, TimeFrame } from "../schemas/goalModels";
import { TaskDetailsDialog } from "./taskDetails-Dialog";
import { useEffect, useState } from "react";

interface TasksDataGridProps {
  tasks: Task[];
}

export function TasksScrollArea({ tasks }: TasksDataGridProps) {
  const [open, setOpen] = useState(false);
  const [selectedTask, setSelectedTask] = useState<Task>({
    id: 0,
    name: "",
    description: "",
    timeFrames: null
  });

  const handleOnClick = (task: Task) => {
    setSelectedTask(task);
    setOpen(true);
  };

  const formatTimeFrames = (timeFrames: TimeFrame[] | null): string => {
    if (!timeFrames || timeFrames.length === 0) {
      return 'No Time Frame';
    }

    return timeFrames.map(tf => {
      const date = new Date(tf.date).toLocaleDateString();
      return `${date}: ${tf.startTime} - ${tf.endTime}`;
    }).join(', ');
  };

  const columns: GridColDef[] = [
    { field: 'name', headerName: 'Task Name', width: 200 },
    { field: 'description', headerName: 'Description', width: 200 },
    {
      field: 'timeFrames', 
      headerName: 'Time Frames', 
      width: 230,
      renderCell: (params) => formatTimeFrames(params.value as TimeFrame[] | null)
    },
  ];

  const rows: GridRowsProp = tasks.map((task) => ({
    id: task.id,
    name: task.name,
    description: task.description,
    timeFrames: task.timeFrames,
  }));

  useEffect(() => {
    if (selectedTask.id !== 0) {
      const task = tasks.find((t) => t.id === selectedTask.id);
      if (task) {
        setSelectedTask(task);
      }
    }
  }, [tasks, selectedTask.id]);

  return (
    <div className="h-72">
      <h1 className="mb-4 text-sm leading-none font-bold">Tasks</h1>
      <div style={{ height: 400, width: '100%' }}>
        <DataGrid
          rows={rows}
          columns={columns}
          onRowClick={(params) => handleOnClick(params.row as Task)}
        />
      </div>

      <TaskDetailsDialog open={open} setOpen={setOpen} task={selectedTask} />
    </div>
  );
}