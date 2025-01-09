import { Button } from "@/components/UI/button"
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/UI/card"
import { Input } from "@/components/UI/input"
import { Label } from "@/components/UI/label"
import { cn } from "@/lib/utils"

interface signupFormProps{
  RenderLogin: ()=> void;
}
export function SignUpForm({RenderLogin}:signupFormProps) {
  return (
    <div className="flex flex-col gap-6">
      <Card>
        <CardHeader>
          <CardTitle className="text-2xl">SignUp</CardTitle>
          <CardDescription>
            Create an account with us!
          </CardDescription>
        </CardHeader>
        <CardContent>
          <form>
            <div className="flex flex-col gap-6">
              <div className="grid gap-2">
                <Label htmlFor="firstname">Firstname</Label>
                <Input
                  id="firstname"
                  type="text"
                  placeholder="Firstname"
                  required
                />
              </div>
              <div className="grid gap-2">
                <Label htmlFor="lastname">Lastname</Label>
                <Input
                  id="lastname"
                  type="text"
                  placeholder="Lastname"
                  required
                />
              </div>
              <div className="grid gap-2">
                <Label htmlFor="email">Email</Label>
                <Input
                  id="email"
                  type="email"
                  placeholder="m@example.com"
                  required
                />
              </div>
              <div className="grid gap-2">
                <div className="flex items-center">
                  <Label htmlFor="password">Password</Label>
                </div>
                <Input id="password" type="password" placeholder="**********" required />
              </div>
              <Button type="submit" className="w-full">
                SignUp
              </Button>
            </div>
            <div className="mt-4 text-center text-sm">
              Already have an account?{" "}
              <a onClick={RenderLogin} className="underline underline-offset-4 cursor-pointer">
                Login
              </a>
            </div>
           
          </form>
        </CardContent>
      </Card>
    </div>
  )
}
