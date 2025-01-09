import { createLazyFileRoute, useRouter } from '@tanstack/react-router'

export const Route = createLazyFileRoute('/')({
  component: RouteComponent,
})

function RouteComponent() {
  const router = useRouter();

  router.navigate({to: "/home"});
  return null;
}
