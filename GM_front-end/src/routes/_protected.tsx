import { SideBar } from '@/components/sidebar'
import { createFileRoute, Outlet } from '@tanstack/react-router'

export const Route = createFileRoute('/_protected')({
  component: RouteComponent,
})

function RouteComponent() {
  return (
      <div className="App flex">
        <SideBar />
        <div className="Content flex-1">
          <Outlet />
        </div>
      </div>
  )
}
