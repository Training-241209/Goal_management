import { BasePage } from '@/components/base-page'
import { createFileRoute, Outlet } from '@tanstack/react-router'

export const Route = createFileRoute('/_auth')({
  component: RouteComponent,
})

function RouteComponent() {
  return <BasePage>
    <Outlet />
  </BasePage>


}
