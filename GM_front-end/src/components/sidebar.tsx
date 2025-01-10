import { Sidebar, Menu, MenuItem, SubMenu } from 'react-pro-sidebar';


export function SideBar(){
    return <>
        <Sidebar>
            <Menu>
                <SubMenu>
                    <MenuItem>Hellow</MenuItem>
                    <MenuItem>wourld</MenuItem>
                </SubMenu>
            </Menu>
        </Sidebar>
    </>
}