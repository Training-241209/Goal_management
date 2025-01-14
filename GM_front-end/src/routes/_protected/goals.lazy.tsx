import { Button } from '@/components/ui/button'
import { AddGoalForm } from '@/features/goals/components/addGoal-form';
import { AddTaskForm } from '@/features/goals/components/addTask-form';
import { GoalDetails } from '@/features/goals/components/goalDetails';
import { GoalsCarousel } from '@/features/goals/components/goalsCarousel';
import { TasksScrollArea } from '@/features/goals/components/Tasks-scrollArea';
import { useGoals } from '@/features/goals/hooks/use-goals';
import { Goal } from '@/features/goals/schemas/goalModels';
import { createLazyFileRoute } from '@tanstack/react-router'
import { useEffect, useState } from 'react'

export const Route = createLazyFileRoute('/_protected/goals')({
    component: RouteComponent,
})

function RouteComponent() {
    const [openAG, setOpenAG] = useState(false);
    const [openAT, setOpenAT] = useState(false);
    const { data, isLoading } = useGoals();
    const [selectedGoalId, SetselectedGoalId] = useState(0);
    const [selectedGoal, setSelectedGoal] = useState<Goal | undefined>(undefined);


    useEffect(() => {
        if(selectedGoalId!= 0){
            setSelectedGoal(data?.find((goal) => goal.id === selectedGoalId));
        }       
    }, [selectedGoalId,data])



    return <div className='min-h-screen flex flex-col'>
        <h1>Goals</h1>
        <Button variant="outline" onClick={() => setOpenAG(true)}>
            Add
        </Button>
        <AddGoalForm open={openAG} setOpen={setOpenAG} />
        <div className='flex flex-row flex-grow'>
            <div className=' bg-gray-200 min-w-fit flex justify-center items-center shadow-[inset_0_-2px_20px_rgba(0,0,0,0.6)]'>
                <GoalsCarousel onSelect={(d) => { SetselectedGoalId(d) }} />
            </div>
            {selectedGoal ?
                <div className='bg-white min-w-fit border-purple-500 border-4  rounded-sm'>
                    <GoalDetails goal={selectedGoal} />
                    <Button variant="outline" onClick={() => setOpenAT(true)}>
                        Add Task
                    </Button>
                    <AddTaskForm goalId={selectedGoal.id} open={openAT} setOpen={setOpenAT}/>
                    <TasksScrollArea tasks={selectedGoal.tasks}/>
                </div>
                : null}
        </div>

    </div>
}
