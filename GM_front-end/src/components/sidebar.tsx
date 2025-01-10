import { ProSidebar, Menu, MenuItem, SidebarHeader, SidebarContent, SidebarFooter} from 'react-pro-sidebar';
import "@/custom.scss"
import DashboardOutlinedIcon from '@mui/icons-material/DashboardOutlined';
import AdsClickIcon from '@mui/icons-material/AdsClick';
import MenuRoundedIcon from '@mui/icons-material/MenuRounded';
import { IconButton, Typography } from '@mui/material';
import AdsClickRoundedIcon from '@mui/icons-material/AdsClickRounded';
import { useState } from 'react';
import CalendarMonthOutlinedIcon from '@mui/icons-material/CalendarMonthOutlined';
import LogoutOutlinedIcon from '@mui/icons-material/LogoutOutlined';
export function SideBar(){
    const [isCollapsed, setIsCollapsed] = useState(false);
    return <>
    <div className='h-screen'>
        <ProSidebar collapsed={isCollapsed}>
            <SidebarHeader>
                <div className="flex items-center justify-between p-5" >
                    {!isCollapsed && <Typography variant="h6">AIMZY</Typography>}
                    <IconButton color='default' onClick={() => setIsCollapsed(!isCollapsed)}><MenuRoundedIcon /></IconButton>
                </div>
            </SidebarHeader>
            <SidebarContent className='mt-5'>
                <Menu iconShape="circle">
                    <div className='mt-3'>
                        <MenuItem icon={<DashboardOutlinedIcon />}>Dashboard</MenuItem>   
                    </div>
                    <div className="mt-3">
                        <MenuItem icon={<AdsClickRoundedIcon />}>Goals</MenuItem>
                    </div>
                    <div className="mt-3">
                        <MenuItem icon={<CalendarMonthOutlinedIcon />}>Calendar</MenuItem>
                    </div>
                </Menu>
            </SidebarContent>
            <SidebarFooter>
                <Menu iconShape='circle'>
                    <MenuItem icon={<LogoutOutlinedIcon />}>LogOut</MenuItem>
                </Menu>
            </SidebarFooter>
        </ProSidebar>
    </div>
    </>
}