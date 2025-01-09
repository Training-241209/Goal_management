import { createLazyFileRoute } from '@tanstack/react-router'

import LoginPage from '../../../app/login/page'

export const Route = createLazyFileRoute('/_auth/auth/login')({
  component: RouteComponent,
})

function RouteComponent() {
  return <div>
    <LoginPage />
  </div>
}
