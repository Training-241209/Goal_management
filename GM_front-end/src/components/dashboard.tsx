import { NewsCard } from "./newsCard";
import { StatBox } from "./stateboxs";
import { TaskTable } from "./taskTable";
import { Button } from "./ui/button";



export function Dashboard(){
    return <>
        <div className="bg-gray-100 min-h-screen m-0 p-5 overflow-hidden">
            <div className="bg-white p-5 rounded-lg shadow-md">
                <h1 className="text-4xl font-bold text-purple-900 mb-4">Today Task</h1>
                <p className="text-gray-600 mb-6">Check your daily tasks and schedules</p>
                <Button className="bg-purple-500 text-white px-6 py-2 rounded-lg hover:bg-purple-600 transition-colors">
                    Today's schedule
                </Button>
            </div>
            
            {/* stateboxes */}
            <div className= "mx-auto mt-10 overflow-hidden">
                <div className="flex gap-12 flex-wrap">
                    <StatBox />
                    <StatBox />
                    <StatBox />
                </div>
            </div>

            <div className="mx-auto mt-10 overflow-hidden">
                <div className="flex gap-12 flex-wrap ">
                    <NewsCard />
                    <TaskTable />
                </div>
            </div>
        </div>
    </>
}