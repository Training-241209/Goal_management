import { Outlet } from "@tanstack/react-router";
import {SideBar} from "@/components/sidebar"

interface DashAuthProps {
    children?: React.ReactNode;
}

export function DashAuth({ children }: DashAuthProps){
    return <>
        <div className="App flex">
            <SideBar />
            <div className="Content flex-1">
             {children}
            </div>
        </div>
    </>
}