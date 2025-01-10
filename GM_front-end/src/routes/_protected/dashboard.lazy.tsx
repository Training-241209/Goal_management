import { DashAuth } from '@/dashAuth'
import { createLazyFileRoute } from '@tanstack/react-router'

export const Route = createLazyFileRoute('/_protected/dashboard')({
  component: RouteComponent,
})

function RouteComponent() {
  return <div>
    <DashAuth />
  </div>
}
