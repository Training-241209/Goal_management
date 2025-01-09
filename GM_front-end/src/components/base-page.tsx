import { Navbar } from "./navbar";
import { TypeAnimation } from "react-type-animation";

export function BasePage() {
  return (
    <>
      <Navbar />
        <div className="flex items-center justify-center h-screen">
            <div className="text-center mt-16">
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
        </div>
    </>
  );
}
