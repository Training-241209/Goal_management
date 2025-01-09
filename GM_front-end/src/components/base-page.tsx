import { LoginForm } from "@/features/auth/components/login-form";
import { Navbar } from "./navbar";
import { TypeAnimation } from "react-type-animation";

export function BasePage() {
  return (
    <>
      {/* <Navbar /> */}
      <div className="flex items-center justify-center h-screen px-10">
        <div className="flex lg:flex-row w-full justify-evenly items-center ">
          {/* Welcome Text */}
          <div className="text-center lg:text-left">
            <h1 className="text-5xl font-bold mb-6">Welcome to Aimzy!</h1>
            <TypeAnimation
              preRenderFirstString={true}
              sequence={[
                "We will help you set objectives.",
                1000,
                "We will help you build routines.",
                1000,
                "We will help you track progress.",
                1000,
                "We will help you stay motivated.",
                1000,
                () => {
                  console.log("Sequence completed");
                },
              ]}
              speed={50}
              wrapper="span"
              cursor={true}
              repeat={Infinity}
              style={{
                fontSize: "20px",
                display: "inline-block",
                fontFamily: "sans-serif",
              }}
            />
          </div>

          {/* Login Form */}
          <div className="rounded-lg shadow-lg w-full max-w-sm">
            <LoginForm />
          </div>
        </div>
      </div>
    </>
  );
}
