import { createLazyFileRoute } from '@tanstack/react-router'

export const Route = createLazyFileRoute('/_auth/auth/signup')({
  component: RouteComponent,
})

function RouteComponent() {
  return <div>Hello "/_auth/auth/signup"!</div>
}
