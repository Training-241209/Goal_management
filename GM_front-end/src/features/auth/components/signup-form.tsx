import { Button } from "@/components/UI/button"
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/UI/card"
import { Input } from "@/components/UI/input"
import { Label } from "@/components/UI/label"
import { cn } from "@/lib/utils"
import { zodResolver } from "@hookform/resolvers/zod"
import { FieldError, useForm } from "react-hook-form"
import { signupFormSchema, SignupSchema } from "../schemas/signup-schema"
import { Form, FormControl, FormField, FormItem, FormMessage } from "@/components/UI/form"

interface signupFormProps {
  RenderLogin: () => void;
}
export function SignUpForm({ RenderLogin }: signupFormProps) {
  const form = useForm<SignupSchema>({
    resolver: zodResolver(signupFormSchema),
  });

  const { errors } = form.formState;

  function onSubmit(values: SignupSchema) {
    console.log(values);
  }

  function handleErrorStyling(value: FieldError | undefined): string {
    if (value) {
      return "border-red-600";
    }
    return "";
  }

  return (
    <div className="flex flex-col ">
      <Card >
        <CardHeader>
          <CardTitle className="text-2xl">SignUp</CardTitle>
          <CardDescription>
            Create an account with us!
          </CardDescription>
        </CardHeader>
        <CardContent>
          <Form {...form}>
            <form onSubmit={form.handleSubmit(onSubmit)}>
              <div className="flex flex-col">
                <div className="flex justify-between gap-2">
                  <FormField
                    control={form.control}
                    name="firstname"
                    render={({ field }) => (
                      <FormItem>
                        <Label>First name</Label>
                        <FormControl>
                          <Input className={handleErrorStyling(errors.firstname)} placeholder="john" {...field} max={20} />
                        </FormControl>
                        <FormMessage />
                      </FormItem>
                    )}
                  />
                  <FormField
                    control={form.control}
                    name="lastname"
                    render={({ field }) => (
                      <FormItem>
                        <Label>Last name</Label>
                        <FormControl>
                          <Input className={handleErrorStyling(errors.lastname)} placeholder="Doe" {...field} max={20} />
                        </FormControl>
                        <FormMessage />
                      </FormItem>
                    )}
                  />
                </div>
                <FormField
                  control={form.control}
                  name="email"
                  render={({ field }) => (
                    <FormItem>
                      <Label>Email</Label>
                      <FormControl>
                        <Input className={handleErrorStyling(errors.email)} placeholder="jd@example.com" {...field} />
                      </FormControl>
                      <FormMessage />
                    </FormItem>
                  )}
                />
                <FormField
                  control={form.control}
                  name="password"
                  render={({ field }) => (
                    <FormItem>
                      <Label>Password</Label>
                      <FormControl>
                        <Input className={handleErrorStyling(errors.password)} placeholder="**********" {...field} />
                      </FormControl>
                      <FormMessage />
                    </FormItem>
                  )}
                />
                <FormField
                  control={form.control}
                  name="confirmPassword"
                  render={({ field }) => (
                    <FormItem>
                      <Label>Confirm password</Label>
                      <FormControl>
                        <Input className={handleErrorStyling(errors.confirmPassword)} placeholder="**********" {...field} />
                      </FormControl>
                      <FormMessage />
                    </FormItem>
                  )}
                />

                <Button type="submit" className="w-full mt-6">
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
          </Form>
        </CardContent>
      </Card>
    </div>
  )
}
