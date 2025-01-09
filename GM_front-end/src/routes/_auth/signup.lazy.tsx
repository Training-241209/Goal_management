import { SignUpForm } from '@/features/auth/components/signup-form'
import { createLazyFileRoute } from '@tanstack/react-router'

export const Route = createLazyFileRoute('/_auth/signup')({
  component: RouteComponent,
})

function RouteComponent() {
  return <div>
    <SignUpForm/>
  </div>
}
