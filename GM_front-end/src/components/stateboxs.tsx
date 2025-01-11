import { Box, Card, CardContent, CardHeader, Typography } from "@mui/material";
import AccessTimeIcon from '@mui/icons-material/AccessTime';

export function StatBox(){
    return <>
        <Card className="flex-1 p-6 rounded-xl shadow-sm">
            <CardContent className="space-y-2">
                <Box className="flex flex-row items-center justify-between">
                    <h1 className="text-lg font-bold">Upcoming</h1>
                    <AccessTimeIcon color="secondary" />
                </Box>
                <h3 className="text-2xl font-bold">10</h3>
                <p className="text-gray-600 text-sm">Task pending</p>
            </CardContent>
        </Card>

    </>
}