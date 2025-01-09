import { LoginForm } from '@/features/auth/components/login-form'
import { createLazyFileRoute } from '@tanstack/react-router'

export const Route = createLazyFileRoute('/_auth/home')({
  component: RouteComponent,
})

function RouteComponent() {
  return (
    <div>
      <LoginForm />
    </div>
  )
}
