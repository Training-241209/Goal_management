import { BasePage } from '@/components/base-page'
import { createLazyFileRoute } from '@tanstack/react-router'

export const Route = createLazyFileRoute('/_home/base')({
  component: RouteComponent,
})

function RouteComponent() {
  return (
    <div>
      <BasePage />
    </div>
  )
}
