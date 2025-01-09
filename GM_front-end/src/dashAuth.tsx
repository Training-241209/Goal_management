import { Outlet } from "@tanstack/react-router";
import {SideBar} from "@/components/sidebar"

export function DashAuth(){
    return <>
        <div className="App">
            <SideBar />
            <div className="Content">
                <Outlet />
            </div>
        </div>
    </>
}