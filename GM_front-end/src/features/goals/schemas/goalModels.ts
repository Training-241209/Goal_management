export type Goal = {
    id: number,
    objective: String,
    description: String,
    type: "Routine"| "Target",
    startDay: String,
    endDay: String
}