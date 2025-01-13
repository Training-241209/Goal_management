import { Avatar, Box } from "@mui/material";
import { ProSidebar } from "react-pro-sidebar";
import { Calendar } from "@/components/ui/calendar"
import React from "react";
import "@/styles/rightsidebar.scss"

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
    const [date, setDate] = React.useState<Date | undefined>(new Date())
    return <>
        <ProSidebar>
                <div className="mx-5 flex flex-col items-center gap-5 justify-center bg-purple-100 rounded-lg">
                    <Box className="flex items-center gap-3 p-5 border-b-2 border-gray-400">
                        <Avatar {...stringAvatar('Nick Butani')} />
                        <Box >
                            <h1 className="font-semibold"> First last</h1>
                            <p className="text-xs font-thin">Role</p>
                        </Box>
                    </Box>
                    <Box className="p-5 justify-center">
                        <p className="text-center">The secret of getting ahead is getting started.</p>
                    </Box>
                </div>
                <div className="mx-5 mb-10 border-purple-500">
                    <Calendar
                        mode="single"
                        selected={date}
                        onSelect={setDate}
                        className="rounded-md border border-purple-500"
                    />
                </div>
        </ProSidebar>
    </>
}