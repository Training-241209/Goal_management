import { useUserStats } from "@/features/auth/hooks/use_userstat";
import { NewsCard } from "./newsCard";
import { StatBox } from "./stateboxs";
import { TaskTable } from "./taskTable";
import { Button } from "./ui/button";
import Goalsvg from "@/assets/GM.svg"
import ModeStandbyIcon from '@mui/icons-material/ModeStandby';
import AccessTimeIcon from '@mui/icons-material/AccessTime';
import CheckCircleIcon from '@mui/icons-material/CheckCircle';
export function Dashboard() {
    const { totalGoals, totalTasks, completedTasks } = useUserStats();
    return <>
        <div className="bg-gray-100 min-h-screen m-0 p-5 overflow-hidden content-center">
            <div className="bg-white p-5 rounded-lg shadow-md flex mt-10">
                <div className="flex flex-col max-w-md">
                    <h1 className="text-4xl font-bold text-purple-900 mb-4">Today Task</h1>
                    <p className="text-gray-600 mb-6">Check your daily tasks and schedules</p>
                    <button className="bg-purple-500 text-white px-6 py-3 rounded-lg hover:bg-purple-600 transition-colors">
                        Today's schedule
                    </button>
                </div>
                <div className=" absolute w-[270px] top-[-10px] right-[350px]">
                    <img src={Goalsvg} alt="Goal Illustration" className="w-full" />
                </div>
            </div>

            {/* stateboxes */}
            <div className="mx-auto mt-5 overflow-hidden">
                <div className="flex gap-12 flex-wrap">
                    <StatBox title="Total Goals" value={totalGoals} description="Goals set" icon={ModeStandbyIcon} />
                    <StatBox title="Total Tasks" value={totalTasks} description="Tasks within goals" icon={AccessTimeIcon} />
                    <StatBox title="Completed Tasks" value={completedTasks} description="Tasks completed" icon={CheckCircleIcon} />
                </div>
            </div>

            <div className="mx-auto mt-5 overflow-hidden">
                <div className="flex gap-12 flex-wrap ">
                    <NewsCard />
                    <TaskTable />
                </div>
            </div>
        </div>
    </>
}