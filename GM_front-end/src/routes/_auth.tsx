import { createFileRoute, Outlet } from '@tanstack/react-router'

export const Route = createFileRoute('/_auth')({
  component: RouteComponent,
})

function RouteComponent() {
  return <div className='bg-gradient-to-br from-gray-900 to-gray-500 min-h-screen'>
    <Outlet />
  </div>
}
