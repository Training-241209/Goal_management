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

    return <div>
        <div className='flex flex-row'>
            <div className='bg-red-700 min-w-[50%]'>
                <h1 className='text-2xl font-bold'>Your active Goals</h1>

                <div className='min-h-[600px] placeholder:justify-center items-center my-10'>
                    <GoalsCarousel />
                </div>
            </div>
            <div className='bg-green-700 min-w-[50%]'>
                <Button variant="outline" onClick={() => setOpen(true)}>
                    Add
                </Button>
            </div>
        </div>
        <AddGoalForm open={open} setOpen={setOpen} />
    </div>
}
