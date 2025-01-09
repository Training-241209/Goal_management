import { Navbar } from "./navbar";
import { TypeAnimation } from 'react-type-animation';

export function BasePage(){
    return <>
        <div className="2xl:container">
            <div className="2xl:container">
                <Navbar />
            </div>
            <div className="container justify-center">
                <div className="flex flex-col justify-center">
                    <h1 className="text-3xl ">Welcome to Aimzy!</h1>
                    <TypeAnimation
                        preRenderFirstString={true}
                        sequence={[
                            'We will help you Set objectives.', 
                            1000,
                            'We will help you build routines.',
                            1000,
                            'We will help you track progress.',
                            1000,
                            'We will help you stay motivated.',
                            1000,
                            () => {
                            console.log('Sequence completed');
                            },
                        ]}
                        speed={50}
                        wrapper="span"
                        cursor={true}
                        repeat={Infinity}
                        style={{ fontSize: '16px', display: 'inline-block', fontFamily:'' }}
                        />
                        {/* <p>Set objectives, build routines, track progress, and stay motivated every step of the way.</p> */}
                </div>
            </div>
        </div>
    </>
}