import { Button } from '@/components/ui/button'
import { AddGoalForm } from '@/features/goals/components/addGoal-form';
import { createLazyFileRoute } from '@tanstack/react-router'
import { useState } from 'react'

export const Route = createLazyFileRoute('/_protected/goals')({
  component: RouteComponent,
})

function RouteComponent() {
    const [open, setOpen] = useState(false);

  return <div>
    <Button variant="outline" onClick={()=>setOpen(true)}>
        Add
    </Button>
    <AddGoalForm open={open} setOpen={setOpen}/>
  </div>
}
