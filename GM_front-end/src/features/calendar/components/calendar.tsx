import React from 'react';
import { Calendar, momentLocalizer } from 'react-big-calendar';
import moment from 'moment';
import { useCalendarEvents } from '../hooks/use_calendarevent';
import "react-big-calendar/lib/css/react-big-calendar.css";
import { Card } from "@/components/ui/card";

const localizer = momentLocalizer(moment);

const MyCalendar = () => {
  const { calendarEvents } = useCalendarEvents();

  const eventStyleGetter = (event) => {
    let style = {
      backgroundColor: '#4f46e5', 
      borderRadius: '4px',
      opacity: 0.8,
      color: 'white',
      border: 'none',
      display: 'block',
    };

    // Color coding based on event type
    if (event.isGoal) {
      style.backgroundColor = '#2563eb'; 
    } else if (event.isTimeFrame) {
      if (event.type === 'ROUTINE') {
        style.backgroundColor = event.status ? '#059669' : '#dc2626';
      } else {
        style.backgroundColor = '#d97706';
      }
    }

    return {
      style,
    };
  };
  const tooltipAccessor = (event) => {
    if (event.isGoal) {
      return `Goal: ${event.title}\nType: ${event.type}\nDescription: ${event.description}`;
    } else if (event.isTimeFrame) {
      return `Task: ${event.title}\nType: ${event.type}\nDescription: ${event.description}`;
    }
    return event.title;
  };

  return (
    <Card className="p-4">
      <Calendar
        localizer={localizer}
        events={calendarEvents}
        startAccessor="start"
        endAccessor="end"
        eventPropGetter={eventStyleGetter}
        className="min-h-screen"
        views={['month', 'week', 'day']}
        defaultView="month"
        tooltipAccessor={tooltipAccessor}
        step={15}
        timeslots={4}
      />
    </Card>
  );
};

export default MyCalendar;