export type Goal = {
    id: number,
    objective: String,
    description: String,
    type: "Routine"| "Target",
    startDay: String,
    endDay: String
    tasks: Task[]
}

export type Task = {
    id: number,
    name: String,
    description: String
    timeFrames: TimeFrame[]
}

export type TimeFrame = {
    id: number,
    date: string,
    startTime:string,
    endTime: string
}