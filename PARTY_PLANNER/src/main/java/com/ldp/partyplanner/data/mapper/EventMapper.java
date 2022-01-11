package com.ldp.partyplanner.data.mapper;

import com.ldp.partyplanner.models.Event;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EventMapper implements RowMapper<Event> {

    public Event mapRow(ResultSet resultSet, int i) throws SQLException {
        Event event = new Event();
        event.setEventId(resultSet.getInt("event_id"));
        event.setEventName(resultSet.getString("event_name"));
        event.setVenueAddress(resultSet.getString("venue_address"));
        event.setStartTime(resultSet.getTimestamp("start_time"));
        event.setEndTime(resultSet.getTimestamp("end_time"));
        return event;
    }

}
