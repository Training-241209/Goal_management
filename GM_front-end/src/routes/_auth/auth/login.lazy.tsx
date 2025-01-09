import { createLazyFileRoute } from '@tanstack/react-router'
import NotificationsSignInPageError from '../../../components/UI/signupPage'

export const Route = createLazyFileRoute('/_auth/auth/login')({
  component: RouteComponent,
})

function RouteComponent() {
  return <div>
    < NotificationsSignInPageError />
  </div>
}
