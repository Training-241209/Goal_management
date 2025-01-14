import { Button } from '@/components/ui/button'
import { AddGoalForm } from '@/features/goals/components/addGoal-form';
import { GoalsCarousel } from '@/features/goals/components/goalsCarousel';
import { createLazyFileRoute } from '@tanstack/react-router'
import { useState } from 'react'

export const Route = createLazyFileRoute('/_protected/goals')({
    component: RouteComponent,
})

function RouteComponent() {
    const [open, setOpen] = useState(false);

    return <div className='min-h-screen flex flex-col'>
        <h1>Goals</h1>
        <Button variant="outline" onClick={() => setOpen(true)}>
            Add
        </Button>
        <AddGoalForm open={open} setOpen={setOpen} />
        <div className='flex flex-row flex-grow'>
            <div className=' bg-gray-200 min-w-fit flex justify-center items-center shadow-[inset_0_-2px_20px_rgba(0,0,0,0.6)]'>
                <GoalsCarousel />
            </div>
            <div className='bg-white min-w-fit border-purple-500 border-4  rounded-sm'>


            </div>
        </div>

    </div>
}
