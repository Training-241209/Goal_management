import { BasePage } from '@/components/base-page'
import { createLazyFileRoute } from '@tanstack/react-router'

export const Route = createLazyFileRoute('/home/base')({
  component: RouteComponent,
})

function RouteComponent() {
  return (
    <div>
      <BasePage />
    </div>
  )
}
