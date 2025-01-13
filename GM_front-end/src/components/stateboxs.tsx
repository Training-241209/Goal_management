import { Box, Card, CardContent} from "@mui/material";

export function StatBox({title, value, description, icon:Icon}){
    return <>
        <Card className="flex-1 p-6 rounded-xl shadow-sm">
            <CardContent className="space-y-2">
                <Box className="flex flex-row items-center justify-between">
                    <h1 className="text-lg font-bold">{title}</h1>
                    <Icon color="secondary" />
                </Box>
                <h3 className="text-2xl font-bold">{value}</h3>
                <p className="text-gray-600 text-sm">{description}</p>
            </CardContent>
        </Card>

    </>
}