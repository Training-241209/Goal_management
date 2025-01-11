import { SideBar } from '@/components/sidebar'
import { createFileRoute, Outlet } from '@tanstack/react-router'
import "@/index.css"
import { RightSideBar } from '@/components/rightSideBar'

export const Route = createFileRoute('/_protected')({
  component: RouteComponent,
})

function RouteComponent() {
  return (
      <div className="app flex">
        <SideBar />
        <main className="content flex-1 bg-gray-100 overflow-auto">
          <Outlet />
        </main>
        <RightSideBar />
      </div>
  )
}
