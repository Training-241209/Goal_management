import { createLazyFileRoute } from '@tanstack/react-router'
import SignUpPage from '@/app/signup/page'

export const Route = createLazyFileRoute('/_auth/auth/signup')({
  component: RouteComponent,
})

function RouteComponent() {
  return <div>
    <SignUpPage/>
  </div>
}
