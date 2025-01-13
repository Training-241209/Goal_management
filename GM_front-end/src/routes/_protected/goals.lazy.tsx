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
        <div className='flex flex-row flex-grow'>
            <div className='  bg-gradient-to-r from-[#0f172a] to-[#334155] min-w-[40%] flex justify-center items-center'>
                <GoalsCarousel />
            </div>
            <div className='bg-green-700 min-w-[60%]'>
                <Button variant="outline" onClick={() => setOpen(true)}>
                    Add
                </Button>
                <AddGoalForm open={open} setOpen={setOpen} />
            </div>
        </div>

    </div>
}
