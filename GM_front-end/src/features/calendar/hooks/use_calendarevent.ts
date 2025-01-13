import { useQuery } from '@tanstack/react-query';
import { axiosInstance } from '@/lib/axios-config';

export function useCalendarEvents() {
    const { data: goals} = useQuery({
        queryKey: ["goals"],
        queryFn: async () => {
            try {
                const token = localStorage.getItem('jwtToken');
                const response = await axiosInstance.get("user/goals");
                return response.data;
            } catch (err) {
                console.error("Failed to fetch goals:", err);
                throw err;
            }
        },
    });

    const calendarEvents = goals?.reduce((events, goal) => {
        events.push({
            id: `goal-${goal.id}`,
            title: `Goal: ${goal.objective}`,
            start: new Date(goal.startDate),
            end: new Date(goal.endDate),
            isGoal: true,
            description: goal.description,
            type: goal.type,
            status: goal.status
        });

        // Process tasks and their timeframes
        if (goal.tasks) {
            goal.tasks.forEach(task => {
                if (task.timeFrames) {
                    const timeFrameEvents = task.timeFrames.map(timeFrame => {
                        const startDateTime = new Date(`${timeFrame.date}T${timeFrame.startTime}`);
                        const endDateTime = new Date(`${timeFrame.date}T${timeFrame.endTime}`);

                        return {
                            id: `timeframe-${timeFrame.id}`,
                            title: `${task.name}: ${timeFrame.objective}`,
                            start: startDateTime,
                            end: endDateTime,
                            isTimeFrame: true,
                            taskId: task.id,
                            goalId: goal.id,
                            status: timeFrame.status,
                            description: task.description,
                            type: task.type
                        };
                    });
                    events.push(...timeFrameEvents);
                }
            });
        }

        return events;
    }, []) || [];

    return {
        calendarEvents
    };
}