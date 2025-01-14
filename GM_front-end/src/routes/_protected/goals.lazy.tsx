import { Button } from '@/components/ui/button'
import { AddGoalForm } from '@/features/goals/components/addGoal-form';
import { GoalDetails } from '@/features/goals/components/goalDetails';
import { GoalsCarousel } from '@/features/goals/components/goalsCarousel';
import { useGoals } from '@/features/goals/hooks/use-goals';
import { Goal } from '@/features/goals/schemas/goalModels';
import { createLazyFileRoute } from '@tanstack/react-router'
import { useEffect, useState } from 'react'

export const Route = createLazyFileRoute('/_protected/goals')({
    component: RouteComponent,
})

function RouteComponent() {
    const [open, setOpen] = useState(false);
    const { data, isLoading } = useGoals();
    const [selectedGoalId,SetselectedGoalId] = useState(0);
    const [selectedGoal, setSelectedGoal] = useState<Goal | undefined>(undefined);

     
     useEffect(()=>{
        setSelectedGoal(data?.find((goal) => goal.id === selectedGoalId));
     },[selectedGoalId])

    return <div className='min-h-screen flex flex-col'>
        <h1>Goals</h1>
        <Button variant="outline" onClick={() => setOpen(true)}>
            Add
        </Button>
        <AddGoalForm open={open} setOpen={setOpen} />
        <div className='flex flex-row flex-grow'>
            <div className=' bg-gray-200 min-w-fit flex justify-center items-center shadow-[inset_0_-2px_20px_rgba(0,0,0,0.6)]'>
                <GoalsCarousel onSelect={(d)=>{SetselectedGoalId(d)}} />
            </div>
            {selectedGoal ? 
            <div className='bg-white min-w-fit border-purple-500 border-4  rounded-sm'>
                <GoalDetails goal={selectedGoal}/>

            </div>
            : null}   
        </div>

    </div>
}
