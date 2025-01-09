import { createLazyFileRoute } from '@tanstack/react-router'

import LoginPage from '../../../components/UI/Login'

export const Route = createLazyFileRoute('/_auth/auth/login')({
  component: RouteComponent,
})

function RouteComponent() {
  return <div>
    <LoginPage />
  </div>
}
