import { DashAuth } from '@/dashAuth'
import { createFileRoute, Outlet } from '@tanstack/react-router'

export const Route = createFileRoute('/_protected')({
  component: RouteComponent,
})

function RouteComponent() {
  return (
      <DashAuth>
          <Outlet />
      </DashAuth>
    )
}
