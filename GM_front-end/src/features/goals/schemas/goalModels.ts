export type Goal = {
    id: number,
    objective: string,
    description: string,
    type: "Routine"| "Target",
    startDate: string,
    endDate: string
    tasks: Task[]
}

export type Task = {
    id: number,
    name: string,
    description: string
    timeFrames: TimeFrame[]
}

export type TimeFrame = {
    id: number,
    date: string,
    startTime:string,
    endTime: string
}