import { Button } from "./ui/button";

export function Navbar(){
    return<>
        <div  className="2xl:container flex justify-between mx-14 px-11 mt-5 justify-items-center">
            <div className="text-2xl">
                GM
            </div>
            <div className="gap-2">
                <Button className="px-9 py-5">Login</Button>
            </div>
        </div>
    </>
}