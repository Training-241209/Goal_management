import "react-big-calendar/lib/css/react-big-calendar.css"
import { Calendar, momentLocalizer } from 'react-big-calendar'
import moment from 'moment'
import "@/styles/calendar.css"


const localizer = momentLocalizer(moment)

const MyCalendar = () => (
    <div>
        <Calendar
            localizer={localizer}
            startAccessor="start"
            endAccessor="end"
            style={{
                height: '100vh',
                backgroundColor: 'white',
                padding: '20px',
                borderRadius: '12px',
                boxShadow: '0 4px 12px rgba(0, 0, 0, 0.1)',
                border: '1px solid #e0e0e0',
                fontFamily: '"Inter", sans-serif',
            }}
        />
    </div>
)

export default MyCalendar;