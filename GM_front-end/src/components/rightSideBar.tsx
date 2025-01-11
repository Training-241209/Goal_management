import { Avatar, Box } from "@mui/material";
import { ProSidebar } from "react-pro-sidebar";


function stringToColor(string: string) {
    let hash = 0;
    let i;
  
    /* eslint-disable no-bitwise */
    for (i = 0; i < string.length; i += 1) {
      hash = string.charCodeAt(i) + ((hash << 5) - hash);
    }
  
    let color = '#';
  
    for (i = 0; i < 3; i += 1) {
      const value = (hash >> (i * 8)) & 0xff;
      color += `00${value.toString(16)}`.slice(-2);
    }
    /* eslint-enable no-bitwise */
  
    return color;
}
  
function stringAvatar(name: string) {
    return {
      sx: {
        bgcolor: stringToColor(name),
      },
      children: `${name.split(' ')[0][0]}${name.split(' ')[1][0]}`,
    };
}
export function RightSideBar(){
    return <>
        <ProSidebar >
                <div className="mx-5 flex flex-col items-center gap-5 justify-center bg-purple-100">
                    <Box className="flex items-center gap-3 p-5 border-b-1">
                        <Avatar {...stringAvatar('Nick Butani')} />
                        <Box>
                            <h1 className="font-semibold"> Nick Butani</h1>
                            <p className="text-xs font-thin">Role</p>
                        </Box>
                    </Box>
                    <Box>
                        <p>The secret of getting ahead is getting started.</p>
                    </Box>
                </div>
        </ProSidebar>
    </>
}